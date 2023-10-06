package com.fragmentviewpager.newsapijsonexample.newsApiGetExample.modal;

import java.util.List;

public class NewsRightNowModal {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public NewsRightNowModal(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
