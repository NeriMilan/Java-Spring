package com.shangma.service;

import com.shangma.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 从数据库中查询手机号
     */
    Employee doLogin(String phone);
    List<Employee> findAll();
}
