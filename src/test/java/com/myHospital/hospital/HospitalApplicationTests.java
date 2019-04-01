package com.myHospital.hospital;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.MedicineDepartmentService;
import com.myHospital.hospital.service.RecordService;
import com.myHospital.hospital.service.RolePermissionService;
import com.myHospital.hospital.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(HospitalApplicationTests.class);

	@Autowired
	private RecordService recordService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private RolePermissionService rolePermissionService;
	@Test
	public void contextLoads() {
		ArrayList<String> featureDaysList = new ArrayList<>();
		for (int i = 0; i <7; i++) {
			featureDaysList.add(getFeatureDate(i));
		}
		for (String date : featureDaysList) {
			System.out.println(date);
		}
	}
	public static String getFeatureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd EEE");
		return format.format(today);
	}
}

