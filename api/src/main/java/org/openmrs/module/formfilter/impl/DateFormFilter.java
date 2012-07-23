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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;

/**
 * This class will provide current date based form filter.
 */
public class DateFormFilter implements FormFilterHandler {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Default Constructor
	 */
	public DateFormFilter() {
		
	}
	
	/**
	 * Constructor sets this class field values.
	 * 
	 * @param properties ,string property from FormFilterProperty class in key=value based format
	 *            Example: date=20/10/2012&show=before
	 */
	public DateFormFilter(String properties) {
		
		for (String string : properties.split("&")) {
			String str[] = string.split("=");
			try {
				Field field = this.getClass().getDeclaredField(str[0]);
				field.set(this, (Object) str[1]);
			}
			catch (Exception e) {
				log.error("Error while setting filter properties:", e);
			}
		}
	}
	
	private String date;
	
	private String show;
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getShow() {
		return show;
	}
	
	public void setShow(String show) {
		this.show = show;
	}
	
	/**
	 * @see org.openmrs.module.formfilter.FormFilterHandler#shouldDisplayForm(org.openmrs.Patient,
	 *      org.openmrs.User)
	 * @return True , if current date fall before/after the specified filter date.
	 * @return False, if current date does not fall before/after the specified filter date.
	 * @should display form when current date is before/after mentioned filter date.
	 * @should not display form when current date is not before/after mentioned filter date.
	 */
	@Override
	public boolean shouldDisplayForm(Patient p, User u) {
		SimpleDateFormat odf = new SimpleDateFormat("dd/MM/yyyy");
		Date filterDate = new Date();
		
		try {
			filterDate = odf.parse(date);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		if (show.equalsIgnoreCase("before") && new Date().before(filterDate)) {
			return true;
		} else if (show.equalsIgnoreCase("after") && new Date().after(filterDate)) {
			return true;
		}
		return false;
	}
	
}
