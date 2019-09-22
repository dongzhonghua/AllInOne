package xyz.dsvhsx.blog.mapper;


import xyz.dsvhsx.blog.model.Friendlink;

public interface FriendlinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friendlink record);

    int insertSelective(Friendlink record);

    Friendlink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friendlink record);

    int updateByPrimaryKey(Friendlink record);
}