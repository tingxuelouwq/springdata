package com.kevin.springdata.util;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * kevin<br/>
 * 2023/6/18 17:55<br/>
 * 封装原生SQL的动态查询操作
 */
@SuppressWarnings("unchecked")
@Component
public class NativeQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 不带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集列表
     */
    public <T> List<T> nativeQuery(String sql, Class<T> resultClass) {
        return nativeQuery(sql, null, resultClass);
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集列表
     */
    public <T> List<T> nativeQuery(String sql, Map<String, Object> param, Class<T> resultClass) {
        Query nativeQuery = entityManager.createNativeQuery(sql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach(nativeQuery::setParameter);
        }

        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(resultClass));
        return nativeQuery.getResultList();
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param transformer 结果集转换器
     * @param <T>         结果集实体类
     * @return 结果集列表
     */
    public <T> List<T> nativeQuery(String sql, Map<String, Object> param, ResultTransformer transformer) {
        Query nativeQuery = entityManager.createNativeQuery(sql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach(nativeQuery::setParameter);
        }

        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(transformer);
        return nativeQuery.getResultList();
    }

    /**
     * 不带查询参数的原生SQL查询
     *
     * @param sql         原生SQL
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 单个结果实体
     */
    public <T> T nativeQuerySingleResult(String sql, Class<T> resultClass) {
        return nativeQuerySingleResult(sql, null, resultClass);
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 单个结果实体
     */
    public <T> T nativeQuerySingleResult(String sql, Map<String, Object> param, Class<T> resultClass) {
        Query nativeQuery = entityManager.createNativeQuery(sql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach(nativeQuery::setParameter);
        }

        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(resultClass));
        return (T) nativeQuery.getSingleResult();
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param transformer 结果集转换器
     * @param <T>         结果集实体类
     * @return 单个结果实体
     */
    public <T> T nativeQuerySingleResult(String sql, Map<String, Object> param, ResultTransformer transformer) {
        Query nativeQuery = entityManager.createNativeQuery(sql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach(nativeQuery::setParameter);
        }

        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(transformer);
        return (T) nativeQuery.getSingleResult();
    }

    /**
     * 不带查询参数的原生SQL查询计数
     *
     * @param sql         原生SQL
     * @return 计数
     */
    public Long nativeQueryCount(String sql) {
        return nativeQueryCount(sql, null);
    }

    /**
     * 带查询参数的原生SQL查询计数
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @return 计数
     */
    public Long nativeQueryCount(String sql, Map<String, Object> param) {
        Query nativeQuery = entityManager.createNativeQuery(sql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach(nativeQuery::setParameter);
        }

        return ((BigInteger) nativeQuery.getSingleResult()).longValue();
    }

    /**
     * 不带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param pageable    分页参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, Pageable pageable, Class<T> resultClass) {
        String countSql = "SELECT COUNT(*)  FROM ( " + sql + " ) getcount";
        return nativeQueryPage(sql, countSql, null, pageable, resultClass);
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param pageable    分页参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, Map<String, Object> param, Pageable pageable, Class<T> resultClass) {
        String countSql = "SELECT COUNT(*)  FROM ( " + sql + " ) getcount";
        return nativeQueryPage(sql, countSql, param, pageable, resultClass);
    }

    /**
     * 带查询参数的原生SQL动态查询
     *
     * @param sql         原生SQL
     * @param param       查询参数
     * @param pageable    分页参数
     * @param transformer 结果集转换器
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, Map<String, Object> param, Pageable pageable, ResultTransformer transformer) {
        String countSql = "SELECT COUNT(*)  FROM ( " + sql + " ) getcount";
        return nativeQueryPage(sql, countSql, param, pageable, transformer);
    }

    /**
     * 不带查询参数的原生SQL动态查询，带计数SQL(注意，计数SQL不能存在GROUP BY子句，否则在计数为0时，COUNT(*)返回NULL而非0，一般使用默认计数SQL即可)
     *
     * @param sql         原生SQL
     * @param countSql    计数SQL
     * @param pageable    分页参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, String countSql, Pageable pageable, Class<T> resultClass) {
        return nativeQueryPage(sql, countSql, null, pageable, resultClass);
    }

    /**
     * 带查询参数的原生SQL动态查询，带计数SQL(注意，计数SQL不能存在GROUP BY子句，否则在计数为0时，COUNT(*)返回NULL而非0，一般使用默认计数SQL即可)
     *
     * @param sql         原生SQL
     * @param countSql    计数SQL
     * @param param       查询参数
     * @param pageable    分页参数
     * @param resultClass 结果集实体类对应的Class
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, String countSql, Map<String, Object> param, Pageable pageable, Class<T> resultClass) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        Query nativeCountQuery = entityManager.createNativeQuery(countSql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach((key, value) -> {
                nativeQuery.setParameter(key, value);
                nativeCountQuery.setParameter(key, value);
            });
        }

        BigInteger totalCount = (BigInteger) nativeCountQuery.getSingleResult();

        nativeQuery.setFirstResult((int) pageable.getOffset());
        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(resultClass));
        List<T> resultList = nativeQuery.getResultList();

        return new PageImpl<>(resultList, pageable, totalCount.longValue());
    }

    /**
     * 带查询参数的原生SQL动态查询，带计数SQL(注意，计数SQL不能存在GROUP BY子句，否则在计数为0时，COUNT(*)返回NULL而非0，一般使用默认计数SQL即可)
     *
     * @param sql         原生SQL
     * @param countSql    计数SQL
     * @param param       查询参数
     * @param pageable    分页参数
     * @param transformer 结果集转换器
     * @param <T>         结果集实体类
     * @return 结果集分页列表
     */
    public <T> Page<T> nativeQueryPage(String sql, String countSql, Map<String, Object> param, Pageable pageable, ResultTransformer transformer) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        Query nativeCountQuery = entityManager.createNativeQuery(countSql);

        if (!CollectionUtils.isEmpty(param)) {
            param.forEach((key, value) -> {
                nativeQuery.setParameter(key, value);
                nativeCountQuery.setParameter(key, value);
            });
        }

        BigInteger totalCount = (BigInteger) nativeCountQuery.getSingleResult();

        nativeQuery.setFirstResult((int) pageable.getOffset());
        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(transformer);
        List<T> resultList = nativeQuery.getResultList();

        return new PageImpl<>(resultList, pageable, totalCount.longValue());
    }
}
