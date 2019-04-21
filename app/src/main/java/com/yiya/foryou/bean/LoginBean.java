package com.yiya.foryou.bean;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/11	15:21
 * description:
 */
public class LoginBean {

    /**
     * code : 200
     * msg : OK
     * data : {"uid":"xueqi"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return getCode() == 200;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : xueqi
         */

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
