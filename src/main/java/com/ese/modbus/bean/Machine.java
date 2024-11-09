package com.ese.modbus.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "machine")
public class Machine {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "ip_address")
    private String ipAddress;

    @Lob
    @Column(name = "mount_path")
    private String mountPath;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @Column(name = "modify_datetime")
    private Instant modifyDatetime;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("ipAddress")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @JsonProperty("mountPaht")
    public String getMountPath() {
        return mountPath;
    }

    public void setMountPath(String mountPath) {
        this.mountPath = mountPath;
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

}