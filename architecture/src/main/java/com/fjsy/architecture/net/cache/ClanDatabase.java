package com.fjsy.architecture.net.cache;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fjsy.architecture.global.data.bean.PaperDetailBean;
import com.fjsy.architecture.global.data.bean.PaperDetailDao;
import com.fjsy.architecture.global.data.bean.UserBean;
import com.fjsy.architecture.global.data.bean.UserDao;
import com.fjsy.architecture.utils.Utils;


@Database(entities = {Cache.class
        , PaperDetailBean.class
}, version = 2)
//数据读取、存储时数据转换器,比如将写入时将Date转换成Long存储，读取时把Long转换Date返回
//@TypeConverters(DateConverter.class)
public abstract class ClanDatabase extends RoomDatabase {
    private static ClanDatabase database;

    public abstract CacheDao getCacheDao();

    //    public abstract UserDao getUser();
    public abstract PaperDetailDao getPaperDetailDao();

    public static ClanDatabase get() {
        synchronized (Utils.getApp()) {
            if (database == null) {
                database = Room.databaseBuilder(Utils.getApp(), ClanDatabase.class, "clan")
                        //是否允许在主线程进行查询
                        .allowMainThreadQueries()
//                数据库创建和打开后的回调
//                .addCallback()
                        //设置查询的线程池
                        //.setQueryExecutor()
//                .openHelperFactory()
                        //room的日志模式
                        .setJournalMode(JournalMode.AUTOMATIC)
                        //数据库升级异常之后的回滚
//                .fallbackToDestructiveMigration()
                        //数据库升级异常后根据指定版本进行回滚
                        .fallbackToDestructiveMigrationFrom()
                        .addMigrations(sMigration_1_2)
                        .build();
            }
            return database;
        }
    }

    //     Array of all migrations
    private static final Migration[] ALL_MIGRATIONS = new Migration[]{
            ClanDatabase.sMigration_1_2};

    static final Migration sMigration_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `PaperDetailBean` (" +
                    "   `id`              TEXT  NOT NULL " +
                    "  , `viewUid`              TEXT  NOT NULL " +
                    "  ,`resultData`    TEXT " +
                    "  ,`count`            INTEGER NOT NULL" +
                    "  ,`statusInfo_statusCode`            INTEGER " +
                    "  ,`statusInfo_statusMessage`            TEXT" +
                    "  ,`cover_id_value`   INTEGER " +
                    "  ,`cover_id_text`    TEXT " +
                    "  ,`benison`         TEXT " +
                    "  ,`amount`          TEXT" +
                    "  ,`nums`            INTEGER NOT NULL" +
                    "  ,`uid`             TEXT" +
                    "  ,`typeid_value`     INTEGER " +
                    "  ,`typeid_text`      TEXT" +
                    "  ,`cateid_value`     INTEGER " +
                    "  ,`cateid_text`      TEXT" +
                    "  ,`state_value`      INTEGER " +
                    "  ,`state_text`       TEXT" +
                    "  ,`object_id`       INTEGER NOT NULL" +
                    "  ,`is_show`         INTEGER NOT NULL" +
                    "  ,`pay_time`        INTEGER NOT NULL" +
                    "  ,`balance`         TEXT " +
                    "  ,`rand_price`      TEXT " +
                    "  ,`create_time`     TEXT " +
                    "  ,`update_time`     TEXT " +
                    "  ,`delete_time`     TEXT " +
                    "  ,`user_uid`         TEXT " +
                    "  ,`user_nickname`    TEXT " +
                    "  ,`user_avatar_url`  TEXT" +
                    "  ,`receive_money`   TEXT" +
                    "  ,`status`          INTEGER NOT NULL" +
                    "  ,PRIMARY KEY(`id`,`viewUid`)"+
                    ")"
            );
        }
    };
}
