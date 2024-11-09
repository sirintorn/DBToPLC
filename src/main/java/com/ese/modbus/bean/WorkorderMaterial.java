package com.ese.modbus.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "workorder_material")
public class WorkorderMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workorder_material_id_gen")
    @SequenceGenerator(name = "workorder_material_id_gen", sequenceName = "workorder_material_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workorder_id", nullable = false)
    private Workorder workorder;

    @Lob
    @Column(name = "specoil_barcode")
    private String specoilBarcode;

    @Lob
    @Column(name = "specoil_code", nullable = false)
    private String specoilCode;

    @Lob
    @Column(name = "specoil_description")
    private String specoilDescription;

    @Lob
    @Column(name = "lot")
    private String lot;

    @Lob
    @Column(name = "coil_no", nullable = false)
    private String coilNo;

    @Column(name = "coil_weight_start")
    private Double coilWeightStart;

    @Column(name = "coil_weight_start_actual")
    private Double coilWeightStartActual;

    @Column(name = "coil_weight_estimate")
    private Double coilWeightEstimate;

    @Column(name = "coil_weight_actual")
    private Double coilWeightActual;

    @Column(name = "coil_weight_remaining")
    private Double coilWeightRemaining;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "modify_datetime")
    private Instant modifyDatetime;

    @Column(name = "remark")
    private String remark;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("workorder")
    public Workorder getWorkorder() {
        return workorder;
    }

    public void setWorkorder(Workorder workorder) {
        this.workorder = workorder;
    }

    @JsonProperty("specoilBarcode")
    public String getSpecoilBarcode() {
        return specoilBarcode;
    }

    public void setSpecoilBarcode(String specoilBarcode) {
        this.specoilBarcode = specoilBarcode;
    }

    @JsonProperty("specoilCode")
    public String getSpecoilCode() {
        return specoilCode;
    }

    public void setSpecoilCode(String specoilCode) {
        this.specoilCode = specoilCode;
    }

    @JsonProperty("specoilDescription")
    public String getSpecoilDescription() {
        return specoilDescription;
    }

    public void setSpecoilDescription(String specoilDescription) {
        this.specoilDescription = specoilDescription;
    }

    @JsonProperty("lot")
    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @JsonProperty("coilNo")
    public String getCoilNo() {
        return coilNo;
    }

    public void setCoilNo(String coilNo) {
        this.coilNo = coilNo;
    }

    @JsonProperty("coilWeightStart")
    public Double getCoilWeightStart() {
        return coilWeightStart;
    }

    public void setCoilWeightStart(Double coilWeightStart) {
        this.coilWeightStart = coilWeightStart;
    }

    @JsonProperty("coilWeightStartActual")
    public Double getCoilWeightStartActual() {
        return coilWeightStartActual;
    }

    public void setCoilWeightStartActual(Double coilWeightStartActual) {
        this.coilWeightStartActual = coilWeightStartActual;
    }

    @JsonProperty("coilWeightEstimate")
    public Double getCoilWeightEstimate() {
        return coilWeightEstimate;
    }

    public void setCoilWeightEstimate(Double coilWeightEstimate) {
        this.coilWeightEstimate = coilWeightEstimate;
    }

    @JsonProperty("coilWeightActual")
    public Double getCoilWeightActual() {
        return coilWeightActual;
    }

    public void setCoilWeightActual(Double coilWeightActual) {
        this.coilWeightActual = coilWeightActual;
    }

    @JsonProperty("coilWeightRemaining")
    public Double getCoilWeightRemaining() {
        return coilWeightRemaining;
    }

    public void setCoilWeightRemaining(Double coilWeightRemaining) {
        this.coilWeightRemaining = coilWeightRemaining;
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

    @JsonProperty("createDatetime")
    public Instant getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Instant createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("modifyDatetime")
    public Instant getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Instant modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}