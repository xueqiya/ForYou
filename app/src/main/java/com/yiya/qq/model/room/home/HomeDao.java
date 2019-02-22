package com.yiya.qq.model.room.home;

import com.yiya.qq.model.bean.HomeBean;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/22	15:23
 * description:
 */
@Dao
public interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTitle(HomeBean... homeBeans);
}
