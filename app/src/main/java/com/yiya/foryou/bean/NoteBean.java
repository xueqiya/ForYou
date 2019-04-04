package com.yiya.foryou.bean;

public class NoteBean {

    /**
     * code : 200
     * msg : OK
     * data : {"id":12,"uid":"xueqi","title":"《淘汰》","time":"2019-03-26 23:54:19","details":"我説了所有的谎 \r\n你全都相信 \r\n简单的我爱你，你却老不信"}
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
         * id : 12
         * uid : xueqi
         * title : 《淘汰》
         * time : 2019-03-26 23:54:19
         * details : 我説了所有的谎
         你全都相信
         简单的我爱你，你却老不信
         */

        private int id;
        private String uid;
        private String title;
        private String time;
        private String details;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
