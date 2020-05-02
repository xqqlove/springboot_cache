package com.yiwa.springboot_cache;

import com.yiwa.springboot_cache.beans.Employee;
import com.yiwa.springboot_cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

@SpringBootTest
class SpringbootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作字符串
    @Autowired
    RedisTemplate redisTemplate;//对象

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmployee(1);
        System.out.println(employee);
    }

    /**
     * String List Set Hash ZSet  redis五大数据类型
     */
    @Test
    public void test(){
//        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist1","1");
        stringRedisTemplate.opsForList().leftPush("mylist1","2");
        stringRedisTemplate.opsForList().leftPush("mylist1","3");
    }
    @Test
    public void test3(){
//        Employee employee = employeeMapper.getEmployee(1);
//        //
//        redisTemplate.opsForValue().set("emp-01",employee);
        Object o = redisTemplate.opsForValue().get("dept:1");
        System.out.println(o);
    }

    @Test
    public void test1(){
        Jedis jedis=new Jedis("192.168.205.128",6379);
        System.out.println(jedis.ping());
    }

}
