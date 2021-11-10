package com.shangma.controller;

import com.shangma.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    // RedisTemplate可以操作任意类型
//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("test02")
    public String test02(){
        ValueOperations<String, String> opsString = stringRedisTemplate.opsForValue();
        opsString.set("jjj","kkk");
        System.out.println(opsString.get("jjj"));

//        stringRedisTemplate.opsForHash().put();
//        stringRedisTemplate.opsForList().rightPush("","");bbb");
//        System.out.println(stringRedisTemplate.opsForList().index("dat
//        stringRedisTemplate.opsForList().leftPush("")
//        stringRedisTemplate.opsForList().index()
//        stringRedisTemplate.opsForSet().add()
//        stringRedisTemplate.opsForSet().members()
//        stringRedisTemplate.opsForZSet().add()
//        stringRedisTemplate.opsForZSet().range()
        return "success";
    }


//    @GetMapping("test01")
//    public String test01(){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("aaa","bbb");
//        System.out.println(valueOperations.get("aaa"));
//        valueOperations.set("employee",new Employee());
//        System.out.println(valueOperations.get("employee"));
//        return "success";
//    }

}
