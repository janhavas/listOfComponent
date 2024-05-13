package com.beko.component_list.prodcomponents;

import java.util.List;

public interface ProdComponentDao {

    List<ProdComponent> selectAllComponents(String fg, String altBom);
}
