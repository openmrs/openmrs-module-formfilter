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
 * Form filter based on User privileges. Form is shown on patient dashboard only if the user has
 * filter specific privilege.
 */
public class PrivilegeFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * User privilege
	 */
	private String privileges;
	
	//Getters and Setters
	
	/**
	 * Returns user required privileges
	 * 
	 * @return privileges, list of privileges separated by comma(,) 
	 */
	public String getPrivileges() {
		return privileges;
	}
	
	/**
	 * Sets user required privilege.
	 * 
	 * @param privilege , to set filter privileges separated by comma(,)
	 */
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
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
	 *            Example: privileges=View Form,Manage Form
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
	 * @param p , patient on whos dashboard forms are listed.
	 * @param u , user viewing the list on patient dashboard.
	 * @return True ,if user has any of specified privileges.
	 * @return False,if user does not have any  specified privileges.
	 * @should display form when user has any mentioned privileges.
	 * @should not display form when user does not have any mentioned privileges.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {

		String privilege[]=privileges.split(",");
		for (String privilegeName : privilege) {
		if (u.hasPrivilege(privilegeName.trim())) {
			return true;
		}
		}

		return false;
	}
	
}