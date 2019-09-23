package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.Categories;
import xyz.dsvshx.blog.entity.CategoriesExample;

public interface CategoriesMapper {
    int countByExample(CategoriesExample example);

    int deleteByExample(CategoriesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    int insertSelective(Categories record);

    List<Categories> selectByExample(CategoriesExample example);

    Categories selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Categories record, @Param("example") CategoriesExample example);

    int updateByExample(@Param("record") Categories record, @Param("example") CategoriesExample example);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);
}