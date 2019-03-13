package com.myHospital.hospital.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 3/12/2019
 */
public class ExcelUtil<T> {

    private boolean isExcel2003(String fileName){
        return fileName.matches("^.+\\\\.(?i)(xls)$");
    }
    private boolean isExcel2007(String fileName){
        return fileName.matches("^.+\\\\.(?i)(xlsx)$");
    }

    public String validateExcel(String fileName){
        if (isExcel2003(fileName)){
            return "isExcel2003";
        }
        else if (isExcel2007(fileName)){
            return "isExcel2007";
        }else {
            return "notExcel";
        }
    }

    public HSSFWorkbook exportRecord(List<T> records, List<String> columns, String sheetName){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i=0; i<columns.size(); i++){
            hssfRow.createCell(i).setCellValue(columns.get(i));
        }
        for (int j=0;j<records.size();j++){
            hssfRow = hssfSheet.createRow(j+1);
            String record = records.get(j).toString();
            String[] recordValue = record.split(",");
            for (int i=0; i<columns.size(); i++){
                hssfRow.createCell(i).setCellValue(recordValue[i]);
            }
        }
        return hssfWorkbook;
    }
}
