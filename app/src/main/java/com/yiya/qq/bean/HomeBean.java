package com.yiya.qq.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	17:08
 * description:
 */
@Entity(tableName = "Home")
public class HomeBean {
    /**
     * id : 1147
     * title : 爆竹声声辞旧岁，欢天喜地迎新年
     * createdate : 2019-02-01 09:59:12
     * img : https://img.qmwm777.com/3/upload/image/201902/c4680483874d45cfa03e643f4ace3a9c_750x300.jpg
     * readnum : 625
     */
    @PrimaryKey
    private int id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String createdate;

    @Ignore
    private String img;

    @Ignore
    private int readnum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }
}
