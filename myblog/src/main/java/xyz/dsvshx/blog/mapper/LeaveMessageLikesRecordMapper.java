package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.LeaveMessageLikesRecord;
import xyz.dsvshx.blog.entity.LeaveMessageLikesRecordExample;

public interface LeaveMessageLikesRecordMapper {
    int countByExample(LeaveMessageLikesRecordExample example);

    int deleteByExample(LeaveMessageLikesRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessageLikesRecord record);

    int insertSelective(LeaveMessageLikesRecord record);

    List<LeaveMessageLikesRecord> selectByExample(LeaveMessageLikesRecordExample example);

    LeaveMessageLikesRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveMessageLikesRecord record, @Param("example") LeaveMessageLikesRecordExample example);

    int updateByExample(@Param("record") LeaveMessageLikesRecord record, @Param("example") LeaveMessageLikesRecordExample example);

    int updateByPrimaryKeySelective(LeaveMessageLikesRecord record);

    int updateByPrimaryKey(LeaveMessageLikesRecord record);
}