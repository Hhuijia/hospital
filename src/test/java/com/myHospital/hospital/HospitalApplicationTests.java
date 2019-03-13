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
		final List<Appointment> appointments = recordService.findAllAppointment();
		log.info("[{}]",appointments);
		final List<Users> users = usersService.checkAllUser();
		log.info("[{}]",users);
	}
}

