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

    //通过recordId查询病历记录
    @Select("SELECT * FROM record where recordId = #{recordId}")
    Record findRecordById(String recordId);

    //通过recordId查询病人处方
    @Select("SELECT * FROM prescription where recordId = #{recordId}")
    @Results({
            @Result(property = "medicine",column = "medicineId",one = @One(select = "com.myHospital.hospital.dao.MedicineDepartmentDao.findMedicineById"))
    })
    List<Prescription> findPrescriptionById(String recordId);

    //通过userId未缴费查询病人病历
    @Select("SELECT * FROM record where userId = #{userId} AND recordStatus=1")
    @Results({
            @Result(property = "prescription",column = "recordId",many = @Many(select = "com.myHospital.hospital.dao.PrescriptionRecordDao.findPrescriptionById"))
    })
    List<Record> findRecordAndPrescriptionWithoutPay(String userId);

    //查询所有已缴费病历
    @Select("SELECT * FROM record WHERE recordStatus=2")
    List<Record> findAllRecordAndPrescriptionWithPay();

    //更新RecordStatus
    @Update("UPDATE record SET recordStatus=#{recordStatus} WHERE recordId=#{recordId}")
    int updateRecordStatusById(@Param("recordStatus") int recordStatus, @Param("recordId") String recordId);

}
