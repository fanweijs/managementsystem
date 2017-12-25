package com.company.utils;

import com.company.model.RewardPublish;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * @author 柏欢欢
 * @on 2017/12/23
 * 数据导出工具类
 */
public class ExportExcel {
	public static void exportData(List<RewardPublish> list, String path) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();//创建一个工作簿对象
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("员工奖惩信息表");//创建一个工作表对象
		HSSFRow hssfRow = hssfSheet.createRow(0);
		HSSFCell hssfCell = hssfRow.createCell(0);
		/* 列名 */
		String[] titles = {"员工号",  "奖惩标记", "奖惩信息", "奖惩时间"};
		/* for循环生成列名 */
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);
			hssfCell.setCellValue(titles[i]);
		}
		/* 填充数据 */
		int rowIndex = 1;
		for (RewardPublish rewardPublish : list) {
			hssfRow = hssfSheet.createRow(rowIndex);
			hssfCell = hssfRow.createCell(0);
			hssfCell.setCellValue(rewardPublish.getAccount());
			hssfCell = hssfRow.createCell(1);
			hssfCell.setCellValue(rewardPublish.getFlag());
			hssfCell = hssfRow.createCell(2);
			hssfCell.setCellValue(rewardPublish.getRp_name());
			hssfCell = hssfRow.createCell(3);
			hssfCell.setCellValue(rewardPublish.getRp_time().toString());
			rowIndex++;
		}
		    File file = new File(path + "\\员工奖惩信息.xls");
			OutputStream output = null;
			try {
				output = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				hssfWorkbook.write(output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
