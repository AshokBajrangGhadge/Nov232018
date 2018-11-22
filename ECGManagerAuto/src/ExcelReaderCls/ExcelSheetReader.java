package ExcelReaderCls;


import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetReader 
{
	public static XSSFWorkbook f;
	public  Sheet getSheetName(String filePath,String sheetName) throws IOException
	{
		FileInputStream fs=new FileInputStream(filePath);
		this.f=new XSSFWorkbook(fs);
		XSSFSheet sh = this.f.getSheet(sheetName);
		FileOutputStream fout=new FileOutputStream(filePath);
		this.f.write(fout);		
		return sh;
    }
}