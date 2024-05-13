package com.beko.component_list.prodcomponents;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdComponentService {

    private final ProdComponentDao prodComponentDao;

    public ProdComponentService(@Qualifier("jdbc_comp")ProdComponentDao prodComponentDao) {
        this.prodComponentDao = prodComponentDao;
    }

    public List<ProdComponent> getAllProdComponents(String fg, String altNum){
        return prodComponentDao.selectAllComponents(fg, altNum);
    }
}
