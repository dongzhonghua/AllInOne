package xzy.dsvshx.blog.mapper;

import xzy.dsvshx.blog.model.Privateword;

public interface PrivatewordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privateword record);

    int insertSelective(Privateword record);

    Privateword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privateword record);

    int updateByPrimaryKey(Privateword record);
}