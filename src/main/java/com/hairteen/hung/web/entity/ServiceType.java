package com.hairteen.hung.web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_SERVICE_TYPE")
public class ServiceType {

    @Id
    @Column(name = "ID_SERVICE_TYPE")
    private Integer idServiceType;

    @Column(name = "NAME_SERVICE_TYPE")
    private String nameServiceType;

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
    
    @OneToMany(mappedBy = "serviceType")
    private Collection<Service> services;

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public void setIdServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }

    public String getNameServiceType() {
        return nameServiceType;
    }

    public void setNameServiceType(String nameServiceType) {
        this.nameServiceType = nameServiceType;
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

    public Integer getIdServiceType() {
        return idServiceType;
    }

    public ServiceType(Integer idServiceType) {
        super();
        this.idServiceType = idServiceType;
    }
    public ServiceType() {}

    public ServiceType(Integer idServiceType, String nameServiceType, String registerId, Date registerTsamp,
            String updateId, Date updateTsamp, String deleteId, Date deleteTsamp, Collection<Service> services) {
        super();
        this.idServiceType = idServiceType;
        this.nameServiceType = nameServiceType;
        this.registerId = registerId;
        this.registerTsamp = registerTsamp;
        this.updateId = updateId;
        this.updateTsamp = updateTsamp;
        this.deleteId = deleteId;
        this.deleteTsamp = deleteTsamp;
        this.services = services;
    }
}
