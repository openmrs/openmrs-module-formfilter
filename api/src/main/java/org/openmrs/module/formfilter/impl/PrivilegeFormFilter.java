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
import org.openmrs.Privilege;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;

/**
 * Form filter based on User privileges.
 * Form is shown on patient dashboard only if the user has filter specific privilege.
 */
public class PrivilegeFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * User privilege
	 */
	private String privilege;
	
	//Getters and Setters
	
	/**
	 * Returns user required privilege
	 * 
	 * @return privilege name
	 */
	public String getPrivilege() {
		return privilege;
	}
	
	/**
	 * 
	 * Sets user required privilege.
	 * 
	 * @param privilege
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	//Constructors	
	
	/**
	 * Default Constructor.
	 */
	public PrivilegeFormFilter() {
		
	}
	
	/**
	 * Constructor sets this class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: privilege=Add Form
	 */
	public PrivilegeFormFilter(String properties) {
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
	 * @return True ,if user has specified privilege.
	 * @return False,if user does not have specified privilege.
	 * @should display form when user has mentioned privilege.
	 * @should not display form when user does not have mentioned privilege.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		
		for (Role role : u.getAllRoles()) {
			if (role.getPrivileges().contains(new Privilege(privilege))) {
				return true;
			}
		}
		
		return false;
	}
	
}
