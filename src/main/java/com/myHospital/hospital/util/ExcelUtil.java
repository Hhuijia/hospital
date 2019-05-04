package com.myHospital.hospital.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author: Queeney.Huang
 * @date: 3/12/2019
 */
public class ExcelUtil<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    private boolean isExcel2003(String fileName){
        log.info("********fileName-[{}]*********",fileName);
        return fileName.matches("^.+\\.(?i)(xls)$");
    }
    private boolean isExcel2007(String fileName){
        log.info("********fileName-[{}]*********",fileName);
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }

    public boolean validateExcel(String fileName){
        return isExcel2003(fileName) || isExcel2007(fileName);
    }

    public Sheet initImport(String fileName, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = null;
        Sheet sheet;
        if (isExcel2003(fileName)){
            workbook = new HSSFWorkbook(inputStream);
        }
        else if (isExcel2007(fileName)){
            workbook = new XSSFWorkbook(inputStream);
        }
        sheet = workbook != null ? workbook.getSheetAt(0) : null;
        return sheet;
    }

    public void export(HttpServletResponse response, List records, List<String> columns, String msgType) throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(msgType);
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i=0; i<columns.size(); i++){
            hssfRow.createCell(i).setCellValue(columns.get(i));
            log.info(columns.get(i));
        }
        for (int j=0;j<records.size();j++){
            hssfRow = hssfSheet.createRow(j+1);
            String record = records.get(j).toString();
            String[] recordValue = record.split(",");
            log.info("[{}]",record);
            for (int i=0; i<columns.size(); i++){
                hssfRow.createCell(i).setCellValue(recordValue[i]);
            }
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-disposition","attachment;filename="+msgType+".xls");
        hssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
