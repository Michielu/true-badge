package com.truebadge.interfaces;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MediaFileControllerInterface {
	public JSONObject singleFileUpload(MultipartFile multipart, @RequestParam("title") String title);
	public String retrieveFile(@RequestBody JSONObject id);
}

