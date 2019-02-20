package com.yiya.qq.api;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	9:59
 * description:
 */
public class ApiException extends RuntimeException {

    private static String message;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case 10001:
                message = "错误的请求KEY";
                break;
            case 10003:
                message = "KEY过期";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }
}