package com.server.edu.userservice.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.server.edu.userservice.entity.User;

/**
 * 
 * @Description:Excel导出
 * @author:lifeiyue
 * @time:2018年5月31日 下午5:43:36
 */
@Component
public class ExcelExport
{
    
    static final String[] cellTitle = {"序号", "学工号", "用户名", "真实姓名", "地址", "昵称",
        "头像", "性别", "手机号", "身份证号", "出生日期", "电子邮件", "备注"};
    
    @SuppressWarnings("resource")
    public void exportUserInfoList(List<User> userList)
        throws IOException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDDhhmmss");
        String now = dateFormat.format(new Date());
        // 导出文件路径
        String basePath = "D:/";
        // 文件名
        String exportFileName = "userInfo_" + now + ".xlsx";
        // 声明一个工作薄
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workBook.createSheet();
        workBook.setSheetName(0, "学生信息");
        // 创建表格标题行 第一行
        XSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < cellTitle.length; i++)
        {
            titleRow.createCell(i).setCellValue(cellTitle[i]);
        }
        // 插入需导出的数据
        for (int i = 0; i < userList.size(); i++)
        {
            User user = userList.get(i);
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(user.getUserId());
            row.createCell(2).setCellValue(user.getUserName());
            row.createCell(3).setCellValue(user.getRealName());
            row.createCell(4).setCellValue(user.getAddress());
            row.createCell(5).setCellValue(user.getAliaName());
            row.createCell(6).setCellValue(user.getHeadPortRait());
            row.createCell(7).setCellValue(user.getSex());
            row.createCell(8).setCellValue(user.getTelephone());
            row.createCell(9).setCellValue(user.getIdentity());
            row.createCell(10).setCellValue(user.getBirthday());
            row.createCell(11).setCellValue(user.getEmail());
            row.createCell(12).setCellValue(user.getRemarks());
        }
        File file = new File(basePath + exportFileName);
        // 文件输出流
        FileOutputStream outStream = new FileOutputStream(file);
        workBook.write(outStream);
        outStream.flush();
        outStream.close();
        System.out.println("导出2007文件成功！文件导出路径：--" + basePath + exportFileName);
    }
    
}
