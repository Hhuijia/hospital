package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Prescription;
import com.myHospital.hospital.entity.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/17
 */
@Mapper
public interface PrescriptionRecordDao {

    //通过userId查询个人以往病历和处方
    @Select("SELECT * FROM record where userId = #{userId}")
    @Results({
            @Result(property = "prescription",column = "prescriptionId",one = @One(select = "com.myHospital.hospital.dao.PrescriptionRecordDao.findPrescriptionById"))
    })
    List<Record> findAllRecordAndPrescription(String userId);

    //通过prescriptionId查询病人处方
    @Select("SELECT * FROM prescription where prescriptionId = #{prescriptionId}")
    Prescription findPrescriptionById(String prescriptionId);

}
