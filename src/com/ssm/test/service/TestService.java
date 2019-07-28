package com.ssm.test.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import com.ssm.test.pojo.Employee;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

@Service
public class TestService {

	public String index() {
		System.out.println("testService——index 执行");
		return "success";
	}

	public String outPut(HttpServletResponse response) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("/resource/employeeTemplate2.xlsx");
		List<Employee> data = new ArrayList();
		Context context = new Context();
		Employee employee = new Employee();
		employee.setDepartmentName("客服部");
		employee.setName("wang");
		employee.setEmail("asdasd@qq.com");
		employee.setEmployeeId("123");
		employee.setPhone("214141412421");
		data.add(employee);
        context.putVar("list",data);
        ServletOutputStream out = null;
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
            response.setContentType("application/octet-stream");
			out = response.getOutputStream();
			JxlsHelper.getInstance().processTemplate(is, out, context);			
//           response.setContentType("application/vnd..ms-excel;charset=UTF-8");
//            BufferedInputStream bis = null;
//            BufferedOutputStream bos = null;
//            OutputStream fos = null;
//            InputStream fis = null;
//            String path = "F:/out.xlsx";
//            File uploadFile = new File(path);
//            fis = new FileInputStream(uploadFile);
//            bis = new BufferedInputStream(fis);
//            fos = response.getOutputStream();
//            bos = new BufferedOutputStream(fos);
//            // 弹出下载对话框
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
//             bos.write(buffer, 0, bytesRead);
//            }
//            bos.flush();
//            fis.close();
//            bis.close();
//            //fos.close();
//            bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
				is.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
			
		}
        
		return null;
	}
	
	public String outPut2(HttpServletResponse response) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("/resource/employeeTemplate2.xlsx");
		List<Employee> data = new ArrayList();
		Map<String,Object> model = new HashMap();
		//Context context = new Context();
		Employee employee = new Employee();
		employee.setDepartmentName("客服部");
		employee.setName("wang");
		employee.setEmail("asdasd@qq.com");
		employee.setEmployeeId("123");
		employee.setPhone("214141412421");
		data.add(employee);
       // context.putVar("list",data);
        model.put("list",data);
        XLSTransformer transformer = new XLSTransformer();
        String templateFileName = "F:/文档/employeeTemplate.xlsx";
		String serverFilePath = "F:/out.xlsx";
		// 生成Excel文件
        try {
			transformer.transformXLS(templateFileName, model, serverFilePath );
			response.reset();
//            response.setHeader("Content-disposition", "attachment;success=true;filename =" + URLEncoder.encode(outExcelName, "utf-8"));
			response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
			BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream fos = null;
            InputStream fis = null;
            File uploadFile = new File(serverFilePath);
            fis = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead = 0;
            byte[] buffer = new byte[819200];
            while ((bytesRead = bis.read(buffer, 0, 819200)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
 
        // 清空服务端生成的excel文件，节约空间
            File file = new File(serverFilePath);
            if (file.exists()) {
                file.delete();
            }        

		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
