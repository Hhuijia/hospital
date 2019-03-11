package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.MedicineDepartmentDao;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Medicine;
import com.myHospital.hospital.service.MedicineDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/3/11
 */
@Repository
public class MedicineDepartmentServiceImp implements MedicineDepartmentService {
    private static final Logger log = LoggerFactory.getLogger(MedicineDepartmentServiceImp.class);

    @Autowired
    private MedicineDepartmentDao medicineDepartmentDao;

    @Override
    public void addMedicine(Medicine medicine) {
        log.info("******************addMedicine********************");
        String strMedicine = String.format("%04d", new Random().nextInt(1001));
        medicine.setMedicineId( "MEDICINE_" + strMedicine + "_" + System.currentTimeMillis());
        medicineDepartmentDao.addMedicine(medicine);
    }

    @Override
    public void addDepartment(Department department) {
        log.info("******************addDepartment********************");
        String strDepartment = String.format("%04d", new Random().nextInt(1001));
        department.setDepartmentId("DEPARTMENT_" + strDepartment + "_" + System.currentTimeMillis());
        medicineDepartmentDao.addDepartment(department);
    }

    @Override
    public List<Medicine> findAllMedicine() {
        log.info("******************findAllMedicine********************");
        return medicineDepartmentDao.findAllMedicine();
    }

    @Override
    public List<Department> findAllDepartment() {
        log.info("******************findAllDepartment********************");
        return medicineDepartmentDao.findAllDepartment();
    }

    @Override
    public void deleteMedicineById(String medicineId) {
        log.info("******************deleteMedicineById********************");
        medicineDepartmentDao.deleteMedicineById(medicineId);
    }

    @Override
    public void deleteDepartmentById(String departmentId) {
        log.info("******************deleteDepartmentById********************");
        medicineDepartmentDao.deleteDepartmentById(departmentId);
    }
}
