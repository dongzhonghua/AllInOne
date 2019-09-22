package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.Friendlink;

public interface FriendlinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friendlink record);

    int insertSelective(Friendlink record);

    Friendlink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friendlink record);

    int updateByPrimaryKey(Friendlink record);
}