package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Nurses;
import com.myHospital.hospital.entity.Pay;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NursesDao {

    //添加护士
    @Insert("INSERT INTO nurses(nurseId,nurseName,nurseTitle,nursePlace,userId) VALUES(#{nurseId},#{nurseName},#{nurseTitle},#{nursePlace},#{userId})")
    void addNurse(Nurses nurses);

    //添加缴费记录
    @Insert("INSERT INTO paym(payId,payCount,recordId,userId,nurseId) VALUES(#{payId},#{payCount},#{recordId},#{userId},#{nurseId})")
    void addPay(Pay pay);

    //添加取药记录
    @Insert("INSERT INTO getmedicine(getMedicineId,recordId,userId,nurseId) VALUES(#{getMedicineId},#{recordId},#{userId},#{nurseId})")
    void addGetMedicine(GetMedicine getMedicine);

//    //通过nurseId更新护士信息
//    @Update("UPDATE doctors SET doctorName=#{doctorName},doctorSex=#{doctorSex},doctorAge=#{doctorAge},doctorPhone=#{doctorPhone},departmentNo=#{departmentNo},doctorTitle=#{doctorTitle},doctorProfession=#{doctorProfession},doctorMedicalServiceLife=#{doctorMedicalServiceLife},doctorIntroduction=#{doctorIntroduction} WHERE userNo=#{userNo}")
//    int updateUserByNo(Doctors doctors);

    //通过nurseId查询对应在用户表的信息
    @Select("SELECT userId FROM nurses WHERE nurseId = #{nurseId}")
    String findUserIdById(String nurseId);

    //通过userId查询对应在护士表的信息
    @Select("SELECT * FROM nurses WHERE userId = #{userId}")
    Nurses findNurseByUserId(String userId);

    //查询所有护士信息
    @Select("SELECT * FROM nurses")
    @Results({
            @Result(property = "users",column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Nurses> findAllNurse();

    //按缴费顺序输出缴费记录
    @Select("SELECT * FROM paym WHERE recordId IN (SELECT recordId FROM record WHERE recordStatus=2) ORDER BY payCreatedTime")
    List<Pay> finAllPay();

    //通过nurseId删除护士信息
    @Delete("DELETE FROM nurses WHERE nurseId = #{nurseId}")
    void deleteNurseById(String nurseId);
}
