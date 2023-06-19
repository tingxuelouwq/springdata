package com.kevin.springdata.transformer;

import org.hibernate.transform.BasicTransformerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * kevin<br/>
 * 2023/6/18 17:42<br/>
 */
public class ToPersonResultTransformer<T> extends BasicTransformerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Class<T> target;

    public ToPersonResultTransformer(Class<T> target) {
        this.target = target;
    }

    /**
     *
     * @param tuple   字段值
     * @param aliases 字段名
     * @return
     */
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        T t = null;
        try {
            t = target.getDeclaredConstructor().newInstance();
            Field[] fields = target.getDeclaredFields();
            for (Field field : fields) {
                for (int i = 0; i < aliases.length; i++) {
                    if (field.getName().equals(aliases[i])) {
                        field.setAccessible(true);
                        if (tuple[i] == null) {
                            continue;
                        }
                        if (tuple[i].getClass().getTypeName().equals("java.math.BigInteger")) {
                            field.set(t, ((BigInteger) tuple[i]).longValue());
                        } else {
                            field.set(t, tuple[i]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("实体转换异常", e);
            throw new FatalBeanException("实体转换异常, className = " + target.getName(), e);
        }
        return t;
    }
}

