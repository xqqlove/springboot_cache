package com.yiwa.springboot_cache.mapper;

import com.yiwa.springboot_cache.beans.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface EmployeeMapper {


    @Select("select * from tbl_employee where id=#{id}")
    public Employee getEmployee(Integer id);

    @Update("update tbl_employee set last_name=#{lastName},gender=#{gender},email=#{email},dept_id=#{deptId} where id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from tbl_employee where id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("insert into tbl_employee(last_name,gender,email,dept_id) values(#{lastName},#{gender},#{email},#{deptId})")
    public void insertEmployee(Employee employee);

    @Select("select * from tbl_employee where last_name=#{lastName}")
    public Employee getEmpBylastName(String lastName);
}
