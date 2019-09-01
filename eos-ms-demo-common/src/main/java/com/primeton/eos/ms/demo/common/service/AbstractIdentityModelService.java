/**
 * 
 */
package com.primeton.eos.ms.demo.common.service;

import static com.primeton.eos.ms.demo.common.exception.CommonErrorCode.NOT_FOUND_MODEL_BY_ID;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.primeton.eos.ms.demo.common.model.IdentityModel;
import com.primeton.eos.ms.demo.common.model.PersistentModel_;
import com.primeton.eos.ms.demo.common.repository.CommonJpaRepository;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public abstract class AbstractIdentityModelService<T extends IdentityModel> {

    protected abstract CommonJpaRepository<T, String> getRepo();

    protected Sort defaultSort = Sort.by(Order.asc(PersistentModel_.createdDate.getName()));

    @Transactional(rollbackOn = Throwable.class)
    public T create(T model) {
        return getRepo().save(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public T update(T model) {
        return getRepo().save(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        getRepo().deleteById(id);
    }

    public T findById(String id) {
        return findById(id, true);
    }

    public T findById(String id, boolean errorIfNotFound) {
        Optional<T> optional = getRepo().findById(id);
        return errorIfNotFound ? optional.orElseThrow(() -> NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id)) : optional.orElse(null);
    }

    public void errorIfNotFoundById(String id) {
        if (!getRepo().existsById(id)) {
            throw NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id);
        }
    }

    public List<T> findAll() {
        return getRepo().findAll(defaultSort);
    }

    public Page<T> findAll(Pageable page) {
        if (!page.getSort().isSorted()) {
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), defaultSort);
        }
        return getRepo().findAll(page);
    }

    protected String getRepoClassName() {
        Class<?> clazz = getRepo().getClass().getInterfaces()[0];
        Type type = clazz.getGenericSuperclass();
        System.out.println(type);
        return clazz.getSimpleName();
    }

}
