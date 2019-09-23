package xyz.dsvshx.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.dsvshx.blog.entity.Friendlink;
import xyz.dsvshx.blog.entity.FriendlinkExample;

public interface FriendlinkMapper {
    int countByExample(FriendlinkExample example);

    int deleteByExample(FriendlinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friendlink record);

    int insertSelective(Friendlink record);

    List<Friendlink> selectByExample(FriendlinkExample example);

    Friendlink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friendlink record, @Param("example") FriendlinkExample example);

    int updateByExample(@Param("record") Friendlink record, @Param("example") FriendlinkExample example);

    int updateByPrimaryKeySelective(Friendlink record);

    int updateByPrimaryKey(Friendlink record);
}