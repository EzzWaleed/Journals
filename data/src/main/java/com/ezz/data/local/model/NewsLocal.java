package com.ezz.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Entity
public class NewsLocal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "published_date")
    public long publishedDate;

    @ColumnInfo(name = "author_name")
    public String authorName;

    @ColumnInfo(name = "source_name")
    public String sourceName;

}
