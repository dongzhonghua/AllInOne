package xyz.dsvshx.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.dsvshx.blog.entity.CommentRecord;
import xyz.dsvshx.blog.entity.CommentRecordExample;

public interface CommentRecordMapper {
    int countByExample(CommentRecordExample example);

    int deleteByExample(CommentRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentRecord record);

    int insertSelective(CommentRecord record);

    List<CommentRecord> selectByExampleWithBLOBs(CommentRecordExample example);

    List<CommentRecord> selectByExample(CommentRecordExample example);

    CommentRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentRecord record, @Param("example") CommentRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentRecord record, @Param("example") CommentRecordExample example);

    int updateByExample(@Param("record") CommentRecord record, @Param("example") CommentRecordExample example);

    int updateByPrimaryKeySelective(CommentRecord record);

    int updateByPrimaryKeyWithBLOBs(CommentRecord record);

    int updateByPrimaryKey(CommentRecord record);

    @Select("select count(*) from comment_record where isRead=1 and respondentId=#{respondentId} and answererId<>#{respondentId}")
    int countIsReadNumByRespondentId(@Param("respondentId") int respondentId);

    @Select("select count(*) from comment_record")
    int commentNum();

    @Delete("delete from comment_record where articleId=#{articleId}")
    void deleteCommentByArticleId(long articleId);

    @Update("update comment_record set isRead=0 where id=#{id}")
    void readCommentRecordById(int id);

    @Update("update comment_record set isRead=0 where respondentId=#{respondentId}")
    void readCommentRecordByRespondentId(int respondentId);
}