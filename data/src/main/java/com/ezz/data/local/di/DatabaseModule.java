package com.ezz.data.local.di;

import android.content.Context;

import com.ezz.data.di.DataScope;
import com.ezz.data.local.DatabaseManger;
import com.ezz.data.local.dao.NewsDao;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

import static com.ezz.data.local.DatabaseManger.DATA_BASE_NAME;

/**
 * Created by Ezz Waleed on 05,March,2019
 */
@Module
public class DatabaseModule {

    @DataScope
    @Provides
    DatabaseManger provideDatabaseManger(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                DatabaseManger.class, DATA_BASE_NAME).build();
    }

    @Provides
    NewsDao provideNewsDao(DatabaseManger databaseManger){
        return databaseManger.newsDao();
    }
}
