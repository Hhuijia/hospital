package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.MedicineDepartmentDao;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Medicine;
import com.myHospital.hospital.service.MedicineDepartmentService;
import com.myHospital.hospital.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
    public void batchImport(String fileName, MultipartFile file) throws Exception {
        List<Department> departments = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook workbook;
        ExcelUtil excelUtil = new ExcelUtil();
        String str = excelUtil.validateExcel(fileName);
        if (str.equals("notExcel")){
            log.info("******************文件格式不正确********************");
            return;
        } else if (str.equals("isExcel2003")){
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);

        for (int i=2;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            if (row == null){continue;}
            Department department = new Department();
            row.getCell(0).setCellType(CellType.STRING);
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(2).setCellType(CellType.STRING);
            row.getCell(3).setCellType(CellType.STRING);
            String departmentName = row.getCell(0).getStringCellValue();
            String departmentSystem = row.getCell(1).getStringCellValue();
            String departmentInfo = row.getCell(2).getStringCellValue();
            String departmentSymptom = row.getCell(3).getStringCellValue();
            if (departmentName == null || departmentName.isEmpty()){
                log.info("******第[{}]行科室名未填写********",i+1);
                return;
            }
            if (departmentSystem == null || departmentSystem.isEmpty()){
                log.info("******第[{}]行所属科系未填写********",i+1);
                return;
            }
            if (departmentSymptom == null || departmentSymptom.isEmpty()){
                log.info("******第[{}]行主要病症未填写********",i+1);
                return;
            }
            department.setDepartmentName(departmentName);
            department.setDepartmentSystem(departmentSystem);
            department.setDepartmentSymptom(departmentSymptom);
            department.setDepartmentInfo(departmentInfo);

            departments.add(department);
        }
        for (Department department : departments){
            String dName = department.getDepartmentName();
            int cnt = medicineDepartmentDao.isExitDepartmentName(dName);
            if (cnt==0){
                String strDepartment = String.format("%04d", new Random().nextInt(1001));
                department.setDepartmentId("DEPARTMENT_" + strDepartment + "_" + System.currentTimeMillis());
                medicineDepartmentDao.addDepartment(department);
            }
            else {
                medicineDepartmentDao.updateDepartment(department);
            }
        }

    }

    @Override
    public void updateDepartment(Department department) {
        log.info("******************updateDepartment********************");
        medicineDepartmentDao.updateDepartment(department);
    }
}
