package com.ese.modbus.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "stakeholder")
public class Stakeholder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stakeholder_id_gen")
    @SequenceGenerator(name = "stakeholder_id_gen", sequenceName = "stakeholder_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "department")
    private String department;

    @Lob
    @Column(name = "username", nullable = false)
    private String username;

    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "email")
    private String email;

    @Column(name = "is_valid")
    private Boolean isValid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private Stakeholder createBy;

    @Column(name = "create_datetime")
    private Instant createDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_by")
    private Stakeholder modifyBy;

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

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("isValid")
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @JsonProperty("createBy")
    public Stakeholder getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Stakeholder createBy) {
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
    public Stakeholder getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Stakeholder modifyBy) {
        this.modifyBy = modifyBy;
    }

    @JsonProperty("modifyDatetime")
    public Instant getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Instant modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

}