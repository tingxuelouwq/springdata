package com.kevin.springdata.transformer;

import org.hibernate.transform.BasicTransformerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;

import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * kevin<br/>
 * 2023/6/18 17:42<br/>
 */
public class ToPersonResultTransformer extends BasicTransformerAdapter {

    private static final long serialVersionUID = -5645251125599703559L;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Class<?> target;

    public ToPersonResultTransformer(Class<?> target) {
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
        Object obj;
        try {
            obj = target.getDeclaredConstructor().newInstance();
            Field[] fields = target.getDeclaredFields();
            String typeName;
            for (Field field : fields) {
                for (int i = 0; i < aliases.length; i++) {
                    if (field.getName().equals(aliases[i])) {
                        field.setAccessible(true);
                        if (tuple[i] == null) {
                            continue;
                        }

                        typeName = tuple[i].getClass().getTypeName();
                        if (typeName.equals(BigInteger.class.getTypeName())) {
                            field.set(obj, ((BigInteger) tuple[i]).longValue());
                        } else if (typeName.equals(Character.class.getTypeName())) {
                            field.set(obj, String.valueOf(tuple[i]));
                        } else if (typeName.equals(Byte.class.getTypeName())) { // tinyint默认会被映射为Boolean，需将jdbcUrl中的tinyInt1isBit=false，这样会将其映射为Byte
                            field.set(obj, ((Byte) tuple[i]).intValue());
                        } else {
                            field.set(obj, tuple[i]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("实体转换异常, className=" + target.getName() + ", errMsg=" + e.getMessage(), e);
            throw new FatalBeanException("实体转换异常", e);
        }
        return obj;
    }
}

