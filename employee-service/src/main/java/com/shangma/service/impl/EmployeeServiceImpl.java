package com.shangma.service.impl;

import com.shangma.entity.Employee;
import com.shangma.entity.EmployeeExample;
import com.shangma.mapper.EmployeeMapper;
import com.shangma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee doLogin(String phone) {
        // 查询条件
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeePhoneEqualTo(phone);
        // 去数据库中查询
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if (employees.isEmpty()) return null;

        return employees.get(0);
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.selectByExample(null);
    }
}
