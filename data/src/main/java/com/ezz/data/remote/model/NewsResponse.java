
package com.ezz.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("newsRemotes")
    @Expose
    private List<NewsRemote> newsRemotes;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsRemote> getNewsRemotes() {
        return newsRemotes;
    }

    public void setNewsRemotes(List<NewsRemote> newsRemotes) {
        this.newsRemotes = newsRemotes;
    }

}
