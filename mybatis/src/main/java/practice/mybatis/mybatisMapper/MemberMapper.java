package practice.mybatis.mybatisMapper;

import org.apache.ibatis.annotations.*;
import practice.mybatis.entity.Member;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member WHERE name = #{name}")
    Member findByName(@Param("name") String name);

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO member (name, age) VALUES ( #{name}, #{age} )")
    void insertMember(@Param("name") String name, @Param("age") int age);
}
