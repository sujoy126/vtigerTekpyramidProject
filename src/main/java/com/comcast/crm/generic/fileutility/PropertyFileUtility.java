package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

import pathFile.FilePath;

public class PropertyFileUtility {
	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./configAppData/commonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data = pobj.getProperty(key);
		
		return data;
	}

}
