package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
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

    //添加用户-病患
    @Insert("INSERT INTO users VALUES(#{userId},#{userName},#{userPwd},#{userSex},#{userAge},#{userBirth},#{userIDNum},#{userPhone},#{userAddress},,,#{salt},null,null)")
    void addUser(Users user);

    //添加用户-医生
    @Insert("INSERT INTO users VALUES(#{userId},#{userName},#{userPwd},#{userSex},#{userAge},#{userBirth},#{userIDNum},#{userPhone},#{userAddress},,,#{salt},#{doctorId},null)")
    void addDoctor(Users user);

    //添加用户-护士
    @Insert("INSERT INTO users VALUES(#{userId},#{userName},#{userPwd},#{userSex},#{userAge},#{userBirth},#{userIDNum},#{userPhone},#{userAddress},,,#{salt},null,#{nurseId})")
    void addNurse(Users user);

//    //通过userNo更新用户信息
//    @Update("UPDATE users SET userName=#{userName},userSex=#{userSex},userAge=#{userAge},userAddress=#{userAddress} WHERE userNo=#{userId}")
//    int updateUserById(Users user);
//
//    //通过userId查询用户信息
//    @Select("SELECT * FROM users WHERE userNo = #{userId}")
//    Users findUserById(String userId);
//
//    //通过userName查询用户信息
//    @Select("SELECT * FROM users WHERE userName = #{userName}")
//    Users findUserByName(String userName);

    //通过userIDNum查询用户信息
    @Select("SELECT * FROM users WHERE userIDNum = #{userIDNum}")
    Users findUserByIDNum(String userIDNum);

    //通过userIDNum查询用户角色
    @Select("SELECT * FROM role WHERE roleId in (SELECT roleId FROM users u, userRole ur WHERE userIDNum = #{userIDNum} AND u.userId = ur.userId)")
    List<Role> findRoleByIDNum(String userIDNum);

    //通过userIDNum查询用户角色
    @Select("SELECT * FROM permission WHERE permissionId in (SELECT permissionId FROM RolePermission WHERE roleId = #{roleId})")
    List<Permission> findPermissionByRoleId(String roleId);

    //查询所有用户信息
    @Select("SELECT * FROM users")
    List<Users> findAllUser();


}
