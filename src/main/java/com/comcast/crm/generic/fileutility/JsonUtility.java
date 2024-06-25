package com.comcast.crm.generic.fileutility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Throwable {
		FileReader fr = new FileReader("./configAppData/appjsondata.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);
		JSONObject map = (JSONObject) obj;
		String data = map.get(key).toString();
		return data;
	}
}
