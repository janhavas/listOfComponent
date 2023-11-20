package com.whirlpool.component_list.prodcellorders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get-cell-orders")
public class ProdCellOrderController {

    private final ProdCellOrderService prodCellOrderService;

    public ProdCellOrderController(ProdCellOrderService prodCellOrderService) {
        this.prodCellOrderService = prodCellOrderService;
    }
    @GetMapping
    public List<ProdCellOrder> getAllProdCellOrders(){
        return prodCellOrderService.getAllProdCellOrders();
    }
}
