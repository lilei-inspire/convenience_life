package com.ls.util.constants;

public enum ErrorMsg {
    /*
     * 错误信息
     * */
    E_400("400", "请求处理异常，请稍后再试"),

    E_900("900","该用户不存在"),
    E_903("903","密码错误"),
    E_901("901","用户名已存在"),
    E_902("902","发送失败，请确认邮箱"),
    E_904("903","图片仅支持JPG、PNG格式"),
    E_905("905","发表动态失败，请稍后再试"),
    E_906("906","未上传图片"),
    E_907("907","您的账号已被封禁，请联系管理员"),
    E_908("908","赋权失败，请检查后台！"),
    E_909("909","封号失败，请检查后台！"),
    E_910("910","注销失败，请稍后再试！");
    private String errorCode;

    private String errorMsg;

    ErrorMsg(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
