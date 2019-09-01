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

package com.primeton.eos.ms.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.eos.ms.demo.order.model.Order;
import com.primeton.eos.ms.demo.order.model.OrderItem;
import com.primeton.eos.ms.demo.product.model.Product;
import com.primeton.eos.ms.demo.user.model.User;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@RunWith(SpringRunner.class)
@Transactional(transactionManager = "transactionManager")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
public class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testOrder() throws Throwable {
        User user = new User();
        user.setName("user01");
        user.setAge(10);
        MvcResult result = mockMvc.perform(post("/api/users").content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        user = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        Product prod = new Product();
        prod.setName("prod01");
        prod.setPrice(10);
        prod.setQuantity(10);
        MvcResult result2 = mockMvc.perform(post("/api/products").content(objectMapper.writeValueAsString(prod)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        prod = objectMapper.readValue(result2.getResponse().getContentAsString(), Product.class);

        Order order = new Order();
        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem item = new OrderItem();
        item.setProduct(new Product(prod.getId()));
        items.add(item);
        order.setOrderNo("order01");
        order.setOwner(new User(user.getId()));
        order.setItems(items);

        MvcResult result3 = mockMvc.perform(post("/api/orders").content(objectMapper.writeValueAsString(order)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        order = objectMapper.readValue(result3.getResponse().getContentAsString(), Order.class);

        MvcResult result4 = mockMvc.perform(get("/api/orders")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        System.out.println(result4.getResponse().getContentAsString());
    }
}

/*
 * 修改历史
 * $Log$ 
 */