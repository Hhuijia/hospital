package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.MedicineDepartmentDao;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Medicine;
import com.myHospital.hospital.service.MedicineDepartmentService;
import com.myHospital.hospital.util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<String> findAllDepartmentName() {
        log.info("******************findAlldepartmentName********************");
        return medicineDepartmentDao.findAllDepartmentName();
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

    @Override
    public void batchImportDepartment(String fileName, MultipartFile file) throws IOException {
        log.info("******************batchImportDepartment********************");
        List<Department> departments = new ArrayList<>();
        ExcelUtil excelUtil = new ExcelUtil();
        if (!excelUtil.validateExcel(fileName)){
            log.info("******************文件格式不正确********************");
        }else {
            Sheet sheet = excelUtil.initImport(fileName, file);
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                if (row == null) { continue; }
                Department department = new Department();
                String departmentName = row.getCell(0).getStringCellValue();
                String departmentSystem = row.getCell(1).getStringCellValue();
                String departmentSymptom = row.getCell(2).getStringCellValue();
                String departmentInfo = row.getCell(3).getStringCellValue();

                department.setDepartmentName(departmentName);
                department.setDepartmentSystem(departmentSystem);
                department.setDepartmentSymptom(departmentSymptom);
                department.setDepartmentInfo(departmentInfo);

                departments.add(department);
            }
            for (Department department : departments) {
                String dName = department.getDepartmentName();
                int cnt = medicineDepartmentDao.isExitDepartmentName(dName);
                if (cnt == 0) {
                    String strDepartment = String.format("%04d", new Random().nextInt(1001));
                    department.setDepartmentId("DEPARTMENT_" + strDepartment + "_" + System.currentTimeMillis());
                    medicineDepartmentDao.addDepartment(department);
                } else {
                    medicineDepartmentDao.updateDepartment(department);
                }
            }
        }
    }

    @Override
    public void batchImportMedicine(String fileName, MultipartFile file) throws IOException {
        log.info("******************batchImportMedicine********************");
        List<Medicine> medicines = new ArrayList<>();
        ExcelUtil excelUtil = new ExcelUtil();
        if (!excelUtil.validateExcel(fileName)){
            log.info("******************文件格式不正确********************");
        }else {
            Sheet sheet = excelUtil.initImport(fileName,file);
            for (int i=0; i<sheet.getLastRowNum()+1; i++){
                Row row = sheet.getRow(i);
                if (row == null){continue;}
                Medicine medicine = new Medicine();
                String medicineName = row.getCell(0).getStringCellValue();
                BigDecimal medicinePrice = new BigDecimal(row.getCell(1).getStringCellValue());
                String medicineResidual = row.getCell(2).getStringCellValue();
                String medicineType = row.getCell(3).getStringCellValue();
                String medicineEfficacy = row.getCell(4).getStringCellValue();
                String medicineDosage = row.getCell(5).getStringCellValue();

                medicine.setMedicineName(medicineName);
                medicine.setMedicinePrice(medicinePrice);
                medicine.setMedicineResidual(medicineResidual);
                medicine.setMedicineType(medicineType);
                medicine.setMedicineEfficacy(medicineEfficacy);
                medicine.setMedicineDosage(medicineDosage);

                medicines.add(medicine);
            }
            for (Medicine medicine : medicines){
                String strMedicine = String.format("%04d", new Random().nextInt(1001));
                medicine.setMedicineId( "MEDICINE_" + strMedicine + "_" + System.currentTimeMillis());
                medicineDepartmentDao.addMedicine(medicine);
            }
        }
    }

    @Override
    public void updateDepartment(Department department) {
        log.info("******************updateDepartment********************");
        medicineDepartmentDao.updateDepartment(department);
    }
}
