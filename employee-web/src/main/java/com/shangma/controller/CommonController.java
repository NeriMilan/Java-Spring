package com.shangma.controller;

import com.shangma.entity.Employee;
import com.shangma.exception.ApiException;
import com.shangma.http.AxiosResult;
import com.shangma.http.AxiosStatus;
import com.shangma.service.EmployeeService;
import com.shangma.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("common")
//@CrossOrigin(origins = "http://localhost:63343",methods = RequestMethod.GET,allowedHeaders = "*",allowCredentials = "false")
public class CommonController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *  No 'Access-Control-Allow-Origin' header is present on the requested resource
     *
     *  为什么会出现跨域问题?
     *
     *  URL的组成:
     *  协议:ip:端口/请求路径1/请求路径2..?参数列表#fragment
     *  协议
     *  域名
     *  端口
     *  如果三者都一样，表示是同一个网站，此时不会出现跨域问题
     *  如果三者有一个不一样，表示不同的网站，此时会出现跨域问题
     *  跨域问题只会出现在ajax请求中
     *  同步请求:a标签 表单  不会出现跨域问题
     *
     *  SpringMVC跨域问题的解决
     *
     *
     *  1.在类上面加注解@CrossOrigin 整个类中就不会出现跨域问题
     *  2.@CrossOrigin(origins = "http://localhost:63343") 表示http://localhost:63343的网站请求不会出现跨域问题
     *
     * 全局配置
     * 1. 在springmvc的xml配置文件中添加配置
     * 2. web层的核心配置类实现WebMvcConfigurer接口，重写addCorsMappings方法
     */

    /**
     *  发送短信的方式：
     *  1. 三大运行商
     *        聚合数据
     *        极速数据
     *  2.云平台
     *      阿里云
     *      腾讯云
     *      百度云
     *  3.第三方平台
     *       极光
     */
    @GetMapping("getPhoneCode/{phone}")
    public AxiosResult getPhoneCode(@PathVariable String phone){
        // byte short int long float double char boolean             void
        // Byte Short Integer Long Float Double Character Boolean
        // 应该去数据库中查询是否有这个手机号
        Employee employee = employeeService.doLogin(phone);
        System.out.println(employee);

        if (employee == null){
            // No 'Access-Control-Allow-Origin' header is present on the requested resource.
            // 如果数据库中没有手机号,那么返回前端错误
            throw new ApiException(AxiosStatus.ERROR);
        }else{
            // 如果数据库中有手机号，那么发送短信验证码
            // 生成6位数字的验证码
            int code = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
            // 耗时操作
            SmsUtil.sendSms(phone,code + "");
            // 把手机号作为key，code作为值放入redis中
            // 设置phone对应的值的失效时间
            stringRedisTemplate.opsForValue().set(phone,code + "",5, TimeUnit.MINUTES);
            return AxiosResult.success();
        }
    }

    /**
     *
     * StringDataRedis
     * 操作Redis的客户端官方推荐有两个:Jedis lettuce
     * 如果有一天公司操作redis的客户端需要从Jedis，换成lettuce，那么会造成大量的错误代码
     *
     * SpringDataRedis是对于Jedis lettuce进行了封装。使用Jedis或者lettuce的封装后的方法一模一样。
     * 如果有一天换客户端了，代码不需要改变。
     *
     *
     */
    @PostMapping("doLogin")
    public AxiosResult doLogin(@RequestBody Map<String,String> map){
        // 判断手机号和对应的验证码是否正确
        // 从前端获取手机号和验证码
        String phone = map.get("phone");
        String code = map.get("code");
        System.out.println(phone);
        System.out.println(code);
        // 从redis中取出验证码
        String realCode = stringRedisTemplate.opsForValue().get(phone);
        if (code.equals(realCode)){
            // 登录成功 删除redis中的数据
            stringRedisTemplate.delete(phone);
            return AxiosResult.success();
        }
        throw new ApiException(AxiosStatus.ERROR);
    }

}
