package com.test;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.file.FileMode;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.test.DateUtil.getWeekly;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/7/18 16:09
 */
public class Exceltest {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\GooGoo\\Desktop\\xs.xlsx");
        List<Map<String, Object>> maps = reader.readAll();
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }

    }
//    public static void main(String[] args) throws Exception {
//        Workbook wb = new Workbook();
//        wb.loadFromFile("D:\\运维记录表1.xls");
//        Worksheet worksheet = wb.getWorksheets().get(0);
//        String week = "星期日";
//        List<String> test = getWeekly("2020-10-01 14:19:29","2021-04-30 00:00:00",week);
//        for (int i = 0; i <test.size() ; i++) {
//            Worksheet emptySheet = wb.createEmptySheet(test.get(i));
//            emptySheet.copyFrom(worksheet);
////            IXLSRange ixlsRange = emptySheet.get(4, 3);
//            CellRange cellRange = emptySheet.getCellRange(4, 3);
//            cellRange.setValue(test.get(i));
//            if (i!=0){
//                if (i%3==0){
//                    CellRange cellRange1 = emptySheet.getCellRange(20, 6);
//                    cellRange1.setValue("日志文件过大,清理一下日志");
//                }
//                if (i%5==0){
//                    CellRange cellRange2 = emptySheet.getCellRange(15, 6);
//                    cellRange2.setValue("日志文件过大,清理一下日志");
//                }
//            }
//        }
////        for (String s : test) {
////            Worksheet emptySheet = wb.createEmptySheet(s);
////            emptySheet.copyFrom(worksheet);
////            IXLSRange ixlsRange = emptySheet.get(4, 3);
////            ixlsRange.setValue(s);
////        }
//
//        wb.saveToFile("D:\\Copy6.xlsx",FileFormat.Version2013);
//
//
////        ExcelReader reader = ExcelUtil.getReader("D:\\运维记录表1.xls");
////        Workbook workbook = reader.getWorkbook();
////        Sheet sheet = workbook.getSheetAt(0);
////        String week = "星期日";
//
////
////        OutputStream out =  new FileOutputStream("D:\\运维记录表1112.xls");
//////                new OutputStreamWriter(new FileOutputStream("D:\\运维记录表1111.xls"));
////        workbook.write(out);
//
//
//    }
}
