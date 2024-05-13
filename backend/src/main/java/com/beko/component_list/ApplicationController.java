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
     // applicationServices.linkOrderWithComponents();
     return applicationServices.selectAllOrdersWithComponents();

    }

    @GetMapping("/cod-next")
    public List<OrderWithComponent> getAllOrdersWithCompoCodNext() {

        return applicationServices.selectAllOrdersWithComponentsCodNext();
    }

    @PostMapping
    public List<OrderWithComponentRespondDTO> getAllOrdersWithComponentByWksId(@RequestBody OrderWithComponentRequestDTO userRequest){
        return applicationServices.selectAllOrdersWithComponentsByLineAndFamCodes(userRequest);
    }



}
