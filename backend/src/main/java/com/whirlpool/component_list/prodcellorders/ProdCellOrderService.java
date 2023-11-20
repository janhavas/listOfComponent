package com.whirlpool.component_list.prodcellorders;

import com.whirlpool.component_list.prodcomponents.ProdComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdCellOrderService {

    private final ProdCellOrderDao prodCellOrderDao;

    public ProdCellOrderService(@Qualifier("jdbc_cell")ProdCellOrderDao prodCellOrderDao) {
        this.prodCellOrderDao = prodCellOrderDao;
    }

    public List<ProdCellOrder> getAllProdCellOrders(){
        return prodCellOrderDao.selectAllProdCellOrders();
    }
}
