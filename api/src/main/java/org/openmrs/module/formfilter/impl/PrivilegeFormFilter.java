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
 * Form filter based on User privileges.
 */
public class PrivilegeFormFilter implements FormFilterHandler {

	protected Log log = LogFactory.getLog(getClass());
	
	//Constructors
	
	public PrivilegeFormFilter(){
		
	}
	
	public PrivilegeFormFilter(String properties){
		String str[] = properties.split("=");
		try {
				Field field = this.getClass().getDeclaredField(str[0]);
				field.set(this, (Object) str[1]);
			}
			catch (Exception e) {
				log.info(e);
			}
	}	

	
	
	private String privilege;

	
	
    public String getPrivilege() {
    	return privilege;
    }

	
    public void setPrivilege(String privilege) {
    	this.privilege = privilege;
    }

    /**
     * @return True ,if user has specified privilege.
     * False,if user does not have specified privilege. 
     * 
     * @see org.openmrs.module.formfilter.FormFilterHandler#shouldDisplayForm(org.openmrs.Patient, org.openmrs.User)
     */
	@Override
    public boolean shouldDisplayForm(Patient p, User u) {

		if(u.hasPrivilege(privilege))
		{
			return true;
		}
		
	    return false;
    }

}
