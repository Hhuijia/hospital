package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Medicine;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/11
 */
@Repository
public interface MedicineDepartmentService {

    //添加药品
    void addMedicine(Medicine medicine);

    //添加科室
    void addDepartment(Department department);

    //查询所有药品
    List<Medicine> findAllMedicine();

    //查询所有科室
    List<Department> findAllDepartment();

    //查询所有科室名称
    List<String> findAllDepartmentName();

    //删除药品
    void deleteMedicineById(String medicineId);

    //删除科室
    void deleteDepartmentById(String departmentId);

    //上传EXCEL文件
    void batchImport(String fileName, MultipartFile file) throws Exception;

    //更新科室信息
    void updateDepartment(Department department);
}
