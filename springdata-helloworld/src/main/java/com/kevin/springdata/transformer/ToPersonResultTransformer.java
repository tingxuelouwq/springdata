package com.kevin.springdata.transformer;

import org.hibernate.transform.BasicTransformerAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * kevin<br/>
 * 2023/6/18 17:42<br/>
 */
public class ToPersonResultTransformer<T> extends BasicTransformerAdapter {

    private Class<T> target;

    public ToPersonResultTransformer(Class<T> target) {
        this.target = target;
    }

    /**
     * 每行一个 String，字段值全部接在一起
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
            e.printStackTrace();
        }
        return t;
    }
}

