package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.LeaveMessageRecord;
import xyz.dsvshx.blog.entity.LeaveMessageRecordExample;

public interface LeaveMessageRecordMapper {
    int countByExample(LeaveMessageRecordExample example);

    int deleteByExample(LeaveMessageRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessageRecord record);

    int insertSelective(LeaveMessageRecord record);

    List<LeaveMessageRecord> selectByExampleWithBLOBs(LeaveMessageRecordExample example);

    List<LeaveMessageRecord> selectByExample(LeaveMessageRecordExample example);

    LeaveMessageRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveMessageRecord record, @Param("example") LeaveMessageRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") LeaveMessageRecord record, @Param("example") LeaveMessageRecordExample example);

    int updateByExample(@Param("record") LeaveMessageRecord record, @Param("example") LeaveMessageRecordExample example);

    int updateByPrimaryKeySelective(LeaveMessageRecord record);

    int updateByPrimaryKeyWithBLOBs(LeaveMessageRecord record);

    int updateByPrimaryKey(LeaveMessageRecord record);
}