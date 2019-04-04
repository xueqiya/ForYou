package com.yiya.foryou.bean;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	17:08
 * description:
 */
public class NoteLisBean {

    /**
     * code : 200
     * msg : OK
     * data : [{"id":27,"uid":"xueqi","title":"3","time":"2019-04-03 22:40:25","details":"4"},{"id":26,"uid":"xueqi","title":"2","time":"2019-04-03 22:38:29","details":"3"},{"id":6,"uid":"xueqi","title":"无人","time":"2019-04-03 22:12:10","details":"这个世界最坏罪名，叫太易动情，但我喜欢这罪名。"},{"id":9,"uid":"xueqi","title":"《你给我听好》","time":"2019-03-26 23:54:19","details":"你给我听好，想哭就要笑。"},{"id":12,"uid":"xueqi","title":"《淘汰》","time":"2019-03-26 23:54:19","details":"我説了所有的谎 \r\n你全都相信 \r\n简单的我爱你，你却老不信"},{"id":15,"uid":"xueqi","title":"《白玫瑰》","time":"2019-03-26 23:54:19","details":"身处劣势、如何不攻心计。"},{"id":18,"uid":"xueqi","title":"《斗战神》","time":"2019-03-26 23:54:19","details":"不做随风飘的沙，不做秋叶上蚂蚱，不坐待冬雪融化，敢问谁敢栽我生死造化。"},{"id":5,"uid":"xueqi","title":"《好久不见》","time":"2019-03-26 23:53:57","details":"你会不会忽然的出现 \r\n在街角的咖啡店 \r\n我会带着笑脸 挥手寒暄 \r\n和你 坐着聊聊天 \r\n我多么想和你见一面 \r\n看看你最近改变 \r\n不再去说从前 只是寒暄 \r\n对你说一句 只是说一句 \r\n好久不见"},{"id":7,"uid":"xueqi","title":"《富士山下》","time":"2019-03-26 23:53:57","details":"原谅我不再送花,伤口应要结疤,花瓣铺满心里坟场才害怕。"},{"id":8,"uid":"xueqi","title":" 《不要说话》","time":"2019-03-26 23:53:57","details":"爱一个人是不是应该有默契 \r\n我以为你懂的每当我看着你。"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Entity
    public static class DataBean {
        /**
         * id : 27
         * uid : xueqi
         * title : 3
         * time : 2019-04-03 22:40:25
         * details : 4
         */
        @PrimaryKey
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
