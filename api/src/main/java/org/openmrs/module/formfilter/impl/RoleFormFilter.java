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
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;

/**
 * Form filtering based on User roles.
 */
public class RoleFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
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
	
	//fields
	
	private String role;
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @see org.openmrs.module.formfilter.FormFilterHandler#shouldDisplayForm(org.openmrs.Patient,
	 *      org.openmrs.User)
	 * @return True ,if user has mentioned role.
	 * @return False,if user does not have mentioned role.
	 * @should display form when user has mentioned role.
	 * @should not display form when user does not have mentioned role.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		
		if (u.getAllRoles().contains(new Role(role))) {
			return true;
		}
		
		return false;
	}
	
}
