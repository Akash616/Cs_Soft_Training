package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass;

import java.util.List;

public class UserResponse {

    private String page, per_page, total, total_pages;
    private List<UsersDataList> data;
    private UserSupport support;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<UsersDataList> getData() {
        return data;
    }

    public void setData(List<UsersDataList> data) {
        this.data = data;
    }

    public UserSupport getSupport() {
        return support;
    }

    public void setSupport(UserSupport support) {
        this.support = support;
    }
}
