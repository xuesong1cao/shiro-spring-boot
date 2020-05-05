package com.service.Impl;

import com.dao.ManagerMapper;
import com.entity.Manager;
import com.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    @Override
    public Manager getManager(String username) {
        return managerMapper.getManager(username);
    }
}
