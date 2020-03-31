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
 * This class will provide patient age based form filter.
 */
public class AgeFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * This holds patient minimum age.
	 */
	private int minimumAge;
	
	/**
	 * This holds patient maximum age.
	 */
	private int maximumAge;
	
	//Getters and Setters
	
	/**
	 * @return minimumAge of filter
	 */
	public int getMinimumAge() {
		return minimumAge;
	}
	
	/**
	 * Sets maximumAge
	 * 
	 * @param maximumAge ,sets filter maximumAge
	 */
	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}
	
	/**
	 * @return maximumAge of filter
	 */
	public int getMaximumAge() {
		return maximumAge;
	}
	
	/**
	 * Sets maximumAge
	 * 
	 * @param maximumAge , sets filter maximumAge
	 */
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}
	
	//Constructors
	
	/**
	 * Default Constructor
	 */
	public AgeFormFilter() {
		
	}
	
	/**
	 * Constructor sets class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: minimumAge=value&maximumAge=value
	 */
	public AgeFormFilter(String properties) {
		for (String string : properties.split("&")) {
			String str[] = string.split("=");
			try {
				Field field = this.getClass().getDeclaredField(str[0]);
				field.set(this, (Object) Integer.parseInt(str[1]));
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
	 * @return True , if patient age lies between minimumAge and maximumAge.
	 * @return False, if patient age does not between minimumAge and maximumAge.
	 * <strong>Should</strong> display form when patient age lies between filter minimum and maximum age values.
	 * <strong>Should</strong> not display form when patient age does not lie between filter minimum and maximum age
	 *         values.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		
		if ((p.getAge() >= minimumAge) && (p.getAge() <= maximumAge)) {
			return true;
		}
		return false;
	}
	
}
