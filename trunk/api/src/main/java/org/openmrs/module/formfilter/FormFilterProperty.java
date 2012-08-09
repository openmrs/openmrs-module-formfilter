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
 * This class holds filter property values related to {@link FormFilter}.
 */
@SuppressWarnings("serial")
public class FormFilterProperty extends BaseOpenmrsObject implements Serializable {
	
	/**
	 * object id.
	 */
	private int formFilterPropertyId;
	
	/**
	 * Full classified name of filter type. Example:
	 * org.openmrs.module.formfilter.impl.AgeFormFilter
	 */
	private String className;
	
	/**
	 * Filter properties are stored in form of key value pair. Example: minimumAge=10&maximumAge=30
	 */
	private String properties;
	
	/** Filter name. */
	private String filterName;
	
	/** Filter Description. */
	private String filterDescription;
	
	//Getters and Setters
	
	/**
	 * Gets the form filter property id.
	 * 
	 * @return the form filter property id
	 */
	public int getFormFilterPropertyId() {
		return formFilterPropertyId;
	}
	
	/**
	 * Sets the form filter property id.
	 * 
	 * @param formFilterPropertyId the new form filter property id
	 */
	public void setFormFilterPropertyId(int formFilterPropertyId) {
		this.formFilterPropertyId = formFilterPropertyId;
	}
	
	/**
	 * Gets the class name.
	 * 
	 * @return the fully classified class name
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * Sets the class name.
	 * 
	 * @param className to set the filter class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * Gets the properties.
	 * 
	 * @return properties defined in a filter
	 */
	public String getProperties() {
		return properties;
	}
	
	/**
	 * Sets the properties.
	 * 
	 * @param properties to set the filter properties
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
	/**
	 * Gets the filter name.
	 * 
	 * @return the filter name
	 */
	public String getFilterName() {
		return filterName;
	}
	
	/**
	 * Sets the filter name.
	 * 
	 * @param filterName to set the filter name
	 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	/**
	 * Gets the filter description.
	 * 
	 * @return the filter description
	 */
	public String getFilterDescription() {
		return filterDescription;
	}
	
	/**
	 * Sets the filter description.
	 * 
	 * @param filterDescription to set the filter description
	 */
	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	@Override
	public Integer getId() {
		return getFormFilterPropertyId();
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	@Override
	public void setId(Integer id) {
		setFormFilterPropertyId(id);
	}
	
}
