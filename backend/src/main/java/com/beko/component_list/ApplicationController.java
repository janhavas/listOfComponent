package com.beko.component_list;

import com.beko.component_list.prodorders.OrderWithComponent;
import com.beko.component_list.prodorders.OrderWithComponentRequestDTO;
import com.beko.component_list.prodorders.OrderWithComponentRespondDTO;
import org.springframework.web.bind.annotation.*;

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

     return applicationServices.selectAllOrdersWithComponents();

    }

    @GetMapping("/pull-orders")
    public void getAllOrdersWithCompoCodNext() {

        applicationServices.linkOrderWithComponents();
    }

    @PostMapping
    public List<OrderWithComponentRespondDTO> getAllOrdersWithComponentByWksId(@RequestBody OrderWithComponentRequestDTO userRequest){
        return applicationServices.selectAllOrdersWithComponentsByLineAndFamCodes(userRequest);
    }



}
