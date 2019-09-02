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

package com.primeton.eos.ms.demo.order.service;

import static com.primeton.eos.ms.demo.common.exception.CommonErrorCode.NOT_FOUND_MODEL_BY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.primeton.eos.ms.demo.common.repository.CommonJpaRepository;
import com.primeton.eos.ms.demo.common.service.AbstractPersistentModelService;
import com.primeton.eos.ms.demo.order.model.Order;
import com.primeton.eos.ms.demo.order.model.OrderCriteria;
import com.primeton.eos.ms.demo.order.model.OrderItem;
import com.primeton.eos.ms.demo.order.model.OrderItem_;
import com.primeton.eos.ms.demo.order.model.Order_;
import com.primeton.eos.ms.demo.order.repository.OrderItemRepository;
import com.primeton.eos.ms.demo.order.repository.OrderRepository;
import com.primeton.eos.ms.demo.user.model.User_;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class OrderService extends AbstractPersistentModelService<Order> {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    protected CommonJpaRepository<Order, String> getRepo() {
        return orderRepo;
    }

    @Transactional(rollbackOn = Throwable.class)
    public Order create(Order order) {
        order = super.create(order);
        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
            orderItemRepo.save(item);
        }
        return order;
    }

    @Transactional(rollbackOn = Throwable.class)
    public Order update(Order order) {
        order = super.update(order);
        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
            orderItemRepo.save(item);
        }
        return order;
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        List<OrderItem> items = orderItemRepo.findAll((root, query, builder) -> {
            return builder.equal(root.get(OrderItem_.order).get(Order_.id), id);
        });
        orderItemRepo.deleteAll(items);
        super.deleteById(id);
    }

    public Order findById(String id) {
        return orderRepo.findOne((root, query, builder) -> {
            root.fetch(Order_.owner);
            root.fetch(Order_.items).fetch(OrderItem_.product);
            return builder.equal(root.get(Order_.id), id);
        }).orElseThrow(() -> NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id));
    }

    public List<Order> findByCriteria(OrderCriteria criteria) {
        return orderRepo.findAll(toSpecification(criteria));
    }

    private Specification<Order> toSpecification(OrderCriteria criteria) {
        return (root, query, builder) -> {
            if (Long.class != query.getResultType()) {
                // 分页查询时, 会有查询总条数, 查询总条数的resultType == Long.class
                root.fetch(Order_.owner);
                root.fetch(Order_.items).fetch(OrderItem_.product);
            }
            if (criteria == null) {
                return builder.and(new Predicate[] {});
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            Optional.ofNullable(criteria.getOrderNo()).ifPresent((value) -> predicates.add(builder.equal(root.get(Order_.orderNo), value)));
            Optional.ofNullable(criteria.getOwnerId()).ifPresent((value) -> predicates.add(builder.equal(root.get(Order_.owner).get(User_.id), value)));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}

/*
 * 修改历史
 * $Log$ 
 */