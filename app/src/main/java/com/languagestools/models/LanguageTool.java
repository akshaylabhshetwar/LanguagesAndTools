package com.languagestools.models;

public class LanguageTool {
    private String name;
    private String icon;

    public LanguageTool(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public LanguageTool() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
