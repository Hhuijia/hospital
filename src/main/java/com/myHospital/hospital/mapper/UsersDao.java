package com.myHospital.hospital.mapper;

import com.myHospital.hospital.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersDao {
    /**
     *
     * Results:数据库字段名与实体类对应的属性名不一致时使用@Results映射来将其对应起来
     *      column为数据库字段名，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
     * ResultMap:这段@Results代码需要在多个方法用到时，为了提高代码复用性，可以为这个@Results注解设置id，然后使用@ResultMap注解来复用这段代码。
     * One:需要通过查询到的一个字段值作为参数，去执行另外一个方法来查询关联的内容，而且两者是一对一关系时使用@One注解来实现。
     * Many:如果使用@One查询到的结果是多行，会抛出TooManyResultException异常，这种时候应该使用的是@Many注解，实现一对多的查询。
     *
     *  */
//    @Results({
//            @Result(property = "userNo", column = "userNo"),
//            @Result(property = "userName", column = "userName"),
//            @Result(property = "userPwd", column = "userPwd"),
//            @Result(property = "userSex", column = "userSex"),
//            @Result(property = "userAge", column = "userAge"),
//            @Result(property = "userPhone", column = "userPhone"),
//            @Result(property = "userAddress", column = "userAddress"),
//            @Result(property = "userCreateTime", column = "userCreateTime"),
//            @Result(property = "userUpdateTime", column = "userUpdateTime"),
//    })

    //添加用户
    @Insert("INSERT INTO users VALUES(#{userNo},#{userName},#{userPwd},#{userSex},#{userAge},#{userPhone},#{userAddress})")
    void addUser(Users user);

    //通过userNo更新用户信息
    @Update("UPDATE users SET userName=#{userName},userSex=#{userSex},userAge=#{userAge},userAddress=#{userAddress} WHERE userNo=#{userNo}")
    int updateUserByNo(Users user);

    //通过查询用户信息
    @Select("SELECT * FROM users WHERE userNo = #{userNo}")
    Users findUserByNo(String userNo);

    //查询所有用户信息
    @Select("SELECT * FROM users")
    List<Users> findAllUser();
}
