package com.whirlpool.component_list.prodorders;

import com.whirlpool.component_list.prodcomponents.ProdComponent;

import java.util.List;

public interface ProdOrderDao {

    List<ProdOrder> selectAllProdOrders();
}
