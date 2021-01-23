package com.news;

import java.util.Date;

/**
 * 新闻实体类
 * @author xiaohang
 */
public class News {
    private Integer id;     //新闻id
    private String title;   //新闻标题
    private String content; //新闻内容
    private Date create_time;//创建时间


    public News() {
    }

    public News(Integer id, String title, String content, Date create_time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                '}';
    }

}
