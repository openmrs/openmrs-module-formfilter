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
package org.openmrs.module.formfilter;

import java.io.Serializable;
import org.openmrs.BaseOpenmrsObject;

/**
 *This class holds filter property values related to {@link FormFilter}.
 */
public class FormFilterProperty extends BaseOpenmrsObject implements Serializable   {

	
    private static final long serialVersionUID = 1L;


	private int formFilterPropertyId;
	
	
	private String className;
	
	private String properties;
	
	private String filterName;
	
	private String filterDescription;
	
	
	

	
    public int getFormFilterPropertyId() {
    	return formFilterPropertyId;
    }

	
    public void setFormFilterPropertyId(int formFilterPropertyId) {
    	this.formFilterPropertyId = formFilterPropertyId;
    }

	
    public String getClassName() {
    	return className;
    }

	
    public void setClassName(String className) {
    	this.className = className;
    }

	
    public String getProperties() {
    	return properties;
    }

	
    public void setProperties(String properties) {
    	this.properties = properties;
    }

	
    public String getFilterName() {
    	return filterName;
    }

	
    public void setFilterName(String filterName) {
    	this.filterName = filterName;
    }

	
    public String getFilterDescription() {
    	return filterDescription;
    }

	
    public void setFilterDescription(String filterDescription) {
    	this.filterDescription = filterDescription;
    }

	@Override
    public Integer getId() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setId(Integer arg0) {
	    // TODO Auto-generated method stub
	    
    }

	
    	
	
}
