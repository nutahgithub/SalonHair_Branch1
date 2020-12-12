package com.hairteen.hung.web.entity;

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
@Table(name = "T_SERVICE")
public class Service {

    @Id
    @Column(name = "ID_SERVICE")
    private Integer idService;

    @Column(name = "NAME_SERVICE")
    private String nameService;

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

    // Co nhiu service trong service type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICE_TYPE", nullable = false)
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service")
    private Collection<ServiceHistory> serviceHistorys;

    @Transient
    private Integer idServiceType;

    public Integer getIdServiceType() {
        return idServiceType;
    }

    public void setIdServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
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

    public Collection<ServiceHistory> getServiceHistorys() {
        return serviceHistorys;
    }

    public void setServiceHistorys(Collection<ServiceHistory> serviceHistorys) {
        this.serviceHistorys = serviceHistorys;
    }

    public Service(Integer idService, String nameService, Float priceService, Integer countIn, Integer countOut,
            Integer countRemain, String registerId, Date registerTsamp, String updateId, Date updateTsamp,
            String deleteId, Date deleteTsamp, ServiceType serviceType) {
        super();
        this.idService = idService;
        this.nameService = nameService;
        this.priceService = priceService;
        this.countIn = countIn;
        this.countOut = countOut;
        this.countRemain = countRemain;
        this.registerId = registerId;
        this.registerTsamp = registerTsamp;
        this.updateId = updateId;
        this.updateTsamp = updateTsamp;
        this.deleteId = deleteId;
        this.deleteTsamp = deleteTsamp;
        this.serviceType = serviceType;
    }

    public Service(Integer idService, String nameService, Float priceService, Integer countIn, Integer countOut,
            Integer countRemain, String registerId, Date registerTsamp, String updateId, Date updateTsamp,
            String deleteId, Date deleteTsamp, Integer idServiceType) {
        super();
        this.idService = idService;
        this.nameService = nameService;
        this.priceService = priceService;
        this.countIn = countIn;
        this.countOut = countOut;
        this.countRemain = countRemain;
        this.registerId = registerId;
        this.registerTsamp = registerTsamp;
        this.updateId = updateId;
        this.updateTsamp = updateTsamp;
        this.deleteId = deleteId;
        this.deleteTsamp = deleteTsamp;
        this.idServiceType = idServiceType;
    }

    public Service(Integer idService) {
        super();
        this.idService = idService;
    }

    public Service() {}
}
