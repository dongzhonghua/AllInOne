package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.ArticleLikesRecord;

public interface ArticleLikesRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLikesRecord record);

    int insertSelective(ArticleLikesRecord record);

    ArticleLikesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleLikesRecord record);

    int updateByPrimaryKey(ArticleLikesRecord record);
}