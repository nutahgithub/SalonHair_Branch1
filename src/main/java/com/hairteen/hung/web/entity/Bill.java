package com.hairteen.hung.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BILL")
public class Bill {

    @Id
    @Column(name = "ID_BILL")
    private Integer idBill;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CHECKIN")
    private Date dateCheckIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CHECKOUT")
    private Date dateCheckOut;

    @Column(name = "ID_CHAIR")
    private Integer idChair;

    @Column(name = "STATUS_BILL")
    private Integer statusBill;

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

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public Integer getIdChair() {
        return idChair;
    }

    public void setIdChair(Integer idChair) {
        this.idChair = idChair;
    }

    public Integer getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(Integer statusBill) {
        this.statusBill = statusBill;
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

    public Integer getIdBill() {
        return idBill;
    }
}
