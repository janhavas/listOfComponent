package com.whirlpool.component_list.prodorders;

import java.sql.Timestamp;

public class ProdOrder {
    private int id;

    private String lineId;

    private String ordNum;
    private String fgNum;
    private String altBom;
    private String indFgNum;
    private String indFgDesc;

    private Timestamp schedDate;
    private int ordQty;

    public ProdOrder(int id, String lineId, String ordNum, String fgNum, String altBom, String indFgNum, String indFgDesc, Timestamp schedDate, int ordQty) {
        this.id = id;
        this.lineId = lineId;
        this.ordNum = ordNum;
        this.fgNum = fgNum;
        this.altBom = altBom;
        this.indFgNum = indFgNum;
        this.indFgDesc = indFgDesc;
        this.schedDate = schedDate;
        this.ordQty = ordQty;
    }

    public ProdOrder(String lineId, String ordNum, String fgNum, String altBom, String indFgNum, String indFgDesc, Timestamp schedDate, int ordQty) {
        this.lineId = lineId;
        this.ordNum = ordNum;
        this.fgNum = fgNum;
        this.altBom = altBom;
        this.indFgNum = indFgNum;
        this.indFgDesc = indFgDesc;
        this.schedDate = schedDate;
        this.ordQty = ordQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public String getFgNum() {
        return fgNum;
    }

    public void setFgNum(String fgNum) {
        this.fgNum = fgNum;
    }

    public String getAltBom() {
        return altBom;
    }

    public void setAltBom(String altBom) {
        this.altBom = altBom;
    }

    public String getIndFgNum() {
        return indFgNum;
    }

    public void setIndFgNum(String indFgNum) {
        this.indFgNum = indFgNum;
    }

    public String getIndFgDesc() {
        return indFgDesc;
    }

    public void setIndFgDesc(String indFgDesc) {
        this.indFgDesc = indFgDesc;
    }

    public Timestamp getSchedDate() {
        return schedDate;
    }

    public void setSchedDate(Timestamp schedDate) {
        this.schedDate = schedDate;
    }

    public int getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(int ordQty) {
        this.ordQty = ordQty;
    }
}

