package com.truebadge.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.interfaces.MediaFileControllerInterface;
import com.truebadge.models.Audio;
import com.truebadge.models.MediaFileImpl;
import com.truebadge.repositories.AudioRepository;
import com.truebadge.util.JSONConverterUtil;
import com.truebadge.util.MediaFileUtil;

@RestController
@RequestMapping("/audios")
public class AudioController implements MediaFileControllerInterface {
	@Autowired
	private AudioRepository repository;

	private String LOCAL_DESKTOP = "/Users/michielu/Desktop/";

	// Upload Audio
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public JSONObject singleFileUpload(MultipartFile multipart, @RequestParam("title") String title) {
		JSONObject audioIdJSON = MediaFileUtil.uploadAudio(repository, title, multipart);
		return audioIdJSON;
	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.POST)
	public JSONObject retrieveFile(@RequestBody JSONObject id) {
		MediaFileImpl demoAudio = MediaFileUtil.retrieveAudio(repository, (String) id.get("id"));

		// This is all a temporary way to retreieve it
		Binary document = ((Audio) demoAudio).getAudio();
		String title = demoAudio.getTitle();
		try {
			if (document != null) {
				// Testing by printing out to desktop
				FileOutputStream fileOuputStream = null;
				try {
					fileOuputStream = new FileOutputStream(LOCAL_DESKTOP + title + ".m4a"); // store extention? -- not
																							// needed bc I'll never
																							// download it like this
					fileOuputStream.write(document.getData());
				} catch (Exception e) {
					e.printStackTrace(); // TODO handle failures better
					return JSONConverterUtil.convertToJSON(false, "failure - filtoutputstream writing error");
				} finally {
					if (fileOuputStream != null) {
						try {
							fileOuputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
							return JSONConverterUtil.convertToJSON(false, "failure - could not close filestream");
						}
					}
				}
			}
			return JSONConverterUtil.convertToJSON(true, "success - audio retrieved");

		} catch (Exception e) {
			e.printStackTrace();
			return JSONConverterUtil.convertToJSON(false, "failure - could not get audio");

		}
	}

}