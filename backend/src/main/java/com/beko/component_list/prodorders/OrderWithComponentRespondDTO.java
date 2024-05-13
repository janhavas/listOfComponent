package com.beko.component_list.prodorders;

import com.beko.component_list.prodcomponents.ProdComponent;

import java.time.LocalDateTime;
import java.util.List;

public record OrderWithComponentRespondDTO(


        String id,
        String lineId,

        String ordNum,
        String fgNum,
        String altBom,
        String indFgNum,
        String indFgDesc,

        LocalDateTime schedDate,
        int ordQty,

        List<ProdComponent> components
) {
}
