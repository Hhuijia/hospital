package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.NursesDao;
import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Nurses;
import com.myHospital.hospital.entity.Pay;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.NurseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public class NurseServiceImp implements NurseService {
    private static final Logger log = LoggerFactory.getLogger(NurseServiceImp.class);

    @Autowired
    private NursesDao nursesDao;

    @Autowired
    private UsersDao usersDao;

    @Override
    public void addPay(Pay pay) {
        log.info("******************addPay********************");
        String strPay = String.format("%04d", new Random().nextInt(1001));
        pay.setPayId( "PAY_" + strPay + "_" + System.currentTimeMillis());
        nursesDao.addPay(pay);
    }

    @Override
    public void addGetMedicine(GetMedicine getMedicine) {
        log.info("******************addGetMedicine********************");
        String strGetM= String.format("%04d", new Random().nextInt(1001));
        getMedicine.setGetMedicineId( "GETMEDICINE_" + strGetM + "_" + System.currentTimeMillis());
        nursesDao.addGetMedicine(getMedicine);
    }

    @Override
    public Nurses findNurseByUserId(String userId) {
        log.info("******************addPay********************");
        return nursesDao.findNurseByUserId(userId);
    }

    @Override
    public List<GetMedicine> findAllGetMedicineToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());
        List<GetMedicine> getMedicines = nursesDao.findAllGetMedicineToday(currentDate);
        for (GetMedicine getMedicine : getMedicines){
            Users users = usersDao.findUserById(getMedicine.getUserId());
            String nurseName = nursesDao.findNameByNurseId(getMedicine.getNurseId());
            getMedicine.setUsers(users);
            getMedicine.setNurseName(nurseName);
        }
        return getMedicines;
    }

    @Override
    public List<Pay> finAllPay() {
        log.info("******************finAllPay********************");
        return nursesDao.finAllPay();
    }
}
