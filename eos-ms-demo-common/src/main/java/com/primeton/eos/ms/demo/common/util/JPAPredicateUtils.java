/**
 * 
 */
package com.primeton.eos.ms.demo.common.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class JPAPredicateUtils {
    public static final String[] STRINGS = new String[] { "/", "_", "%" };

    public static Predicate like(CriteriaBuilder criteriaBuilder, Expression<String> x, String pattern) {
        for (String str : STRINGS) {
            pattern = pattern.replaceAll(str, "/" + str);
        }
        return criteriaBuilder.like(x, "%" + pattern + "%", '/');
    }
}
