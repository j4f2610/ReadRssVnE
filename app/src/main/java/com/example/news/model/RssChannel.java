package com.example.news.model;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict = false)
public class RssChannel
{
    @Element
    private String title;

    @Element
    private RssImage image;

    @ElementList(inline = true, required = false)
    public List<RssItem> item;

    @Override
    public String toString() {
        return "Channel [image=" + image + ", item=" + item + "]";
    }
}
