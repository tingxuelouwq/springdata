package com.kevin.springdata.transformer;

import org.hibernate.transform.BasicTransformerAdapter;

import java.util.Arrays;

/**
 * kevin<br/>
 * 2023/6/18 17:42<br/>
 */
public class ToStringResultTransformer extends BasicTransformerAdapter {
    public static final ToStringResultTransformer INSTANCE = new ToStringResultTransformer();

    private ToStringResultTransformer() {
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
        StringBuilder row = new StringBuilder();
        Arrays.asList(tuple).stream().forEach(row::append);
        return row.toString();
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

