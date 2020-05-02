package com.yiwa.springboot_cache.service;

import com.yiwa.springboot_cache.beans.Employee;
import com.yiwa.springboot_cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后要相同的数据从缓存中获取，不用调用方法
     *
     * CacheManager 管理福讴歌Cache组件，对缓存的真正crud操作在cache组件中，
     * 每一个缓存组件有自己惟一的名字
     *
     * 几个属性：
     *   cacheNames/value 指缓存组件的名字
     *   key：缓存数据使用的key
     *   keyGenerator：key的生成器
     *   cacheManager 指定缓存管理器，或者cacheResolver指定获取解析器
     *   condition指定符合条件下才缓存
     *   unless否定缓存，当unless指定的条件为true方法返回值就不会被缓存
     *   sync：是否使用异步模式
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee employee = employeeMapper.getEmployee(id);
        return employee;
    }

    /**
     * 先调用目标方法 将结果缓存
     * @param employee
     * @return
     */
    @CachePut(value = "emp",key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**缓存清空
     * key只要清空的数据
     * 默认是在方法执行之后执行
     *beforeInvocation=true
     * 代表清除缓存操作在方法运行之后执行，无论是否出现异常 都清
     * @param id
     */
    @CacheEvict(value = "emp" /*,key = "#id"*/,allEntries = true,beforeInvocation = true)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
//        employeeMapper.deleteEmpById(id);
    }

    /**
     * 定义复杂缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp" ,key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpBylastName(lastName);
    }
}
