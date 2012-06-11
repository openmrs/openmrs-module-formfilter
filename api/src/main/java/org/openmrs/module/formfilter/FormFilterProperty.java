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

import java.util.Date;

import org.openmrs.User;

/**
 *This class holds property values related to formFilter class.
 */
public class FormFilterProperty {

	private int formFilterPropertyId;
	
	private FormFilter formFilter;
	
	private String className;
	
	private String properties;
	
	private boolean retired;
	
	private User retiredBy;
	
	private Date dateRetired;
	
	private String retireReason;
	
	private String filterName;
	
	private String filterDescription;

	
    /**
     * @return the formFilterPropertyId
     */
    public int getFormFilterPropertyId() {
    	return formFilterPropertyId;
    }

	
    /**
     * @param formFilterPropertyId the formFilterPropertyId to set
     */
    public void setFormFilterPropertyId(int formFilterPropertyId) {
    	this.formFilterPropertyId = formFilterPropertyId;
    }

	
    /**
     * @return the formFilter
     */
    public FormFilter getFormFilter() {
    	return formFilter;
    }

	
    /**
     * @param formFilter the formFilter to set
     */
    public void setFormFilter(FormFilter formFilter) {
    	this.formFilter = formFilter;
    }

	
    /**
     * @return the className
     */
    public String getClassName() {
    	return className;
    }

	
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
    	this.className = className;
    }

	
    /**
     * @return the properties
     */
    public String getProperties() {
    	return properties;
    }

	
    /**
     * @param properties the properties to set
     */
    public void setProperties(String properties) {
    	this.properties = properties;
    }

	
    /**
     * @return the retired
     */
    public boolean isRetired() {
    	return retired;
    }

	
    /**
     * @param retired the retired to set
     */
    public void setRetired(boolean retired) {
    	this.retired = retired;
    }

	
    /**
     * @return the retiredBy
     */
    public User getRetiredBy() {
    	return retiredBy;
    }

	
    /**
     * @param retiredBy the retiredBy to set
     */
    public void setRetiredBy(User retiredBy) {
    	this.retiredBy = retiredBy;
    }

	
    /**
     * @return the dateRetired
     */
    public Date getDateRetired() {
    	return dateRetired;
    }

	
    /**
     * @param dateRetired the dateRetired to set
     */
    public void setDateRetired(Date dateRetired) {
    	this.dateRetired = dateRetired;
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


	/**
     * @return the retireReason
     */
    public String getRetireReason() {
    	return retireReason;
    }

	
    /**
     * @param retireReason the retireReason to set
     */
    public void setRetireReason(String retireReason) {
    	this.retireReason = retireReason;
    }
	
	
}
