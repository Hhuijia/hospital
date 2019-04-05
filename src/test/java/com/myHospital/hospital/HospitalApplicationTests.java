package com.myHospital.hospital;

import com.myHospital.hospital.entity.*;
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

import java.math.BigDecimal;
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
		String prescriptions = "[[\"MEDICINE_0947_1554423430028\",\"1\",\"瓶\",\"1\",\"每日1次\",\"外用\"],[\"MEDICINE_0907_1554423429897\",\"22\",\"袋\",\"2\",\"每日1次\",\"外用\"]]";
		List<Prescription> prescription = new ArrayList<>();
		prescriptions = prescriptions.substring(prescriptions.indexOf("[")+1,prescriptions.lastIndexOf("]"));
		String[] str = prescriptions.split("]");
		for (String string : str){
			Prescription pre = new Prescription();
			String[] prePart = string.substring(string.indexOf("[")+1).split(",");
			pre.setMedicineId(prePart[0].substring(1,prePart[0].length()-1));
			pre.setPrescriptionCount(Integer.parseInt(prePart[1].substring(1,prePart[1].length()-1)));
			pre.setPrescriptionUnit(prePart[2].substring(1,prePart[2].length()-1));
			pre.setDosageEachTime(BigDecimal.valueOf(Integer.parseInt(prePart[3].substring(1,prePart[3].length()-1))));
			pre.setPrescriptionDosage(prePart[4].substring(1,prePart[4].length()-1));
			pre.setPrescriptionUsage(prePart[5].substring(1,prePart[5].length()-1));

			prescription.add(pre);
		}
		log.info("[{}]",prescription);
	}
}

