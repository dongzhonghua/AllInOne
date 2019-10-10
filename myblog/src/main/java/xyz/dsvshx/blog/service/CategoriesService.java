package xyz.dsvshx.blog.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface CategoriesService {
    JSONArray findCategoriesName();

    JSONObject findCategoriesNameAndArticleNum();

    int countCategoriesNum();
    JSONObject updateCategory(String categoryName, int type);

    JSONObject findAllCategories();
}
