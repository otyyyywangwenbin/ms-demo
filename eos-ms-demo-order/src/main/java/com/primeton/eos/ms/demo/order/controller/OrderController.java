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

package com.primeton.eos.ms.demo.order.controller;

import static com.primeton.eos.ms.demo.common.util.CommonConstants.ACTION_QUERY_BY_CRITERIA;
import static com.primeton.eos.ms.demo.common.util.CommonConstants.API_PATH_PREFIX;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.eos.ms.demo.common.controller.AbstractIdentityModelController;
import com.primeton.eos.ms.demo.common.service.AbstractPersistentModelService;
import com.primeton.eos.ms.demo.order.model.Order;
import com.primeton.eos.ms.demo.order.model.OrderCriteria;
import com.primeton.eos.ms.demo.order.service.OrderService;

import io.swagger.annotations.ApiOperation;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@RestController
@RequestMapping(value = API_PATH_PREFIX + "/orders", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
public class OrderController extends AbstractIdentityModelController<Order> {
    @Autowired
    private OrderService orderSvc;

    protected AbstractPersistentModelService<Order> getSvc() {
        return orderSvc;
    }

    @ApiOperation("按条件查询")
    @RequestMapping(value = "/" + ACTION_QUERY_BY_CRITERIA, method = GET, consumes = ALL_VALUE)
    public List<Order> findByCriteria(@Validated OrderCriteria criteria) {
        return orderSvc.findByCriteria(criteria);
    }

}

/*
 * 修改历史
 * $Log$ 
 */