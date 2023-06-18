package com.kevin.springdata.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @类名: JsonUtil
 * @包名：com.kevin.util
 * @时间：2017/7/12 10:48
 * @版本：1.0
 * @描述：FastJson工具类
 */
public class JsonUtil {

    private static final String DEFAULT_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonUtil() {
        // no constructor function
    }

    /**
     * 序列化
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj) {
        return JSON.toJSONStringWithDateFormat(obj, DEFAULT_DATEFORMAT);
    }

    /**
     * 序列化
     * @param obj
     * @param dateFormat
     * @return
     */
    public static String bean2Json(Object obj, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(obj, dateFormat);
    }

    /**
     * 反序列化
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }

    /**
     * 序列化列表为Json数组
     * @param list
     * @param <T>
     * @return
     */
    public static <T> JSONArray list2JsonArray(List<T> list) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        return jsonArray;
    }

    /**
     * 反序列化Json数组为列表
     * @param jsonArr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArray2List(String jsonArr, Class<T> clazz) {
        return JSONArray.parseArray(jsonArr, clazz);
    }

    /**
     * 序列化map为json
     * @param map
     * @return
     */
    public static String map2Json(Map<String, ?> map) {
        return bean2Json(map);
    }

    /**
     * 反序列化json为map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        return json2Bean(jsonStr, Map.class);
    }

    /**
     * 反序列化json为Json对象
     * @param jsonStr
     * @return
     */
    public static JSONObject json2JsonObject(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }

    /**
     * 反序列化json为Json数组
     * @param jsonArr
     * @return
     */
    public static JSONArray json2JsonArray(String jsonArr) {
        return JSONArray.parseArray(jsonArr);
    }

    /**
     * 过滤json中的非法符号
     * @param jsonStr
     * @return
     */
    public static String trimIllegal(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return "";
        }

        jsonStr = jsonStr.replace("\\\"", "\"");
        if (jsonStr.startsWith("\"") && jsonStr.endsWith("\"")) {
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
        }

        return jsonStr;
    }

    private static String subtraction(String a, String b) {
        if (StringUtils.isEmpty(a) || StringUtils.isEmpty(b)) {
            return a;
        }

        List<String> listA = Stream.of(a.split(",")).collect(Collectors.toList());
        List<String> listB = Stream.of(b.split(",")).collect(Collectors.toList());
        listA.removeAll(listB);
        return listA.stream().distinct().collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
       Integer x = 1;
        plus(x);
        System.out.println(x);
    }

    private static void plus(Integer x) {
        x++;
    }
}
