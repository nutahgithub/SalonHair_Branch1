package com.hairteen.hung.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "ID_EMPLOYEE")
    private Integer idEmployee;

    @Column(name = "NAME_EMPLOYEE")
    private String nameEmployee;

    @Column(name = "SEX")
    private Integer sex;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BASIC_SALARY")
    private Float basicSalary;

    @Column(name = "REGISTER_ID")
    private String registerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTER_TSTAMP")
    private Date registerTsamp;

    @Column(name = "UPDATE_ID")
    private String updateId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TSTAMP")
    private Date updateTsamp;

    @Column(name = "DELETE_ID")
    private String deleteId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETE_TSTAMP")
    private Date deleteTsamp;

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public Date getRegisterTsamp() {
        return registerTsamp;
    }

    public void setRegisterTsamp(Date registerTsamp) {
        this.registerTsamp = registerTsamp;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTsamp() {
        return updateTsamp;
    }

    public void setUpdateTsamp(Date updateTsamp) {
        this.updateTsamp = updateTsamp;
    }

    public String getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(String deleteId) {
        this.deleteId = deleteId;
    }

    public Date getDeleteTsamp() {
        return deleteTsamp;
    }

    public void setDeleteTsamp(Date deleteTsamp) {
        this.deleteTsamp = deleteTsamp;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public Employee(Integer idEmployee, String nameEmployee, Integer sex, Date birthday, String address, String phone,
            String email, Float basicSalary, String registerId, Date registerTsamp, String updateId, Date updateTsamp,
            String deleteId, Date deleteTsamp) {
        super();
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.basicSalary = basicSalary;
        this.registerId = registerId;
        this.registerTsamp = registerTsamp;
        this.updateId = updateId;
        this.updateTsamp = updateTsamp;
        this.deleteId = deleteId;
        this.deleteTsamp = deleteTsamp;
    }

    public Employee() {}
    
}
