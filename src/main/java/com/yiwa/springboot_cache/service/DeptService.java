package com.yiwa.springboot_cache.service;

import com.yiwa.springboot_cache.beans.Department;
import com.yiwa.springboot_cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Cacheable(cacheNames = "dept",unless = "#result==null")
    public Department getDept(Integer id) {
        System.out.println("查询部门信息"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }
}
