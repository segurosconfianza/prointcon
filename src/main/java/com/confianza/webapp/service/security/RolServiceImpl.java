package com.confianza.webapp.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.framework.security.PersonAttributesMapper;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	private PersonAttributesMapper personAttributesMapper;	
	
	/**
	 * @return the personAttributesMapper
	 */
	public PersonAttributesMapper getPersonAttributesMapper() {
		return personAttributesMapper;
	}

	/**
	 * @param personAttributesMapper the personAttributesMapperImpl to set
	 */
	public void setPersonAttributesMapper(PersonAttributesMapper personAttributesMapper) {
		this.personAttributesMapper = personAttributesMapper;
	}
	
	@Override
	@Transactional
	public List<Object[]> loadRoles(List<String> perfiles) {		
		return personAttributesMapper.loadRoles(perfiles);
	}
	
	@Override
	public boolean validateRoles(String perfiles) {
		
		return personAttributesMapper.validateRoles(perfiles.split(","));
	}

}
