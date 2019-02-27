package com.myHospital.hospital.util;

import com.myHospital.hospital.entity.Users;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/18/2019
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5"; //基础散列算法
    public static final int HASH_ITERATIONS = 2; //自定义散列次数

    public void encryptPassword(Users user){
//        将username+salt作为salt因子加入散列算法，密码组成为password+username+salt
        String salt1 = user.getUserName();
        String salt2 = randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getUserPwd(), salt1+salt2, HASH_ITERATIONS).toHex();
        user.setUserPwd(newPassword);
        user.setSalt(salt2);
    }
}
