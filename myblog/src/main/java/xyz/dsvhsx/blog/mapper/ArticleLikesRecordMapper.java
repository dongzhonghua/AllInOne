package xyz.dsvhsx.blog.mapper;


import xyz.dsvhsx.blog.model.ArticleLikesRecord;

public interface ArticleLikesRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLikesRecord record);

    int insertSelective(ArticleLikesRecord record);

    ArticleLikesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleLikesRecord record);

    int updateByPrimaryKey(ArticleLikesRecord record);
}