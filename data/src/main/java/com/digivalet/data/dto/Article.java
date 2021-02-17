package com.digivalet.data.dto;


import java.io.Serializable;

public class Article implements Serializable {
    public String Title;
    public String Url;

    public Article(String title, String url) {
        Title = title;
        Url = url;
    }
}