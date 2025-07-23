package com.beko.component_list.prodorders;

import java.util.List;

public record OrderWithComponentFilterRequestDTO(

        List<String> orders,
        String wksId
) {
}
