package com.hairteen.hung.web.form;

public class ServiceForm {

    private Integer idService;
    private String nameService;
    private Integer idServiceType;
    private String priceService;
    private Integer countIn;
    private Integer countOut;
    private Integer countRemain;
    public Integer getIdService() {
        return idService;
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
    public Integer getIdServiceType() {
        return idServiceType;
    }
    public void setIdServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }
    public String getPriceService() {
        return priceService;
    }
    public void setPriceService(String priceService) {
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
}
