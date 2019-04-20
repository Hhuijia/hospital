package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Repository
public interface CommonService<T> {

    //添加
    void add(Users users, T t, List<String> roleIds, String type);

    //查询所有信息
    List<T> findAll(String type);

    //通过Id删除信息
    void delete(String id, String type);

    //上传EXCEL文件
    void batchImport(String fileName, MultipartFile file, String type) throws Exception;

    //查询某用户的角色名
    List<String> findRoleName(String userId);

    //获取用户信息
    Object findMyMsg(String userId, String type);

}
