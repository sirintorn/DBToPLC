package com.ese.modbus.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "workorder")
public class Workorder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workorder_id_gen")
    @SequenceGenerator(name = "workorder_id_gen", sequenceName = "workorder_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "wo_id", nullable = false)
    private String woId;

    @Lob
    @Column(name = "wo_barcode", nullable = false)
    private String woBarcode;

    @Column(name = "wo_date", nullable = false)
    private Date woDate;

    @Lob
    @Column(name = "ipo", nullable = false)
    private String ipo;

    @Lob
    @Column(name = "prq_id", nullable = false)
    private String prqId;

    @Lob
    @Column(name = "project")
    private String project;

    @Column(name = "prd_completion_deadline", nullable = false)
    private Date prdCompletionDeadline;

    @Column(name = "labor_qty")
    private Integer laborQty;

    @Column(name = "actual_finish")
    private Instant actualFinish;

    @Column(name = "actual_start")
    private Instant actualStart;

    @Column(name = "fg_total_weight")
    private Double fgTotalWeight;

    @Column(name = "downgread_15")
    private Short downgread15;

    @Column(name = "downgrade_22")
    private Integer downgrade22;

    @Column(name = "downgrade_3")
    private Integer downgrade3;

    @Column(name = "downgrade_weight")
    private Double downgradeWeight;

    @Column(name = "scrap_weight")
    private Double scrapWeight;

    @Column(name = "downtime")
    private Integer downtime;

    @Column(name = "setuptime")
    private Integer setuptime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "machine_id")
    private Integer machineId;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "modify_datetime")
    private Instant modifyDatetime;

    @Column(name = "total_dataset")
    private Integer totalDataset;

    @Column(name = "transaction_set")
    private Integer transactionSet;

    @Column(name = "total_meter")
    private Double totalMeter;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("woId")
    public String getWoId() {
        return woId;
    }

    public void setWoId(String woId) {
        this.woId = woId;
    }

    @JsonProperty("woBarcode")
    public String getWoBarcode() {
        return woBarcode;
    }

    public void setWoBarcode(String woBarcode) {
        this.woBarcode = woBarcode;
    }

    @JsonProperty("woDate")
    public Date getWoDate() {
        return woDate;
    }

    public void setWoDate(Date woDate) {
        this.woDate = woDate;
    }

    @JsonProperty("ipo")
    public String getIpo() {
        return ipo;
    }

    public void setIpo(String ipo) {
        this.ipo = ipo;
    }

    @JsonProperty("prqId")
    public String getPrqId() {
        return prqId;
    }

    public void setPrqId(String prqId) {
        this.prqId = prqId;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("prdCompletionDeadline")
    public Date getPrdCompletionDeadline() {
        return prdCompletionDeadline;
    }

    public void setPrdCompletionDeadline(Date prdCompletionDeadline) {
        this.prdCompletionDeadline = prdCompletionDeadline;
    }

    @JsonProperty("laborQty")
    public Integer getLaborQty() {
        return laborQty;
    }

    public void setLaborQty(Integer laborQty) {
        this.laborQty = laborQty;
    }

    @JsonProperty("actualFinish")
    public Instant getActualFinish() {
        return actualFinish;
    }

    public void setActualFinish(Instant actualFinish) {
        this.actualFinish = actualFinish;
    }

    @JsonProperty("actualStart")
    public Instant getActualStart() {
        return actualStart;
    }

    public void setActualStart(Instant actualStart) {
        this.actualStart = actualStart;
    }

    @JsonProperty("fgTotalWeight")
    public Double getFgTotalWeight() {
        return fgTotalWeight;
    }

    public void setFgTotalWeight(Double fgTotalWeight) {
        this.fgTotalWeight = fgTotalWeight;
    }

    @JsonProperty("downgread15")
    public Short getDowngread15() {
        return downgread15;
    }

    public void setDowngread15(Short downgread15) {
        this.downgread15 = downgread15;
    }

    @JsonProperty("downgrade22")
    public Integer getDowngrade22() {
        return downgrade22;
    }

    public void setDowngrade22(Integer downgrade22) {
        this.downgrade22 = downgrade22;
    }

    @JsonProperty("downgrade3")
    public Integer getDowngrade3() {
        return downgrade3;
    }

    public void setDowngrade3(Integer downgrade3) {
        this.downgrade3 = downgrade3;
    }

    @JsonProperty("downgradeWeight")
    public Double getDowngradeWeight() {
        return downgradeWeight;
    }

    public void setDowngradeWeight(Double downgradeWeight) {
        this.downgradeWeight = downgradeWeight;
    }

    @JsonProperty("scrapWeight")
    public Double getScrapWeight() {
        return scrapWeight;
    }

    public void setScrapWeight(Double scrapWeight) {
        this.scrapWeight = scrapWeight;
    }

    @JsonProperty("downtime")
    public Integer getDowntime() {
        return downtime;
    }

    public void setDowntime(Integer downtime) {
        this.downtime = downtime;
    }

    @JsonProperty("setuptime")
    public Integer getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Integer setuptime) {
        this.setuptime = setuptime;
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

    @JsonProperty("totalDataset")
    public Integer getTotalDataset() {
        return totalDataset;
    }

    public void setTotalDataset(Integer totalDataset) {
        this.totalDataset = totalDataset;
    }

    @JsonProperty("transactionSet")
    public Integer getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Integer transactionSet) {
        this.transactionSet = transactionSet;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    @JsonProperty("machineId")
    public Integer getTMachineId() {
        return machineId;
    }

    @JsonProperty("totalMeter")
    public Double getTotalMeter() {
        return totalMeter;
    }

    public void setTotalMeter(Double totalMeter) {
        this.totalMeter = totalMeter;
    }

}