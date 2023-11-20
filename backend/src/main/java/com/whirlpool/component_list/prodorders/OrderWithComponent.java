package com.whirlpool.component_list.prodorders;

import com.whirlpool.component_list.prodcomponents.ProdComponent;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Document
public class OrderWithComponent {
    @Id
    private String id;
    private String lineId;
    @Indexed(unique = true)
    private String ordNum;
    private String fgNum;
    private String altBom;
    private String indFgNum;
    private String indFgDesc;

    private LocalDateTime schedDate;
    private int ordQty;

    private List<ProdComponent> components;

    public OrderWithComponent(String lineId, String ordNum, String fgNum, String altBom, String indFgNum, String indFgDesc, LocalDateTime schedDate, int ordQty, List<ProdComponent> components) {
        this.lineId = lineId;
        this.ordNum = ordNum;
        this.fgNum = fgNum;
        this.altBom = altBom;
        this.indFgNum = indFgNum;
        this.indFgDesc = indFgDesc;
        this.schedDate = schedDate;
        this.ordQty = ordQty;
        this.components = components;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDateTime getSchedDate() {
        return schedDate;
    }

    public void setSchedDate(LocalDateTime schedDate) {
        this.schedDate = schedDate;
    }

    public int getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(int ordQty) {
        this.ordQty = ordQty;
    }

    public List<ProdComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ProdComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "OrderWithComponent{" +
                "id='" + id + '\'' +
                ", lineId='" + lineId + '\'' +
                ", ordNum='" + ordNum + '\'' +
                ", fgNum='" + fgNum + '\'' +
                ", altBom='" + altBom + '\'' +
                ", indFgNum='" + indFgNum + '\'' +
                ", indFgDesc='" + indFgDesc + '\'' +
                ", schedDate=" + schedDate +
                ", ordQty=" + ordQty +
                ", components=" + components +
                '}';
    }
}
