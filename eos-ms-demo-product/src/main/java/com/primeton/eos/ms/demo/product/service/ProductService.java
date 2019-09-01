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

package com.primeton.eos.ms.demo.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.eos.ms.demo.common.repository.CommonJpaRepository;
import com.primeton.eos.ms.demo.common.service.AbstractPersistentModelService;
import com.primeton.eos.ms.demo.product.model.Product;
import com.primeton.eos.ms.demo.product.repository.ProductRepository;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class ProductService extends AbstractPersistentModelService<Product> {
    @Autowired
    private ProductRepository prodRepo;

    protected CommonJpaRepository<Product, String> getRepo() {
        return prodRepo;
    }

}

/*
 * 修改历史
 * $Log$ 
 */