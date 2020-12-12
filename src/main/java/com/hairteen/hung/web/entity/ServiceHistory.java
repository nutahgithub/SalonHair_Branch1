package com.hairteen.hung.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "T_SERVICE_HISTORY")
public class ServiceHistory {

    @Id
    @Column(name = "ID_SERVICE_HISTORY")
    private Integer idServiceHistory;

    //@Column(name = "ID_SERVICE")
    @Transient
    private Integer idService;

    @Column(name = "COUNT_IN_OUT")
    private Integer countInOut;

    @Column(name = "REASON_IN_OUT")
    private String reasonInOut;

    @Column(name = "STATUS_IN_OUT")
    private Integer statusInOut;

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

    // Co nhiu service trong service type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICE", nullable = false)
    private Service service;

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
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

    public Integer getIdServiceHistory() {
        return idServiceHistory;
    }

    public Integer getCountInOut() {
        return countInOut;
    }

    public void setCountInOut(Integer countInOut) {
        this.countInOut = countInOut;
    }

    public String getReasonInOut() {
        return reasonInOut;
    }

    public void setReasonInOut(String reasonInOut) {
        this.reasonInOut = reasonInOut;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setIdServiceHistory(Integer idServiceHistory) {
        this.idServiceHistory = idServiceHistory;
    }

    public Integer getStatusInOut() {
        return statusInOut;
    }

    public void setStatusInOut(Integer statusInOut) {
        this.statusInOut = statusInOut;
    }
}
