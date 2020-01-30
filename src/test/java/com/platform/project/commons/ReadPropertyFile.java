package com.platform.project.commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    Properties properties = new Properties();

    public ReadPropertyFile() {
        loadProperties();
    }


    private void loadProperties() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getPropertyValue(final String key) {
        return properties.getProperty(key);
    }


    public String getdriverPath() {
        String browser = getBrowser();
        String driverPath = properties.getProperty("chrome-path");

        if (browser.equalsIgnoreCase("chrome")) {

            if (driverPath != null) {

                return driverPath;
            } else {
                throw new RuntimeException("path not found");
            }
        }


        return driverPath;
    }


    public String getdriver() {

        String driver = properties.getProperty("chrome-driver");
        if (driver != null) {
            return driver;
        } else {
            throw new RuntimeException("driver not found");
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) {
            return browser;
        } else {
            throw new RuntimeException("Browser not found");
        }
    }

    public String getUserName() {
        String userName = properties.getProperty("userName");
        if (userName != null) {
            return userName;
        } else {
            throw new RuntimeException("Username not found");
        }
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("password not found");
        }
    }

    public String getFilePath() {
        String filePath = properties.getProperty("filePath");
        return filePath;
    }

    public String getFileName() {
        String fileName = properties.getProperty("fileName");
        return fileName;
    }

    public String getSheetName() {
        String sheetName = properties.getProperty("sheetName");
        return sheetName;
    }

    public String getAccountPath(){
        String accountpath = properties.getProperty("accountpath");
        return  accountpath;
    }

    public String getAccountName(){
        String accountname = properties.getProperty("accountname");
        return  accountname;
    }

    public String getAccountSheet(){
        String accountsheet = properties.getProperty("accountsheet");
        return  accountsheet;
    }
}
