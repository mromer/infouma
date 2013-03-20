package com.mromero.infoshool.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mromero.infoshool.model.School;

@Controller
@RequestMapping("/schoolrest/secure/**")
public class SchoolRestSecure {

	private static final String googleApiKey = "AIzaSyAuMsrdabB5B_Tu1s39ZB4VLJqkv69iNVw";
	private static final Logger logger = LoggerFactory.getLogger(SchoolRestSecure.class);

	private List<School> schools;

	@Autowired
	private SchoolDao schoolDao;

	@RequestMapping("/getSchools")
	public @ResponseBody List<School> getSchools() {
		logger.info("petición Rest getSchools");

		if (schools == null) {
			schools = schoolDao.list();
		}

		logger.info("schools size:" + schools.size());
		return schools;
	}

	@RequestMapping("/getSchool/{schoolId}")
	public @ResponseBody School getSchool(@PathVariable Long schoolId) {    
		logger.info("petición Rest getSchool. schoolId:" + schoolId);
		School school = schoolDao.getSchool(schoolId);
		logger.info("petición Rest getSchool. school obtenida:" + school.getDescription());
		return school;
	}

	@RequestMapping("/addSchool/{schoolId}")
	public @ResponseBody String addSchool(@PathVariable Long schoolId) {    
		String result = "KO";


		return result;
	}

	@RequestMapping("/sendNotification/{schoolId}")
	public @ResponseBody String sendNotification(@PathVariable Long schoolId) {    
		Sender sender = new Sender(googleApiKey);
		Builder builder = new Message.Builder();
		builder.addData("msg", "texto de prueba");
		Message message = builder.build();
		
		String regId = "APA91bFPRSZzAz9Hw-XuFg8h2Xaeb0_rv9HwGByUzWUaBqBRl_j6B6g8SfCXSpFv-MrO1KvDl9t3LVYsflxxIER53wqV3U-YV_07fizjaPtRuXnFqlv03RGJDV_8AyNxS484gGXjGYxP6mwlGviCsslnRm3saK4vCQ";
		ArrayList<String> lRegId = new ArrayList<String>();
		lRegId.add(regId);
		
		// Envía el mensaje con 5 reintentos
		MulticastResult result;
		try {
			result = sender.send(message, lRegId, 5);

			List<Result> lResult = result.getResults();

			for (Result r : lResult) {

				if (r.getMessageId() != null) {
					String canonicalRegId = r.getCanonicalRegistrationId();
					if (canonicalRegId != null) {
						// same device has more than on registration ID: update database
					}
				} else {
					String error = r.getErrorCodeName();
					if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
						// application has been removed from device - unregister database
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return "OK";
	}

}
