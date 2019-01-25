package com.myHospital.hospital.util;

import com.myHospital.hospital.entity.Users;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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
        //将username作为salt因子加入散列算法
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getUserPwd(),
                ByteSource.Util.bytes(user.getUserIDNum()),HASH_ITERATIONS).toHex();
        user.setUserPwd(newPassword);
    }
}
