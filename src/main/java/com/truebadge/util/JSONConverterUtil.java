package com.truebadge.util;

import org.json.simple.JSONObject;

public class JSONConverterUtil {

	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(boolean isSuccessful, String message) {
		JSONObject obj = new JSONObject(); 
		 obj.put("success", isSuccessful);	
		 obj.put("message", message);	
		 
		 return obj;
	 }	
	
	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(boolean isSuccessful, String message, String[] keys, String[] data) {
		JSONObject obj = new JSONObject();

		if(data.length != keys.length) { //TODO create errors class 
			 isSuccessful = false;
			 message="Keys and data lenghts do not match";
		 }
		
		 
		 obj.put("success", isSuccessful);	
		 obj.put("message", message);	
		 if(isSuccessful) {
			 for(int i = 0; i < keys.length; i++) {
				 obj.put(keys[i], data[i]);
			 }
		 }	 
		 
		 return obj;
	 }	
	
	
	
}
