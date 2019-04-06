package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Nurses;
import com.myHospital.hospital.entity.Pay;
import com.myHospital.hospital.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public interface NurseService {

    //添加缴费记录
    void addPay(Pay pay);

    //添加取药记录
    void addGetMedicine(GetMedicine getMedicine);

    //通过userId查询对应在护士表的信息
    Nurses findNurseByUserId(String userId);

    //按缴费顺序输出缴费记录
    List<Pay> finAllPay();
}
