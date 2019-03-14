package com.yiya.foryou.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	17:08
 * description:
 */
@Entity(tableName = "note")
public class NoteBean {
    /**
     * id : 1
     * title : 第1条通知的标题
     * time : 2019-03-12 09:46:36
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uid")
    private String uid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "time")
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
