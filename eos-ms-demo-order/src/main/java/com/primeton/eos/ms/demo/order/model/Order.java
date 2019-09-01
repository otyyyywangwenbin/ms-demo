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

package com.primeton.eos.ms.demo.order.model;

import static com.primeton.eos.ms.demo.common.util.CommonConstants.TABLE_NAME_PREFIX;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.primeton.eos.ms.demo.common.model.PersistentModel;
import com.primeton.eos.ms.demo.common.util.ValidationGroups;
import com.primeton.eos.ms.demo.user.model.User;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "ORDER")
public class Order extends PersistentModel {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5012464519940921689L;

    @NotBlank(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private String orderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @ConvertGroup(from = ValidationGroups.Create.class, to = ValidationGroups.Association.class)
    @ConvertGroup(from = ValidationGroups.Update.class, to = ValidationGroups.Association.class)
    private User owner;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @NotEmpty(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private List<OrderItem> items;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}

/*
 * 修改历史
 * $Log$ 
 */