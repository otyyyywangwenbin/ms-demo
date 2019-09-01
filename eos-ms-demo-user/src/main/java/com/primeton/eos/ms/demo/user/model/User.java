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

package com.primeton.eos.ms.demo.user.model;

import static com.primeton.eos.ms.demo.common.util.CommonConstants.TABLE_NAME_PREFIX;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.primeton.eos.ms.demo.common.model.PersistentModel;
import com.primeton.eos.ms.demo.common.util.ValidationGroups;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "USER")
public class User extends PersistentModel {

    private static final long serialVersionUID = -7195802315142091400L;

    @NotBlank(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private String name;

    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @Min(value = 10)
    @Max(value = 200)
    private Integer age;

    public User() {
        super();
    }

    public User(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}

/*
 * 修改历史
 * $Log$ 
 */