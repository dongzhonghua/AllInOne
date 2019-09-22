package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.LeaveMessageLikesRecord;

public interface LeaveMessageLikesRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessageLikesRecord record);

    int insertSelective(LeaveMessageLikesRecord record);

    LeaveMessageLikesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveMessageLikesRecord record);

    int updateByPrimaryKey(LeaveMessageLikesRecord record);
}