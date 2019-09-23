package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.ArticleLikesRecord;
import xyz.dsvshx.blog.entity.ArticleLikesRecordExample;

public interface ArticleLikesRecordMapper {
    int countByExample(ArticleLikesRecordExample example);

    int deleteByExample(ArticleLikesRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLikesRecord record);

    int insertSelective(ArticleLikesRecord record);

    List<ArticleLikesRecord> selectByExample(ArticleLikesRecordExample example);

    ArticleLikesRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleLikesRecord record, @Param("example") ArticleLikesRecordExample example);

    int updateByExample(@Param("record") ArticleLikesRecord record, @Param("example") ArticleLikesRecordExample example);

    int updateByPrimaryKeySelective(ArticleLikesRecord record);

    int updateByPrimaryKey(ArticleLikesRecord record);
}