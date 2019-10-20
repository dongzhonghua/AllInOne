package xyz.dsvshx.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.dsvshx.blog.entity.Visitor;
import xyz.dsvshx.blog.entity.VisitorExample;

public interface VisitorMapper {
    int countByExample(VisitorExample example);

    int deleteByExample(VisitorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    List<Visitor> selectByExampleWithBLOBs(VisitorExample example);

    List<Visitor> selectByExample(VisitorExample example);

    Visitor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Visitor record, @Param("example") VisitorExample example);

    int updateByExampleWithBLOBs(@Param("record") Visitor record, @Param("example") VisitorExample example);

    int updateByExample(@Param("record") Visitor record, @Param("example") VisitorExample example);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKeyWithBLOBs(Visitor record);

    int updateByPrimaryKey(Visitor record);


    @Select("select visitorNum from visitor where pageName=#{pageName}")
    long getVisitorNumByPageName(@Param("pageName") String pageName);

    @Insert("insert into visitor(visitorNum,pageName) values(0,#{pageName})")
    void insertVisitorArticlePage(String pageName);

    @Select("select visitorNum from visitor where pageName='totalVisitor'")
    long getTotalVisitor();

    @Update("update visitor set visitorNum=#{visitorNum} where pageName=#{pageName}")
    void updateVisitorNumByPageName(@Param("pageName") String pageName, @Param("visitorNum") String visitorNum);

}