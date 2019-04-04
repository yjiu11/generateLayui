package com.yjiu.tools;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * 自定义响应结构
 */
@Data
public class PTWResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    private Integer count;

    public static PTWResult build(Integer code, String msg, Object data) {
        return new PTWResult(code, msg, data);
    }
    public static PTWResult unauth(){
		return PTWResult.build(304, "没有权限！");
	}
    public static PTWResult ok(Object data,Integer count) {
        return new PTWResult(data,count);
    }
    public static PTWResult ok(Object data) {
        return new PTWResult(data);
    }

    public static PTWResult ok() {
        return new PTWResult(null);
    }
    
    public PTWResult() {

    }

    public static PTWResult build(Integer code, String msg) {
        return new PTWResult(code, msg, null);
    }

    public PTWResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public PTWResult(Object data) {
        this.code = 0;
        this.msg = "OK";
        this.data = data;
    }
    public PTWResult(Object data,Integer count) {
        this.code = 0;
        this.msg = "OK";
        this.data = data;
        this.count = count;
    }
   

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static PTWResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, PTWResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static PTWResult format(String json) {
        try {
            return MAPPER.readValue(json, PTWResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static PTWResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
