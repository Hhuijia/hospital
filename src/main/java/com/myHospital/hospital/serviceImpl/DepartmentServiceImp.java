package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.DepartmentDao;
import com.myHospital.hospital.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public class DepartmentServiceImp implements DepartmentService {
    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImp.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<String> findAlldepartmentName() {
        log.info("******************findAlldepartmentName********************");
        return departmentDao.findAlldepartmentName();
    }

    @Override
    public String findDepartmentIdByName(String departmentName) {
        log.info("******************findDepartmentIdByName********************");
        return departmentDao.findDepartmentIdByName(departmentName);
    }
}
