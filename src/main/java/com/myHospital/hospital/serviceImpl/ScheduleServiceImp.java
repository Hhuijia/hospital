package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.DoctorsDao;
import com.myHospital.hospital.dao.ScheduleDao;
import com.myHospital.hospital.entity.Schedule;
import com.myHospital.hospital.service.ScheduleService;
import com.myHospital.hospital.util.ExcelUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.myHospital.hospital.util.GetFeatureDayUtil.getFeatureDate;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Repository
public class ScheduleServiceImp implements ScheduleService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleServiceImp.class);

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private DoctorsDao doctorsDao;

    @Override
    public List<Schedule> findScheduleByDoctorId(String doctorId, String currentDay) {
        log.info("******************findScheduleByDoctorId********************");
        return scheduleDao.findScheduleByDoctorId(doctorId,currentDay);
    }

    @Override
    public List<Schedule> findScheduleCurrentDay(String currentDay, String departmentName) {
        log.info("******************findScheduleCurrentDay********************");
        return scheduleDao.findScheduleCurrentDay(currentDay,departmentName);
    }

    @Override
    public List<Schedule> findScheduleBetweenOneWeek(String doctorId) {
        log.info("******************findScheduleBetweenOneWeek********************");
        String currentDate = getFeatureDate(0).substring(0,8);
        String after7Day = getFeatureDate(7).substring(0,8);
        log.info(after7Day);
        return scheduleDao.findScheduleBetweenOneWeek(doctorId,currentDate,after7Day);
    }

    @Override
    public void batchImportSchedule(String fileName, MultipartFile file) throws Exception {
        log.info("******************batchImportDepartment********************");
        List<Schedule> schedules = new ArrayList<>();
        ExcelUtil excelUtil = new ExcelUtil();
        if (!excelUtil.validateExcel(fileName)){
            log.info("******************文件格式不正确********************");
        }else {
            Sheet sheet = excelUtil.initImport(fileName, file);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                if (row == null) { continue; }
                Schedule schedule = new Schedule();
                String departmentName = row.getCell(0).getStringCellValue();
                String doctorName = row.getCell(1).getStringCellValue();
                row.getCell(2).setCellType(CellType.STRING);
                String workDateStr =  row.getCell(2).getStringCellValue();
                Date workDate = sdf.parse(workDateStr);
                int workTime = (int) row.getCell(3).getNumericCellValue();
                int remain = (int) row.getCell(4).getNumericCellValue();

                schedule.setDepartmentName(departmentName);
                schedule.setDoctorName(doctorName);
                schedule.setRemain(remain);
                schedule.setWorkDate(new java.sql.Date(workDate.getTime()));
                schedule.setWorkTime(workTime);

                schedules.add(schedule);
            }
            for (Schedule schedule:schedules) {
                String doctorId = doctorsDao.findDoctorByName(schedule.getDepartmentName(),schedule.getDoctorName());
                schedule.setDoctorId(doctorId);
                String strSchedule = String.format("%04d", new Random().nextInt(1001));
                schedule.setScheduleId("SCHEDULE_" + strSchedule + "_" + System.currentTimeMillis());
                scheduleDao.addSchedule(schedule);
            }
        }
    }
}
