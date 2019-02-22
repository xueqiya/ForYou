package com.yiya.qq.model.room;

import com.yiya.qq.app.App;
import com.yiya.qq.model.bean.HomeBean;
import com.yiya.qq.model.room.home.HomeDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {HomeBean.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mAppDatabase;

    public abstract HomeDao homeDao();


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    public static AppDatabase getDatabase() {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(App.getInstance(), AppDatabase.class, "qiqi.db")
                    .allowMainThreadQueries()
                    //.addMigrations(MIGRATION_1_2)
                    .build();
        }
        return mAppDatabase;
    }

}
