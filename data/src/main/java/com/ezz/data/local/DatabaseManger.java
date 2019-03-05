package com.ezz.data.local;

import com.ezz.data.local.dao.NewsDao;
import com.ezz.data.local.model.NewsLocal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Ezz Waleed on 05,March,2019
 */
@Database(entities = NewsLocal.class, version = 1, exportSchema = false)
public abstract class DatabaseManger extends RoomDatabase {
    public static final String DATA_BASE_NAME = "NewsAppDB";

    public abstract NewsDao newsDao();
}
