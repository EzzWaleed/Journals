package com.ezz.data.local.dao;

import com.ezz.data.local.model.NewsLocal;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Dao
public abstract class NewsDao {

    /**
     * creates data source of {@link NewsLocal} sorted by date DESC
     */
    @Query("SELECT * FROM NewsLocal ORDER BY published_date DESC")
    abstract DataSource<Integer, NewsLocal> newsByDate();

    /**
     * deletes all {@link NewsLocal} records between two dates
     *
     * @param minPublishedDate oldest date receive from api page
     * @param maxPublishedDate newest date receive from api page
     */
    @Query("DELETE FROM NewsLocal WHERE published_date BETWEEN :minPublishedDate AND :maxPublishedDate")
    abstract void deleteRange(long minPublishedDate, long maxPublishedDate);


    /**
     * insert list of {@link NewsLocal}
     * @param newsLocals list to be inserted
     */
    @Insert(onConflict = REPLACE)
    abstract void insertNews(List<NewsLocal> newsLocals);

    /**
     * updates news records according to its published dates
     * @param newsLocalList list to be updated
     * @param isFirstPage
     * @param hasLoadedAllItems
     */
    @Transaction
    public void updateNews(List<NewsLocal> newsLocalList, boolean isFirstPage, boolean hasLoadedAllItems) {

        long minUpdateTime, maxUpdateTime;

        if (!hasLoadedAllItems) {
            minUpdateTime = newsLocalList.get(newsLocalList.size() - 1).publishedDate;
        } else {
            minUpdateTime = 0;
        }

        if (isFirstPage) {
            maxUpdateTime = Long.MAX_VALUE;
        } else {
            maxUpdateTime = newsLocalList.get(0).publishedDate;
        }

        deleteRange(minUpdateTime, maxUpdateTime);
        insertNews(newsLocalList);
    }

}
