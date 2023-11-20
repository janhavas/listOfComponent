package com.whirlpool.component_list.prodorders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdOrderService {

    private final ProdOrderDao prodOrderDao;

    public ProdOrderService(@Qualifier("jdbc_fas")ProdOrderDao prodOrderDao) {
        this.prodOrderDao = prodOrderDao;
    }

    public List<ProdOrder> getAllProdOrders(){


        return prodOrderDao.selectAllProdOrders();
    }
}
