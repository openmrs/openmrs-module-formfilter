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
 * This class provides gender based form filter. Form is shown on patient dashboard only if the
 * patient gender matches with filter gender property value.
 */
public class GenderFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Gender of patient.
	 */
	private String gender;
	
	//Getters and Setters
	
	/**
	 * @return gender value
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets gender value
	 * 
	 * @param gender , to set filter gender value
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	//Constructors
	
	/**
	 * Default Constructor
	 */
	public GenderFormFilter() {
		
	}
	
	/**
	 * Constructor sets this class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: gender=U
	 */
	public GenderFormFilter(String properties) {
		
		for (String string : properties.split("&")) {
			String str[] = string.split("=");
			try {
				Field field = this.getClass().getDeclaredField(str[0]);
				field.set(this, (Object) str[1]);
			}
			catch (Exception e) {
				log.info(e);
				
			}
		}
		
	}
	
	/**
	 * @see org.openmrs.module.formfilter.FormFilterHandler#shouldDisplayForm(org.openmrs.Patient,
	 *      org.openmrs.User)
	 * @param p , patient on whos dashboard forms are listed.
	 * @param u , user viewing the list on patient dashboard.
	 * @return true , if patient gender value match with this.gender
	 * @return false, if patient gender value does not match with this.gender
	 * @should display form when filter gender match with patient gender.
	 * @should not display form when filter gender does not match with patient gender.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		if (getGender().equalsIgnoreCase("U") && p.getGender() == null)
			return true;
		
		if (p.getGender().equalsIgnoreCase(gender))
			return true;
		
		return false;
	}
	
}
