package com.mromero.infoshool.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mromero.infoshool.model.School;

@Component("schoolHome")
public class SchoolHome {

	private static final Logger logger = LoggerFactory.getLogger(SchoolHome.class);

	private School school = new School();
	private List<School> schools;

	@Autowired
	private SchoolDao schoolDao;

	
	public String getMessage() {
		logger.info("Returning message from school home bean");
		return "Hello from Spring";
	}	

	public School getSchool() {
		return school;
	}

	public void saveSchool() {
		schoolDao.save(school);
		school = new School();
		invalidateSchools();
		logger.info("School almacenada");
		System.out.println("School almacenada SO");
	}

	private void invalidateSchools() {
		schools = null;
	}

	public List<School> getSchools() {
		if (schools == null) {
			schools = schoolDao.list();
		}
		return schools;
		
	}
}
