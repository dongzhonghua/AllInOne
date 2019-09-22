package xyz.dsvhsx.blog.mapper;


import xyz.dsvhsx.blog.model.CommentLikesRecord;

public interface CommentLikesRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLikesRecord record);

    int insertSelective(CommentLikesRecord record);

    CommentLikesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLikesRecord record);

    int updateByPrimaryKey(CommentLikesRecord record);
}