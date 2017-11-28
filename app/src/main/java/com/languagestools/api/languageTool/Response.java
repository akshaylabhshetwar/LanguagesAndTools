package com.languagestools.api.languageTool;

import com.languagestools.models.LanguageTool;

import java.util.List;

public class Response {
    private List<LanguageTool> list;
    private int total_pages;

    public List<LanguageTool> getList() {
        return this.list;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }
}
