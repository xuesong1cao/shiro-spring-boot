package com.dao;

import com.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//加了这个注解，表明这个类是mybatis的一个mapper接口
@Mapper
@Repository
public interface ManagerMapper {

    Manager getManager(String username);

}
