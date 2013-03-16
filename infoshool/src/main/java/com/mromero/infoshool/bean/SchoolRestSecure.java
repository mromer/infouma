package com.mromero.infoshool.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mromero.infoshool.model.School;

@Controller
@RequestMapping("/schoolrest/secure/**")
public class SchoolRestSecure {

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
	
}
