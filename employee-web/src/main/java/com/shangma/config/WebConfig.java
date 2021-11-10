package com.shangma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
// 开启包扫描
@ComponentScan(basePackages = {"com.shangma.controller","com.shangma.exception"})
@EnableWebMvc  //开启mvc的注解驱动  相当于 <mvc:annotation-driven/>
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(false);
    }

    /**
     * 配置redis的工厂类
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        return redisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        // 设置工厂对象
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }

    /**
     * redis线程池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        return jedisPoolConfig;
    }

//    //RedisTemplate放入容器中，并且制定用jedis工厂
//    @Bean
//    public RedisTemplate redisTemplate(){
//        // 创建Redis模板
//        RedisTemplate redisTemplate = new RedisTemplate();
//        // 设置连接的客户端
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
}
