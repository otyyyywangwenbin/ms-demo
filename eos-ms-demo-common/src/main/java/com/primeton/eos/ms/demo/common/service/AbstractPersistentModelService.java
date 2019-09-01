/**
 * 
 */
package com.primeton.eos.ms.demo.common.service;

import javax.transaction.Transactional;

import com.primeton.eos.ms.demo.common.model.PersistentModel;
import com.primeton.eos.ms.demo.common.repository.CommonJpaRepository;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public abstract class AbstractPersistentModelService<T extends PersistentModel> extends AbstractIdentityModelService<T> {

    protected abstract CommonJpaRepository<T, String> getRepo();

    @Transactional(rollbackOn = Throwable.class)
    public T create(T model) {
        preCreate(model);
        return super.create(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public T update(T model) {
        preUpdate(model);
        return super.create(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        preDelete(id);
        super.deleteById(id);
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preCreate(T model) {
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preUpdate(final T model) {
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preDelete(String id) {
    }

}
