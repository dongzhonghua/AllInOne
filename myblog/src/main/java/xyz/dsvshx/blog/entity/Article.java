package xyz.dsvshx.blog.entity;

import lombok.Data;

@Data
public class Article {
    private Integer id;

    private Long articleid;

    private String author;

    private String originalauthor;

    private String articletitle;

    private String articletags;

    private String articletype;

    private String articlecategories;

    private String publishdate;

    private String updatedate;

    private String articleurl;

    private Integer likes;

    private Long lastarticleid;

    private Long nextarticleid;

    //private String articleTabloid;
}