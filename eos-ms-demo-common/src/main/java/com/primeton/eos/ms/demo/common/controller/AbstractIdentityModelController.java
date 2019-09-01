/**
 * 
 */
package com.primeton.eos.ms.demo.common.controller;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primeton.eos.ms.demo.common.model.IdentityModel;
import com.primeton.eos.ms.demo.common.service.AbstractIdentityModelService;
import com.primeton.eos.ms.demo.common.util.ValidationGroups;

import io.swagger.annotations.ApiOperation;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public abstract class AbstractIdentityModelController<T extends IdentityModel> {

    protected abstract AbstractIdentityModelService<T> getSvc();

    @ApiOperation("新增")
    @RequestMapping(method = POST, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public T create(@Validated({ ValidationGroups.Create.class }) @RequestBody T model) {
        return getSvc().create(model);
    }

    @ApiOperation("更新")
    @RequestMapping(method = PUT, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public T update(@Validated({ ValidationGroups.Update.class }) @RequestBody T model) {
        return getSvc().update(model);
    }

    @ApiOperation("按主键删除")
    @RequestMapping(value = "/{id}", method = DELETE, consumes = ALL_VALUE)
    public void deleteById(@PathVariable(name = "id") String id) {
        getSvc().deleteById(id);
    }

    @ApiOperation("按主键查询")
    @RequestMapping(value = "/{id}", method = GET, consumes = ALL_VALUE)
    public T findById(@PathVariable(name = "id") String id) {
        return getSvc().findById(id);
    }

    @ApiOperation("按主键查询")
    @RequestMapping(method = GET, consumes = ALL_VALUE)
    public List<T> findAll() {
        return getSvc().findAll();
    }
}
