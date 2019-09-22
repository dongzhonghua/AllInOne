package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.Archives;

public interface ArchivesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Archives record);

    int insertSelective(Archives record);

    Archives selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);
}