package com.ls.util.common;

import com.alibaba.fastjson.JSONObject;
import com.ls.util.constants.Constants;
import com.ls.util.constants.ErrorMsg;

import java.util.List;

public class CommonUtil {

    /**
     * 返回一个data为空对象的成功消息的json
     */
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    /**
     * 返回码为200的成功json（返回对象）
     */
    public static JSONObject successJson(Object data) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", data);
        return resultJson;
    }


    public static JSONObject successJson(JSONObject data) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", data);
        return resultJson;
    }

    /**
     * 返回码为200的成功json（返回List）
     */
    public static JSONObject successJson(List data) {
        JSONObject resultJson = successJson();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", data);
        return resultJson;
    }

    /**
     * 返回码为200的成功json（返回值）
     */
    public static JSONObject successJson(String data) {
        JSONObject resultJson = successJson();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", data);
        return resultJson;
    }

    /**
     * 返回码为200的成功json（返回值+列表）
     */
    public static JSONObject successJson(String param,List data) {
        JSONObject resultJson = successJson();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("param", param);
        resultJson.put("data", data);
        return resultJson;
    }

    /**
     * 返回码为200的成功json（返回值+列表）
     */
    public static JSONObject successJson(Integer param,List data) {
        JSONObject resultJson = successJson();
        resultJson.put("status", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("param", param);
        resultJson.put("data", data);
        return resultJson;
    }

    /**
     * 返回错误信息JSON
     */
    public static JSONObject errorJson(ErrorMsg errorEnum) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("status", errorEnum.getErrorCode());
        resultJson.put("msg", errorEnum.getErrorMsg());
        return resultJson;
    }
}
