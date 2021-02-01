package com.hairteen.hung.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.BillInfo;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.respository.BillInfoRespository;
import com.hairteen.hung.web.respository.BillInfoRespositoryCustom;
import com.hairteen.hung.web.respository.BillRespository;
import com.hairteen.hung.web.respository.BillRespositoryCustom;
import com.hairteen.hung.web.respository.ChairRespository;
import com.hairteen.hung.web.service.BillInfoService;

@Service
public class BillInfoServiceImpl implements BillInfoService{

    @Autowired
    private BillInfoRespositoryCustom billInfoRespositoryCustom;

    @Autowired
    private BillInfoRespository billInfoRespository;

    @Autowired
    private BillRespositoryCustom billRespositoryCustom;

    @Autowired
    private BillRespository billRespository;
    
    @Autowired
    private ChairRespository chairRespository;

    @Override
    public List<BillInfo> getAllBillInfoByBill(Integer idChair) {
        return billInfoRespositoryCustom.getAllBillInfoByBill(idChair);
    }

    @Override
    public Float totalPrice(Integer idChair) {
        List<BillInfo> billInfos = billInfoRespositoryCustom.getAllBillInfoByBill(idChair);
        Float totalPrice = 0f;
        for (BillInfo billInfo : billInfos) {
            totalPrice = totalPrice + billInfo.getCountService() * billInfo.getService().getPriceService();
        }
        return totalPrice;
    }

    @Override
    public BillInfo addBillInfoToBill(Integer idChair, String accountId, Integer idService, Integer idEmployee, Integer countService) {
        // Check exist bill
        Bill bill = billRespositoryCustom.getBillByChair(idChair);
        Integer idBillInfo = billInfoRespository.getMaxId() + 1;
        Date currentDate = new Date();

        // Chua co bill -> insert bill
        if (bill == null) {
            bill = new Bill();
            Chair chair = chairRespository.findById(idChair).get();

            // Insert bill
            bill.setIdBill(billRespository.getMaxId() + 1);
            bill.setDateCheckIn(currentDate);
            bill.setChair(chair);
            bill.setStatusBill(0);
            bill.setRegisterId(accountId);
            bill.setRegisterTsamp(currentDate);
            billRespository.save(bill);

            // Update status chair
            chair.setStatusChair(1);
            chair.setUpdateId(accountId);
            chair.setUpdateTsamp(currentDate);
            chairRespository.save(chair);

            // Insert bill info
            BillInfo billInfo = new BillInfo();
            billInfo.setBill(bill);
            billInfo.setIdBillInfo(idBillInfo);
            billInfo.setService(new com.hairteen.hung.web.entity.Service(idService));
            billInfo.setEmployee(new Employee(idEmployee));
            billInfo.setCountService(countService);
            billInfo.setRegisterTsamp(currentDate);
            billInfo.setRegisterId(accountId);
            billInfoRespository.save(billInfo);
        } else {
            BillInfo billInfo = billInfoRespositoryCustom.getExistBillInfo(idChair, idService, idEmployee);
            if (billInfo != null) {
                // Da ton tai bill info -> update so luong
                billInfo.setCountService(billInfo.getCountService() + countService);
                billInfo.setUpdateId(accountId);
                billInfo.setUpdateTsamp(currentDate);
                idBillInfo = billInfo.getIdBillInfo();
            } else {
                // Chua ton tai -> insert
                billInfo = new BillInfo();
                billInfo.setBill(bill);
                billInfo.setIdBillInfo(idBillInfo);
                billInfo.setService(new com.hairteen.hung.web.entity.Service(idService));
                billInfo.setEmployee(new Employee(idEmployee));
                billInfo.setCountService(countService);
                billInfo.setRegisterTsamp(currentDate);
                billInfo.setRegisterId(accountId);
            }
            billInfoRespository.save(billInfo);
        }
        return billInfoRespository.findById(idBillInfo).get();
    }

    @Override
    public List<BillInfo> getAllBillInfoByBillId(Integer idBill) {
        return billInfoRespositoryCustom.getAllBillInfoByBillId(idBill);
    }

    @Override
    public Float totalPriceByIdBill(Integer idBill) {
        List<BillInfo> billInfos = billInfoRespositoryCustom.getAllBillInfoByBillId(idBill);
        Float totalPrice = 0f;
        for (BillInfo billInfo : billInfos) {
            totalPrice = totalPrice + billInfo.getCountService() * billInfo.getService().getPriceService();
        }
        return totalPrice;
    }

}
