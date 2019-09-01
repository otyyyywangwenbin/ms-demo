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
 * Created on Aug 30, 2019
 *******************************************************************************/

package com.primeton.eos.ms.demo.order.model;

import java.io.Serializable;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class OrderCriteria implements Serializable {
    private static final long serialVersionUID = 2594493326754690159L;

    private String orderNo;

    private String ownerId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}

/*
 * 修改历史
 * $Log$ 
 */