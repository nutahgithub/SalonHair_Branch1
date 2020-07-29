package com.hairteen.hung.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BILL_INFO")
public class BillInfo {

    @Id
    @Column(name = "ID_BILL_INFO")
    private Integer idBillInfo;

    @Column(name = "ID_BILL")
    private Integer idBill;

    @Column(name = "ID_SERVICE")
    private Integer idService;

    @Column(name = "ID_EMPLOYEE")
    private Integer idEmployee;

    @Column(name = "COUNT_SERVICE")
    private Integer countService;

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

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getCountService() {
        return countService;
    }

    public void setCountService(Integer countService) {
        this.countService = countService;
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

    public Integer getIdBillInfo() {
        return idBillInfo;
    }
}
