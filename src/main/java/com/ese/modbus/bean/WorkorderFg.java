package com.ese.modbus.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "workorder_fg")
public class WorkorderFg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workorder_fg_id_gen")
    @SequenceGenerator(name = "workorder_fg_id_gen", sequenceName = "workorder_fg_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "workorder_id", nullable = false)
    private Integer workorderId;

    @Lob
    @Column(name = "fg_barcode", nullable = false)
    private String fgBarcode;

    @Lob
    @Column(name = "fg_spec", nullable = false)
    private String fgSpec;

    @Lob
    @Column(name = "fg_description")
    private String fgDescription;

    @Column(name = "fg_lenght")
    private Double fgLenght;

    @Column(name = "fg_qty")
    private Integer fgQty;

    @Column(name = "fg_weight_estimate")
    private Double fgWeightEstimate;

    @Column(name = "fg_area")
    private Double fgArea;

    @Column(name = "fg_qty_actual")
    private Double fgQtyActual;

    @Column(name = "fg_weight_actual")
    private Double fgWeightActual;

    @Column(name = "fg_bundle")
    private Double fgBundle;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "modify_by")
    private Integer modifyBy;

    @Column(name = "modify_datetime")
    private Instant modifyDatetime;

    @Column(name = "data_set")
    private Integer dataSet;

    @Column(name = "is_send")
    private Boolean isSend;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("workorderId")
    public Integer getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(Integer workorderId) {
        this.workorderId = workorderId;
    }

    @JsonProperty("fgBarcode")
    public String getFgBarcode() {
        return fgBarcode;
    }

    public void setFgBarcode(String fgBarcode) {
        this.fgBarcode = fgBarcode;
    }

    @JsonProperty("fgSpec")
    public String getFgSpec() {
        return fgSpec;
    }

    public void setFgSpec(String fgSpec) {
        this.fgSpec = fgSpec;
    }

    @JsonProperty("fgDescription")
    public String getFgDescription() {
        return fgDescription;
    }

    public void setFgDescription(String fgDescription) {
        this.fgDescription = fgDescription;
    }

    @JsonProperty("fgLenght")
    public Double getFgLenght() {
        return fgLenght;
    }

    public void setFgLenght(Double fgLenght) {
        this.fgLenght = fgLenght;
    }

    @JsonProperty("fgQty")
    public Integer getFgQty() {
        return fgQty;
    }

    public void setFgQty(Integer fgQty) {
        this.fgQty = fgQty;
    }

    @JsonProperty("fgWeightEstimate")
    public Double getFgWeightEstimate() {
        return fgWeightEstimate;
    }

    public void setFgWeightEstimate(Double fgWeightEstimate) {
        this.fgWeightEstimate = fgWeightEstimate;
    }

    @JsonProperty("fgArea")
    public Double getFgArea() {
        return fgArea;
    }

    public void setFgArea(Double fgArea) {
        this.fgArea = fgArea;
    }

    @JsonProperty("fgQtyActual")
    public Double getFgQtyActual() {
        return fgQtyActual;
    }

    public void setFgQtyActual(Double fgQtyActual) {
        this.fgQtyActual = fgQtyActual;
    }

    @JsonProperty("fgWeightActual")
    public Double getFgWeightActual() {
        return fgWeightActual;
    }

    public void setFgWeightActual(Double fgWeightActual) {
        this.fgWeightActual = fgWeightActual;
    }

    @JsonProperty("fgBundle")
    public Double getFgBundle() {
        return fgBundle;
    }

    public void setFgBundle(Double fgBundle) {
        this.fgBundle = fgBundle;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("isValid")
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @JsonProperty("createBy")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @JsonProperty("createDatetime")
    public Instant getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Instant createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("modifyBy")
    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    @JsonProperty("modifyDatetime")
    public Instant getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Instant modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    @JsonProperty("dataSet")
    public Integer getDataSet() {
        return dataSet;
    }

    public void setDataSet(Integer dataSet) {
        this.dataSet = dataSet;
    }

    @JsonProperty("isSend")
    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

}