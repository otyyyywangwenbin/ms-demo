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
 * Created on Aug 29, 2019
 *******************************************************************************/

package com.primeton.eos.ms.demo.common.model;

import static com.primeton.eos.ms.demo.common.exception.CommonErrorCode.MODEL_CLONE_NOT_SUPPORTED;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import com.primeton.eos.ms.demo.common.util.ValidationGroups;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@MappedSuperclass
public abstract class IdentityModel implements Serializable, Cloneable {
    private static final long serialVersionUID = -3308019559908832962L;

    @Id
    // @Null(groups = ValidationGroups.Create.class)
    @NotBlank(groups = { ValidationGroups.Update.class, ValidationGroups.Association.class })
    private String id;

    public IdentityModel() {
    }

    public IdentityModel(String id) {
        setId(id);
    }

    public IdentityModel clone() {
        try {
            return (IdentityModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw MODEL_CLONE_NOT_SUPPORTED.runtimeException(getClass().getName());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        if (StringUtils.isBlank(id)) {
            this.id = UUID.randomUUID().toString();
        }
    }
}

/*
 * 修改历史
 * $Log$ 
 */