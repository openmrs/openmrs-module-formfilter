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
import org.openmrs.Cohort;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.CohortService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilterHandler;

;

/**
 * Form Filter based on patient defined in cohort.
 */
public class CohortFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Holds the cohort name in which the patient should be defined.
	 */
	private String cohort;
	
	//Getters and Setters
	
	/**
	 * 
	 * Returns cohort name.
	 * 
	 * @return cohort name type string.
	 */
	public String getCohort() {
		return cohort;
	}
	
	/**
	 * 
	 * Sets cohort name.
	 * 
	 * @param cohortName
	 */
	public void setCohort(String cohortName) {
		this.cohort = cohortName;
	}
	
	//Constructors
	
	/**
	 * Default Constructor.
	 */
	public CohortFormFilter() {
		
	}
	
	/**
	 * Constructor sets class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: cohort=Male Cohort
	 */
	public CohortFormFilter(String properties) {
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
	 * @return True if the patient is defined in filter cohort otherwise False.
	 * @should display form when patient is defined in mentioned cohort.
	 * @should not display form when patient is not defined in mentioned cohort.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		CohortService cohortService = Context.getCohortService();
		Cohort assignedCohort = cohortService.getCohort(cohort);
		if (assignedCohort.contains(p)) {
			return true;
		}
		return false;
	}
	
}
