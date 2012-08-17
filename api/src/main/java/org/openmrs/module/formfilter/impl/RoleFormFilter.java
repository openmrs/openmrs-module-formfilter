/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.formfilter.impl;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;

/**
 * Form filtering based on User roles. Form is shown on patient dashboard only if the user role
 * matches with filter mentioned role property value.
 */
public class RoleFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	//fields
	
	/**
	 * User required roles.
	 */
	private String roles;
	
	//Getters and Setters
	
	/**
	 * Returns user required roles.
	 * 
	 * @return roles , list separated by ','
	 */
	public String getRoles() {
		return roles;
	}
	
	/**
	 * Sets user required roles.
	 * 
	 * @param roles, set roles separated by ','
	 */
	public void setRole(String roles) {
		this.roles = roles;
	}
	
	//Constructors 
	
	/**
	 * Default Constructor.
	 */
	public RoleFormFilter() {
		
	}
	
	/**
	 * Constructor sets this class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: role=Developer
	 */
	public RoleFormFilter(String properties) {
		
		String str[] = properties.split("=");
		try {
			Field field = this.getClass().getDeclaredField(str[0]);
			field.set(this, (Object) str[1]);
		}
		catch (Exception e) {
			log.info(e);
		}
	}
	
	/**
	 * @see org.openmrs.module.formfilter.FormFilterHandler#shouldDisplayForm(org.openmrs.Patient,
	 *      org.openmrs.User)
	 * @param p , patient on whos dashboard forms are listed.
	 * @param u , user viewing the list on patient dashboard.
	 * @return True ,if user has any mentioned roles.
	 * @return False,if user does not have any mentioned roles.
	 * @should display form when user has any mentioned roles.
	 * @should not display form when user does not have any mentioned roles.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {

		String role[]=roles.split(",");
		for (String roleName : role) {
			if (u.hasRole(roleName.trim())) {
				return true;
			}
		}		
		return false;
	}
	
}