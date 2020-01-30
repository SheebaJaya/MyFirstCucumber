package com.platform.project.commons;

import com.platform.project.model.Registration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {


    public static List<List<String>> readExcel(String filePath, String fileName, String sheetName) {
        File file = new File(filePath + fileName);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        InputStream inputStream = getClass().getResourceAsStream(fileName);
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        Workbook workbook = null;
        try {
            if (fileExtensionName.equals(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);

            } else if (fileExtensionName.equals(".xlx")) {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();

        List<List<String>> tabArray = new ArrayList<>();

        //Create a loop over all the rows of excel file to read it

        for (int i = 1; i <= rowCount; i++) {
            List<String> eachCredential = new ArrayList<>();
            Row row = sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {
                eachCredential.add(row.getCell(j).getStringCellValue());
            }
            tabArray.add(eachCredential);
        }

        return tabArray;
    }

//    public static void main(String[] args) {
//        List<Registration> registrations = readRegistrations("/Users/sheeba/IdeaProjects/WebsiteTesting/src/test/resources/", "AccountInput.xlsx", "Account");
//        System.out.println(registrations);
//    }

    public static List<Registration> readRegistrations(String filePath, String fileName, String sheetName) {
        File file = new File(filePath + fileName);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        InputStream inputStream = getClass().getResourceAsStream(fileName);
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        Workbook workbook = null;
        try {
            if (fileExtensionName.equals(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);

            } else if (fileExtensionName.equals(".xlx")) {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();

        List<Registration> registrations = new ArrayList<>();

        //Create a loop over all the rows of excel file to read it

        for (int i = 1; i <= rowCount; i++) {
            Registration registration = new Registration();
            Row row = sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                switch (j) {
                    case 0:
                        registration.setGender(cell.getStringCellValue());
                        break;
                    case 1:
                        registration.setFirstName(cell.getStringCellValue());
                        break;
                    case 2:
                        registration.setLastName(cell.getStringCellValue());
                        break;
                    case 3:
                        registration.setDob(cell.getStringCellValue());
                        break;
                    case 4:
                        registration.setEmail(cell.getStringCellValue());
                        break;
                    case 5:
                        registration.setCompany(cell.getStringCellValue());
                        break;
                    case 6:
                        registration.setStreetAddress(cell.getStringCellValue());
                        break;
                    case 7:
                        registration.setSubrub(cell.getStringCellValue());
                        break;
                    case 8:
                        registration.setPostcode(cell.getStringCellValue());
                        break;
                    case 9:
                        registration.setCity(cell.getStringCellValue());
                        break;
                    case 10:
                        registration.setState(cell.getStringCellValue());
                        break;
                    case 11:
                        registration.setCountry(cell.getStringCellValue());
                        break;
                    case 12:
                        registration.setTelephone(String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue()));
                        break;
                    case 13:
                        registration.setFax(String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue()));
                        break;
                    case 14:
                        registration.setNewsletter(cell.getStringCellValue());
                        break;
                    case 15:
                        registration.setPassword(cell.getStringCellValue().trim());
                        break;
                    case 16:
                        registration.setConfirmation(cell.getStringCellValue().trim());
                        break;
                }
            }
            registrations.add(registration);
        }

        return registrations;
    }


}
