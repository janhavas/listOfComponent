package com.beko.component_list;

import com.beko.component_list.prodcomponents.ProdComponent;
import com.beko.component_list.prodcomponents.ProdComponentDao;
import com.beko.component_list.prodorders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ApplicationServices {


    private final OrdersWithComponentRepository repository;
    private final ProdOrderDao prodOrderDao;
    private final ProdComponentDao prodComponentDao;

    @Value("${application.config.camCodeList0101}")
    private String camCodes0101;

    @Value("${application.config.camCodeList0102}")
    private String camCodes0102;

    @Value("${application.config.camCodeList0103}")
    private String camCodes0103;

    @Value("${application.config.camCodeList0104}")
    private String camCodes0104;

    @Value("${application.config.camCodeList0201}")
    private String camCodes0201;

    @Value("${application.config.camCodeList0202}")
    private String camCodes0202;

    @Value("${application.config.camCodeList0203}")
    private String camCodes0203;

    @Value("${application.config.camCodeList0204}")
    private String camCodes0204;


    public ApplicationServices(OrdersWithComponentRepository repository, ProdOrderDao prodOrderDao, ProdComponentDao prodComponentDao) {
        this.repository = repository;
        this.prodOrderDao = prodOrderDao;
        this.prodComponentDao = prodComponentDao;

    }

//    private static void mergeList(List<ProdComponent> list1, List<ProdComponent> list2) {
//        for (ProdComponent component2 : list2) {
//            boolean found = false;
//            for (ProdComponent component1 : list1) {
//                if (component1.getMatNum().equals(component2.getMatNum())) {
//                    component1.setCamCode(component2.getCamCode());
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                boolean alreadyExists = false;
//                for (ProdComponent component1 : list1) {
//                    if (component1.getMatNum().equals(component2.getMatNum())) {
//                        alreadyExists = true;
//                        break;
//                    }
//                }
//                if (!alreadyExists) {
//                    list1.add(component2);
//                }
//
//            }
//        }
//    }

    @Async
    protected void linkOrderWithComponents() {

        List<ProdOrder> prodOrders = prodOrderDao.selectAllProdOrders();
        for (ProdOrder prodOrder : prodOrders) {
            //List<ProdComponent> prodComponents = prodComponentDao.selectAllComponents(prodOrder.getFgNum(), prodOrder.getAltBom());
            List<ProdComponent> prodComponentsWithCamCode = prodComponentDao.selectAllComponentsByCamCode(prodOrder.getFgNum(), prodOrder.getAltBom());

            //merge two components based on camCode, all from compo1 with camCode from compo2, all from compo2 when no compo1
//            if ((prodComponents != null && !prodComponents.isEmpty()) || (prodComponentsWithCamCode != null && !prodComponentsWithCamCode.isEmpty())) {
//                mergeList(prodComponentsWithCamCode, prodComponents);
//            }

            if (prodComponentsWithCamCode != null && !prodComponentsWithCamCode.isEmpty()) {
                prodComponentsWithCamCode.sort((o1, o2) -> {
                    int camCodeComparison = o1.getCamCode().compareTo(o2.getCamCode());
                    if (camCodeComparison != 0) {
                        return camCodeComparison;
                    } else {
                        return o1.getMatNum().compareTo(o2.getMatNum());
                    }
                });
            }
            if (prodComponentsWithCamCode != null && !prodComponentsWithCamCode.isEmpty()) {
                OrderWithComponent orderWithComponent = new OrderWithComponent(
                        prodOrder.getLineId(),
                        prodOrder.getOrdNum(),
                        prodOrder.getFgNum(),
                        prodOrder.getAltBom(),
                        prodOrder.getIndFgNum(),
                        prodOrder.getIndFgDesc(),
                        prodOrder.getSchedDate().toLocalDateTime(),
                        prodOrder.getOrdQty(),
                        prodComponentsWithCamCode
                );


                Optional<OrderWithComponent> order = repository.findOrderWithComponentByOrdNum(prodOrder.getOrdNum());
                if (order.isPresent()) {
                    repository.deleteOrderWithComponentByOrdNum(prodOrder.getOrdNum());
                    System.out.println("Order [%s] was deleted ".formatted(prodOrder.getOrdNum()));
                }

                repository.insert(orderWithComponent);
                System.out.println("Order [%s] was inserted".formatted(prodOrder.getOrdNum()));


            }
        }

    }

    public List<OrderWithComponentRespondDTO> selectAllOrdersWithComponentsByLineAndFamCodes(OrderWithComponentRequestDTO request) {
        LocalDate date = null;
        if ("dnes".equals(request.date())) {
            date = LocalDate.now();
        } else if ("zajtra".equals(request.date())) {
            date = LocalDate.now().plusDays(1);
        }

        List<String> camCodes = getCamCodesByWksId(request.wksId());
        String lineId = getLineIdByWksId(request.wksId());
        List<OrderWithComponentRespondDTO> filteredOrders = new ArrayList<>();

        if (!lineId.isEmpty() && date != null && !camCodes.isEmpty()) {
            LocalDateTime dateFrom = date.atStartOfDay();
            LocalDateTime dateTo = date.atStartOfDay().plusHours(24);

            List<OrderWithComponent> orders = null;
            LocalDateTime originalDateTo = dateTo;

            do {
                orders = repository.findOrderWithComponentsBySchedDateBetweenAndLineId(dateFrom, dateTo, lineId);
                if (orders.isEmpty()) {
                    dateTo = dateTo.plusDays(1);
                    if (dateTo.isAfter(originalDateTo.plusDays(2))) {
                        break; //If dateTo is more than orginalDateTo + 3 days, break the cycle to prevent runs method to infinity.
                    }
                }
            } while (orders.isEmpty());

            for (OrderWithComponent order : orders) {

                List<ProdComponent> components = order.getComponents();
                List<ProdComponent> filteredComponents = new ArrayList<>();

                for (ProdComponent component : components) {

                    if (camCodes.contains(component.getCamCode())) {
                        filteredComponents.add(component);
                    }
                }

                if (!filteredComponents.isEmpty()) {
                    OrderWithComponentRespondDTO filteredOrder = new OrderWithComponentRespondDTO(order.getId(), order.getLineId(), order.getOrdNum(), order.getFgNum(), order.getAltBom(), order.getIndFgNum(), order.getIndFgDesc(), order.getSchedDate(), order.getOrdQty(), filteredComponents);
                    filteredOrders.add(filteredOrder);
                }
            }

        }
        return filteredOrders;

    }

    public List<OrderWithComponent> selectAllOrdersWithComponents() {
        LocalDate date = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);
        LocalDateTime dateTime = date.atStartOfDay();

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = to.atStartOfDay().plusHours(8);

        String lineId = "23";
        //List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateIsGreaterThanAndLineId(dateTime, lineId);
        List<OrderWithComponent> list = repository.findOrderWithComponentsBySchedDateBetweenAndLineId(dateFrom, dateTo, lineId);
/*        for (OrderWithComponent owc: list) {
            System.out.println(owc.toString());

        }*/
        return list;
    }

    @Async
    protected void deleteObsoleteOrders() {
        LocalDate date = LocalDate.now().minusWeeks(1);
        LocalDateTime dateTime = date.atStartOfDay();
        repository.deleteOrderWithComponentsBySchedDateIsBefore(dateTime);
        System.out.println("Orders older than one week already deleted");
    }

    /**
     * Run scheduled methods one by one at specific time every day.
     */
    @Scheduled(cron = "0 10 5,9,12,15 * * MON-FRI")
    private void runMethods() {
        CompletableFuture<Void> futureGetOrders = CompletableFuture.runAsync(() -> {
            System.out.println("Linking Components with orders already started");
            linkOrderWithComponents();
            System.out.println("Components with orders already finished");

        });
        futureGetOrders.thenRunAsync(this::deleteObsoleteOrders);


    }

    public List<OrderWithComponentRespondDTO> selectAllOrdersWithComponentsByFilter(OrderWithComponentFilterRequestDTO userRequest) {
        LocalDate date = LocalDate.now().minusDays(8);
        LocalDateTime dateFrom = date.atStartOfDay();
        List<OrderWithComponentRespondDTO> filteredOrders = new ArrayList<>();
        List<String> camCodes = getCamCodesByWksId(userRequest.wksId());
        if (!camCodes.isEmpty()) {

            List<OrderWithComponent> orders = repository.findOrderWithComponentsBySchedDateIsGreaterThanAndOrdNumIn(dateFrom, userRequest.orders());
            if (!orders.isEmpty()) {

                // orders.forEach(order -> System.out.println("Loaded Order: " + order.getOrdNum()));
                //Vytvorenie mapy poradia vstupnych objednavok na nasledne zoradenie vystupnych objednavok.
                Map<String, Integer> orderPositionMap = new LinkedHashMap<>();
                for (int i = 0; i < userRequest.orders().size(); i++) {
                    orderPositionMap.putIfAbsent(userRequest.orders().get(i), i);
                }
                // orderPositionMap.forEach((key, value) -> System.out.println("Order: " + key + " Position: " + value));

                List<OrderWithComponent> sortedOrders = orders.stream()
                        .sorted(Comparator.comparingInt(order -> orderPositionMap.get(order.getOrdNum())))
                        .collect(Collectors.toList());

                // Debug výpis zoradených objednávok
                //sortedOrders.forEach(order -> System.out.println("Sorted Order: " + order.getOrdNum()));

                for (OrderWithComponent order : sortedOrders) {

                    List<ProdComponent> components = order.getComponents();
                    List<ProdComponent> filteredComponents = new ArrayList<>();

                    for (ProdComponent component : components) {

                        if (camCodes.contains(component.getCamCode())) {
                            filteredComponents.add(component);
                        }
                    }

                    if (!filteredComponents.isEmpty()) {
                        OrderWithComponentRespondDTO filteredOrder = new OrderWithComponentRespondDTO(order.getId(), order.getLineId(), order.getOrdNum(), order.getFgNum(), order.getAltBom(), order.getIndFgNum(), order.getIndFgDesc(), order.getSchedDate(), order.getOrdQty(), filteredComponents);
                        filteredOrders.add(filteredOrder);
                    }
                }
            }
        }

        return filteredOrders;
    }

    public List<String> getCamCodesByWksId(String wksId) {
        String camCodes = switch (wksId) {
            case "0101" -> camCodes0101;
            case "0102" -> camCodes0102;
            case "0103" -> camCodes0103;
            case "0104" -> camCodes0104;
            case "0201" -> camCodes0201;
            case "0202" -> camCodes0202;
            case "0203" -> camCodes0203;
            case "0204" -> camCodes0204;
            default -> "";
        };

        // Split the comma-separated string and convert it to a List
        return Arrays.asList(camCodes.split(",\\s*"));
    }

    private String getLineIdByWksId(String wksId) {

        return switch (wksId) {
            case "0101", "0102", "0103", "0104" -> "23";
            case "0201", "0202", "0203", "0204" -> "24";
            default -> "";
        };
    }

}
