package xyz.dsvhsx.blog.mapper;


import xyz.dsvhsx.blog.model.LeaveMessageRecord;

public interface LeaveMessageRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessageRecord record);

    int insertSelective(LeaveMessageRecord record);

    LeaveMessageRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveMessageRecord record);

    int updateByPrimaryKeyWithBLOBs(LeaveMessageRecord record);

    int updateByPrimaryKey(LeaveMessageRecord record);
}