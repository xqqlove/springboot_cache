package com.yiwa.springboot_cache.mapper;

import com.yiwa.springboot_cache.beans.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

    @Select("select * from tbl_dept where id=#{id}")
    Department getDeptById(Integer id);
}
