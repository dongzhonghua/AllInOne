package xyz.dsvshx.blog.service;

import com.alibaba.fastjson.JSONObject;

public interface TagService {
    void addTags(String[] tags, int tagSize);
    JSONObject findTagsCloud();
    int countTagsNum();
    int getTagsSizeByTagName(String tagName);

}
