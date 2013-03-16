package com.mromero.infoshool.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mromero.infoshool.NavigationRules;
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
	
	public String addSchool() {		
		school = new School();		
		logger.info("School almacenada");
		return NavigationRules.TO_ADD;		
	}
	
	public String addDeleteSchool() {
		logger.info("BORRAMOS: id:" + school.getId() + " desc:" + school.getDescription());
		schoolDao.delete(school);
		
		return NavigationRules.TO_LIST;		
	}	
	
	/**
	 * Cargamos los valores
	 * */
	public String editChargeSchool(Long id) {		
				
		logger.info("editChargeSchool inicio. id:" + id);
		
		school = schoolDao.getSchool(id);
		
		logger.info("editChargeSchool. desc:" + school.getDescription());
		
		return NavigationRules.TO_EDIT;
	}
	
	
	/**
	 * Cargamos los valores
	 * */
	public String deleteChargeSchool(Long id) {
		
		logger.info("editChargeSchool inicio. id:" + id);
		
		school = schoolDao.getSchool(id);
		
		logger.info("deleteChargeSchool. id:" + school.getId());
		
		return NavigationRules.TO_DELETE;
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
