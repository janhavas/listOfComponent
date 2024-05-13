package com.beko.component_list.prodcomponents;

import java.sql.Timestamp;

public class ProdComponent {


    private String matNum;
    private String matNumDesc;
    private String famCode;
    private String startDate;
    private String endDate;

    public ProdComponent(String matNum, String matNumDesc, String famCode, String startDate, String endDate) {

        this.matNum = matNum;
        this.matNumDesc = matNumDesc;
        this.famCode = famCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getMatNum() {
        return matNum;
    }

    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    public String getMatNumDesc() {
        return matNumDesc;
    }

    public void setMatNumDesc(String matNumDesc) {
        this.matNumDesc = matNumDesc;
    }

    public String getFamCode() {
        return famCode;
    }

    public void setFamCode(String famCode) {
        this.famCode = famCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{" +
                "matNum='" + matNum + '\'' +
                ", matNumDesc='" + matNumDesc + '\'' +
                ", famCode='" + famCode + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

