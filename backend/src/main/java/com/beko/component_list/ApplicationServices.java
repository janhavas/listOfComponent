package com.beko.component_list;

import com.beko.component_list.prodorders.*;
import com.beko.component_list.prodcomponents.ProdComponent;
import com.beko.component_list.prodcomponents.ProdComponentDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class ApplicationServices {



    private final OrdersWithComponentRepository repository;
    private final ProdOrderDao prodOrderDao;

    private final ProdComponentDao prodComponentDao;

    @Value("${application.config.familyIdList0102}")
    private String famCodes0102;

    @Value("${application.config.familyIdList0103}")
    private String famCodes0103;

    @Value("${application.config.familyIdList0202}")
    private String famCodes0202;

    @Value("${application.config.familyIdList0103}")
    private String famCodes0203;


    public ApplicationServices(OrdersWithComponentRepository repository, ProdOrderDao prodOrderDao, ProdComponentDao prodComponentDao) {
        this.repository = repository;
        this.prodOrderDao = prodOrderDao;
        this.prodComponentDao = prodComponentDao;

    }
    @Async
    protected void linkOrderWithComponents(){

        List<ProdOrder> prodOrders = prodOrderDao.selectAllProdOrders();
        for (ProdOrder prodOrder: prodOrders) {
            List<ProdComponent> prodComponents = prodComponentDao.selectAllComponents(prodOrder.getFgNum(),prodOrder.getAltBom());
            if(prodComponents != null && !prodComponents.isEmpty()){
                OrderWithComponent orderWithComponent = new OrderWithComponent(
                  prodOrder.getLineId(),
                  prodOrder.getOrdNum(),
                  prodOrder.getFgNum(),
                  prodOrder.getAltBom(),
                  prodOrder.getIndFgNum(),
                  prodOrder.getIndFgDesc(),
                  prodOrder.getSchedDate().toLocalDateTime(),
                  prodOrder.getOrdQty(),
                  prodComponents
                );
                //TODO: check if the orderWithComponent already exist in the repo by checking the order number

                Optional<OrderWithComponent> order = repository.findOrderWithComponentByOrdNum(prodOrder.getOrdNum());
                if(order.isPresent()){
                    repository.deleteOrderWithComponentByOrdNum(prodOrder.getOrdNum());
                    System.out.println("Order [%s] was deleted ".formatted(prodOrder.getOrdNum()));
                }

                repository.insert(orderWithComponent);
                System.out.println("Order [%s] was inserted".formatted(prodOrder.getOrdNum()));



            }
        }

    }

    public List<OrderWithComponentRespondDTO> selectAllOrdersWithComponentsByLineAndFamCodes(OrderWithComponentRequestDTO request){
        LocalDate date = null;
        if("dnes".equals(request.date())){
            date = LocalDate.now();
        } else if ("zajtra".equals(request.date())) {
            date = LocalDate.now().plusDays(1);
        }


        List<String> famCodes = new ArrayList<>();
        String lineId = switch (request.wksId()) {
            case "0102" -> {
                famCodes.addAll(Arrays.asList("0092", "0027", "5007", "5095"));
                yield "23";
            }
            case "0103" -> {
                famCodes.addAll(Arrays.asList("5094", "5021", "0017", "5015", "0128", "0093", "5011"));
                yield "23";
            }
            case "0202" -> {
                famCodes.addAll(Arrays.asList("0092", "0027", "5007", "5095"));
                yield "24";
            }
            case "0203" -> {
                famCodes.addAll(Arrays.asList("5094", "5021", "0017", "5015", "0128", "0093", "5011"));
                yield "24";
            }
            default -> "";
        };
        List<OrderWithComponentRespondDTO> filteredOrders = new ArrayList<>();
        if(!lineId.isEmpty() && date !=null){
            LocalDateTime dateFrom = date.atStartOfDay();
            LocalDateTime dateTo = date.atStartOfDay().plusHours(24);

        List<OrderWithComponent> orders= repository.findOrderWithComponentsBySchedDateBetweenAndLineId(dateFrom, dateTo, lineId);

        for (OrderWithComponent order : orders) {

            List<ProdComponent> components = order.getComponents();
            List<ProdComponent> filteredComponents = new ArrayList<>();

            for (ProdComponent component : components) {
                if (famCodes.contains(component.getFamCode())) {
                   filteredComponents.add(component);
                }
            }

            if (!filteredComponents.isEmpty()) {
                OrderWithComponentRespondDTO filteredOrder = new OrderWithComponentRespondDTO(order.getId(),order.getLineId(), order.getOrdNum(), order.getFgNum(), order.getAltBom(), order.getIndFgNum(), order.getIndFgDesc(), order.getSchedDate(), order.getOrdQty(), filteredComponents);
                filteredOrders.add(filteredOrder);
            }
        }

        for (OrderWithComponentRespondDTO owc: filteredOrders) {
            System.out.println(owc.toString());

        }
        }
        return filteredOrders;

    }

    public List<OrderWithComponent> selectAllOrdersWithComponents(){
        LocalDate date = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);
        LocalDateTime dateTime = date.atStartOfDay();

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = to.atStartOfDay().plusHours(8);

        String lineId= "22";
        //List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateIsGreaterThanAndLineId(dateTime, lineId);
        List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateBetweenAndLineId(dateFrom, dateTo,  lineId);
        for (OrderWithComponent owc: list) {
            System.out.println(owc.toString());

        }
        return list;
    }

    public List<OrderWithComponent> selectAllOrdersWithComponentsCodNext(){
        LocalDate date = LocalDate.now().plusDays(1);;
        LocalDateTime dateTime = date.atStartOfDay();
        System.out.println("Cod next date " + dateTime);
        String lineId= "23";
        List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateIsGreaterThanAndLineId(dateTime, lineId);
        for (OrderWithComponent owc: list) {
            System.out.println(owc.toString());

        }
        return list;
    }

    public List<OrderWithComponent> selectAllOrdersWithComponentsAll(){
        LocalDate date = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(5);
        LocalDateTime dateTime = date.atStartOfDay();

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = to.atStartOfDay().plusHours(8);

        String lineId= "23";
        //List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateIsGreaterThanAndLineId(dateTime, lineId);
        List<OrderWithComponent> list= repository.findOrderWithComponentsBySchedDateBetweenAndLineId(dateFrom, dateTo,  lineId);
        for (OrderWithComponent owc: list) {
            System.out.println(owc.toString());

        }
        return list;
    }

    @Async
    protected void deleteObsoleteOrders(){
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


}
