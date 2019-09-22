package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.Categories;

public interface CategoriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    int insertSelective(Categories record);

    Categories selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);
}