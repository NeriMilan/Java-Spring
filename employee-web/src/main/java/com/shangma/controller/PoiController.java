package com.shangma.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.shangma.entity.Employee;
import com.shangma.service.EmployeeService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("emp")
public class PoiController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("writeExcel")

    public ResponseEntity<byte[]> writeExcel() throws IOException {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest.xlsx");

//自定义标题别名
        writer.addHeaderAlias("name", "员工ID");
        writer.addHeaderAlias("age", "员工姓名");
        writer.addHeaderAlias("examDate", "考试时间");
        writer.addHeaderAlias("score", "员工手机");
        writer.addHeaderAlias("isPass", "员工密码");
        writer.addHeaderAlias("examDate", "考试时间");

// 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);

// 合并单元格后的标题行，使用默认标题样式
        writer.merge(4, "一班成绩单");

// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        // 查找所有的员工
//
//        // 1.获取数据
//        List<Employee> employees = employeeService.findAll();
//
//        // 2.创建工作簿
//        Workbook workbook = new XSSFWorkbook();
//        // 3.创建页
//        Sheet sheet = workbook.createSheet("员工信息表");
//        // 创建表头
//        Row titleRow = sheet.createRow(0);
//
//        List<String> strings = Arrays.asList("员工ID", "员工姓名", "员工地址", "员工手机", "入职时间", "员工密码", "员工薪水", "头像地址");
//        for (int i = 0; i < strings.size(); i++) {
//            Cell cell = titleRow.createCell(i);
//            cell.setCellValue(strings.get(i));
//        }
//
//
//        // 4.创建行(根据数据的数量创建对应的行)
//        for (int i = 0; i < employees.size(); i++) {
//            // 创建对应的行
//            Row row = sheet.createRow(i + 1);
//            // 获取员工
//            Employee employee = employees.get(i);
//            // 5.创建每一行中的单元格
//            // 6.设置单元格中的数据
//            Cell idCell = row.createCell(0);
//            idCell.setCellValue(employee.getEmployeeId());
//            Cell nameCell = row.createCell(1);
//            nameCell.setCellValue(employee.getEmployeeName());
//            Cell addressCell = row.createCell(2);
//            addressCell.setCellValue(employee.getEmployeeAddress());
//            Cell phoneCell = row.createCell(3);
//            phoneCell.setCellValue(employee.getEmployeePhone());
//            Cell timeCell = row.createCell(4);
//            timeCell.setCellValue(sdf.format(employee.getEmployeeTime()));
//            Cell passwordCell = row.createCell(5);
//            passwordCell.setCellValue(employee.getEmployeePassword());
//            Cell salaryCell = row.createCell(6);
//            salaryCell.setCellValue(employee.getEmployeeSalary());
//            Cell avatarCell = row.createCell(7);
//            avatarCell.setCellValue(employee.getEmployeeAvatar());
//
//        }
        // 7.写到本地
//        FileOutputStream fos = new FileOutputStream("E:\\员工信息.xlsx");
//        workbook.write(fos);
//        fos.close();
//        workbook.close();
        // 把文件写入到内存中
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        workbook.write(stream);
//        byte[] bytes = stream.toByteArray();
//        // 设置头信息
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("员工信息表.xlsx", "utf-8"));
//
//        // 文件下载的方式导出
//        // 注意:文件下载的时候，必须是同步方式的请求。异步方式不可以下载
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
//        return responseEntity;
    }
}

