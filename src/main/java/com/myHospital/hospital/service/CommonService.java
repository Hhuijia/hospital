package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Repository
public interface CommonService<T> {

    //添加
    void add(Users users, T t, String type);

    //查询所有信息
    List<T> findAll(String type);

    //通过Id删除信息
    void delete(String id, String type);
}
