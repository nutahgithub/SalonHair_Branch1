package com.hairteen.hung.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_SERVICE")
public class Service {

    @Id
    @Column(name = "ID_SERVICE")
    private Integer idService;

    @Column(name = "NAME_SERVICE")
    private String nameService;

    @Column(name = "ID_SERVICE_TYPE")
    private Integer idServiceType;

    @Column(name = "PRICE_SERVICE")
    private Float priceService;

    @Column(name = "COUNT_IN")
    private Integer countIn;

    @Column(name = "COUNT_OUT")
    private Integer countOut;

    @Column(name = "COUT_REMAIN")
    private Integer countRemain;

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

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Integer getIdServiceType() {
        return idServiceType;
    }

    public void setIdServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }

    public Float getPriceService() {
        return priceService;
    }

    public void setPriceService(Float priceService) {
        this.priceService = priceService;
    }

    public Integer getCountIn() {
        return countIn;
    }

    public void setCountIn(Integer countIn) {
        this.countIn = countIn;
    }

    public Integer getCountOut() {
        return countOut;
    }

    public void setCountOut(Integer countOut) {
        this.countOut = countOut;
    }

    public Integer getCountRemain() {
        return countRemain;
    }

    public void setCountRemain(Integer countRemain) {
        this.countRemain = countRemain;
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

    public Integer getIdService() {
        return idService;
    }
}
