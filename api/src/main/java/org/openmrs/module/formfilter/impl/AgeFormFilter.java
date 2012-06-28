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


public class AgeFormFilter implements FormFilterHandler {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public AgeFormFilter()
	{
		
	}
	
	public AgeFormFilter(String properties){
		for (String string : properties.split("&")) {
	        String str[]=string.split("=");
	        try {
	            Field field=this.getClass().getDeclaredField(str[0]);
	            field.set(this, (Object)Integer.parseInt(str[1]));
            }catch (Exception e) {
            	log.info(e);
            }
		}	

	}
	
	private int minimumAge;
	
	private int maximumAge;
	
		
    public int getMinimumAge() {
    	return minimumAge;
    }

	
    public void setMinimumAge(int minimumAge) {
    	this.minimumAge = minimumAge;
    }

	
    public int getMaximumAge() {
    	return maximumAge;
    }

	
    public void setMaximumAge(int maximumAge) {
    	this.maximumAge = maximumAge;
    }
	
    @Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
    	
	    if((p.getAge()>=minimumAge) && (p.getAge()<=maximumAge))
	    {
	    	return true;
	    }		
	    return false;
    }

}
