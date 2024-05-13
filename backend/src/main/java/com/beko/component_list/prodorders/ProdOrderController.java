package com.beko.component_list.prodorders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get-orders")
public class ProdOrderController {

    private final ProdOrderService prodOrderService;

    public ProdOrderController(ProdOrderService prodOrderService) {
        this.prodOrderService = prodOrderService;
    }
    @GetMapping
    public List<ProdOrder> getAllProdOrders(){
        return prodOrderService.getAllProdOrders();
    }
}
