package com.kcc.kccm_project.service.logic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class jsonToXlsxConverter
{
    private ArrayList<Item> mItems = new ArrayList<Item>();

    private void saveExcel()
    {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet(); // create New Sheet

        Row row = sheet.createRow(0); // create new Row
        Cell cell;

        cell = row.createCell(0); // create Cell no.1
        cell.setCellValue("이름"); // input Cell no.1 value

        cell = row.createCell(1); // create Cell no.2
        cell.setCellValue("나이"); // input Cell no.2 value

        for(int i = 0; i < mItems.size() ; i++){ // input Data Excel
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(mItems.get(i).getName());
            cell = row.createCell(1);
            cell.setCellValue(mItems.get(i).getAge());
        }

        File xlsFile = new File(getExternalFilesDir(null),"test.xls");

        try
        {
            FileOutputStream os = new FileOutputStream(xlsFile);
            workbook.write(os); // 외부 저장소에 엑셀 파일 생성
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),xlsFile.getAbsolutePath()+"에 저장되었습니다",Toast.LENGTH_SHORT).show();

        Uri path = Uri.fromFile(xlsFile);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/excel");
        shareIntent.putExtra(Intent.EXTRA_STREAM,path);
        startActivity(Intent.createChooser(shareIntent,"엑셀 내보내기"));
    }
}
