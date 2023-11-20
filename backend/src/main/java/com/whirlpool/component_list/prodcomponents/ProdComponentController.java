package com.whirlpool.component_list.prodcomponents;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get-components")
public class ProdComponentController {

    private final ProdComponentService prodComponentService;


    public ProdComponentController(ProdComponentService prodComponentService) {
        this.prodComponentService = prodComponentService;
    }
    @PostMapping
    public List<ProdComponent> getAllProdComponents(@RequestBody ComponentRequest componentRequest ){
        return prodComponentService.getAllProdComponents(componentRequest.fg(), componentRequest.altBom());
    }
}
