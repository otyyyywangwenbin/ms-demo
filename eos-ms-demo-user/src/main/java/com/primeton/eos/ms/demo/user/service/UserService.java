/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Aug 28, 2019
 *******************************************************************************/

package com.primeton.eos.ms.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.eos.ms.demo.common.repository.CommonJpaRepository;
import com.primeton.eos.ms.demo.common.service.AbstractPersistentModelService;
import com.primeton.eos.ms.demo.user.model.User;
import com.primeton.eos.ms.demo.user.repository.UserRepository;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class UserService extends AbstractPersistentModelService<User> {
    @Autowired
    private UserRepository userRepo;

    protected CommonJpaRepository<User, String> getRepo() {
        return userRepo;
    }

}

/*
 * 修改历史
 * $Log$ 
 */