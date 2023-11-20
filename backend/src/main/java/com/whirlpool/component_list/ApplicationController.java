package com.whirlpool.component_list;

import com.whirlpool.component_list.prodorders.OrderWithComponent;
import com.whirlpool.component_list.prodorders.ProdOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get-orders-with-components")
public class ApplicationController {

    private final ApplicationServices applicationServices;

    public ApplicationController(ApplicationServices applicationServices) {
        this.applicationServices = applicationServices;
    }


   @GetMapping
    public List<OrderWithComponent> getAllOrdersWithCompo() {
      //applicationServices.linkOrderWithComponents();
       return applicationServices.selectAllOrdersWithComponents();
    }


/*    @GetMapping
    public void getAllOrdersWithCompo() {
        applicationServices.linkOrderWithComponents();
    }*/

}
