package com.myHospital.hospital;

import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(HospitalApplicationTests.class);

	@Autowired
	private UsersService usersService;

	@Test
	public void contextLoads() {
		final Users users = usersService.findUserByIDNum("454979516");
		log.info("[result] - [{}]",users.getUserName());
	}
}

