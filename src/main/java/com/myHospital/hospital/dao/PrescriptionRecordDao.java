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

    //添加病历
    @Insert("INSERT INTO record(recordId,diagnosticResult,symptom,userId,doctorId) VALUES(#{recordId},#{diagnosticResult},#{symptom},#{userId},#{doctorId})")
    int addRecord(Record record);

    //添加处方
    @Insert("INSERT INTO prescription(prescriptionId,prescriptionCount,prescriptionUnit,dosageEachTime,prescriptionDosage,prescriptionUsage,recordId,medicineId) VALUES(#{prescriptionId},#{prescriptionCount},#{prescriptionUnit},#{dosageEachTime},#{prescriptionDosage},#{prescriptionUsage},#{recordId},#{medicineId})")
    int addPrescription(Prescription prescription);

    //通过userId查询个人以往病历和处方
    @Select("SELECT * FROM record where userId = #{userId}")
    @Results({
            @Result(property = "prescription",column = "recordId",many = @Many(select = "com.myHospital.hospital.dao.PrescriptionRecordDao.findPrescriptionById"))
    })
    List<Record> findAllRecordAndPrescription(String userId);

    //通过recordId查询病人处方
    @Select("SELECT * FROM prescription where recordId = #{recordId}")
    List<Prescription> findPrescriptionById(String recordId);

}
