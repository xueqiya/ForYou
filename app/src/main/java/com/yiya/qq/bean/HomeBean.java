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
     * id : 2235
     * title : 办公椅2-JP0225030
     * img : https://img.qmwm777.com/3/upload/image/201902/a07a2f76a6864c41b4f23a70aeeee4c7_750x500.jpg
     * inventory : 0
     * sales : 200
     * totalInventory : 200
     * period : 30
     * rate : 2.81
     * inprice : 234.0
     * saledate : 2019-02-25 15:50:23
     * stopsaledate : 2019-02-25 16:52:15
     * goodsType : 4
     */
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String title;
    @Ignore
    private String img;
    @Ignore
    private int inventory;
    @Ignore
    private int sales;
    @Ignore
    private int totalInventory;
    @Ignore
    private int period;
    @Ignore
    private double rate;
    @Ignore
    private double inprice;
    @Ignore
    private String saledate;
    @Ignore
    private String stopsaledate;
    @Ignore
    private int goodsType;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getInprice() {
        return inprice;
    }

    public void setInprice(double inprice) {
        this.inprice = inprice;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public String getStopsaledate() {
        return stopsaledate;
    }

    public void setStopsaledate(String stopsaledate) {
        this.stopsaledate = stopsaledate;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }
}
