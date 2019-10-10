package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.service.TagService;
@Service
public class TagServiceImpl implements TagService {
    @Override
    public void addTags(String[] tags, int tagSize) {

    }

    @Override
    public JSONObject findTagsCloud() {
        return null;
    }

    @Override
    public int countTagsNum() {
        return 0;
    }

    @Override
    public int getTagsSizeByTagName(String tagName) {
        return 0;
    }
}
