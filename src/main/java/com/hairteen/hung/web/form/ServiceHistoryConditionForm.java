package com.hairteen.hung.web.form;

public class ServiceHistoryConditionForm {

    private String yearHistory;
    private String monthHistory;
    private Integer serviceTypeId;
    private Integer serviceId;
    public String getYearHistory() {
        return yearHistory;
    }
    public void setYearHistory(String yearHistory) {
        this.yearHistory = yearHistory;
    }
    public String getMonthHistory() {
        return monthHistory;
    }
    public void setMonthHistory(String monthHistory) {
        this.monthHistory = monthHistory;
    }
    public Integer getServiceTypeId() {
        return serviceTypeId;
    }
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
