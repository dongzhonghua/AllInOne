package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.CommentLikesRecord;
import xyz.dsvshx.blog.entity.CommentLikesRecordExample;

public interface CommentLikesRecordMapper {
    int countByExample(CommentLikesRecordExample example);

    int deleteByExample(CommentLikesRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentLikesRecord record);

    int insertSelective(CommentLikesRecord record);

    List<CommentLikesRecord> selectByExample(CommentLikesRecordExample example);

    CommentLikesRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentLikesRecord record, @Param("example") CommentLikesRecordExample example);

    int updateByExample(@Param("record") CommentLikesRecord record, @Param("example") CommentLikesRecordExample example);

    int updateByPrimaryKeySelective(CommentLikesRecord record);

    int updateByPrimaryKey(CommentLikesRecord record);
}