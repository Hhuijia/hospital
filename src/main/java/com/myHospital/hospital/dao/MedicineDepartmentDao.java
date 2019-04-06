package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Medicine;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/11
 */
@Mapper
public interface MedicineDepartmentDao {

    //添加药品
    @Insert("INSERT INTO medicine(medicineId,medicineName,medicineUnit,medicinePrice,medicineResidual,medicineType,medicineDosage,companyName) VALUES(#{medicineId},#{medicineName},#{medicineUnit},#{medicinePrice},#{medicineResidual},#{medicineType},#{medicineDosage},#{companyName})")
    void addMedicine(Medicine medicine);

    //添加科室
    @Insert("INSERT INTO department(departmentId,departmentName,departmentSystem,departmentInfo,departmentSymptom) VALUES(#{departmentId},#{departmentName},#{departmentSystem},#{departmentInfo},#{departmentSymptom})")
    void addDepartment(Department department);

    //查询所有药品
    @Select("SELECT * FROM medicine")
    List<Medicine> findAllMedicine();

    //查询所有科室
    @Select("SELECT * FROM department")
    List<Department> findAllDepartment();

    //查询所有科室名称
    @Select("SELECT departmentName FROM department")
    List<String> findAllDepartmentName();

    //查询所有科室科系
    @Select("SELECT departmentSystem FROM department GROUP BY departmentSystem")
    List<String> findAllDepartmentSystem();

    //查询是否存在科室
    @Select("SELECT count(*) FROM department WHERE departmentName = #{departmentName}")
    int isExitDepartmentName(String departmentName);

    //通过departmentName查找科室
    @Select("SELECT * FROM department WHERE departmentName = #{departmentName}")
    Department findDepartmentByName(String departmentName);

    //通过departmentId查找科室
    @Select("SELECT * FROM department WHERE departmentId = #{departmentId}")
    Department findDepartmentById(String departmentId);

    //通过ID查找药品
    @Select("SELECT * FROM medicine WHERE medicineId = #{medicineId}")
    Medicine findMedicineById(String medicineId);

    //更新科室信息
    @Update("UPDATE department SET departmentSystem = #{departmentSystem}, departmentInfo = #{departmentInfo}, departmentSymptom = #{departmentSymptom} WHERE departmentName = #{departmentName}")
    void updateDepartment(Department department);

    //更新药品库存
    @Update("UPDATE medicine SET medicineResidual = #{medicineResidual} WHERE medicineId = #{medicineId}")
    void updateMedicineResidual(@Param("medicineResidual") int medicineResidual, @Param("medicineId") String medicineId);

    //通过medicineId删除药品
    @Delete("DELETE FROM medicine WHERE medicineId = #{medicineId}")
    void deleteMedicineById(String medicineId);

    //通过departmentId删除科室
    @Delete("DELETE FROM department WHERE departmentId = #{departmentId}")
    void deleteDepartmentById(String departmentId);
}
