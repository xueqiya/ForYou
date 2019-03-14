package com.yiya.foryou.data.room.home;

import com.yiya.foryou.bean.NoteBean;

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
    long[] insertTitle(NoteBean... homeBeans);
}
