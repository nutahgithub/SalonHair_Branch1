package com.hairteen.hung.web.entity;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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

    @Transient
    private Integer idChair;

    @Column(name = "STATUS_BILL")
    private Integer statusBill;

    @Column(name = "DIS_COUNT")
    private Integer disCount;

    @Column(name = "TOTAL_MONEY")
    private Float totalMoney;

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
    
    @OneToMany(mappedBy = "bill")
    private Collection<BillInfo> billInfos;
    
    // Co nhiu chair trong bill.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CHAIR", nullable = false)
    private Chair chair;

    @Transient
    private String dateCheckOutFormat;

    @Transient
    private String dateCheckInFormat;

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

    public Collection<BillInfo> getBillInfos() {
        return billInfos;
    }

    public void setBillInfos(Collection<BillInfo> billInfos) {
        this.billInfos = billInfos;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public Integer getDisCount() {
        return disCount;
    }

    public void setDisCount(Integer disCount) {
        this.disCount = disCount;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDateCheckOutFormat() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return this.dateCheckOut != null?sm.format(this.dateCheckOut):"";
    }

    public void setDateCheckOutFormat(String dateCheckOutFormat) {
        this.dateCheckOutFormat = dateCheckOutFormat;
    }

    public String getDateCheckInFormat() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return this.dateCheckIn != null?sm.format(this.dateCheckIn):"";
    }

    public void setDateCheckInFormat(String dateCheckInFormat) {
        this.dateCheckInFormat = dateCheckInFormat;
    }
}
