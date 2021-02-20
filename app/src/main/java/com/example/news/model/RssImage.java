package com.example.news.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "image", strict = false)
public class RssImage
{
    @Element
    private String url;

    @Element
    private String title;

    @Element
    private String link;

    @Override
    public String toString() {
        return "RssImage [url=" + url + ", width=" + title + ", height=" + link + "]";
    }
}
