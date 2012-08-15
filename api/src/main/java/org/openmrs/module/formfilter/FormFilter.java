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
import java.util.HashSet;
import java.util.Set;


import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Form;

/**
 * Its form filter object , each form has single form filter object.
 */
public class FormFilter extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 536586875337316441L;

	/**
	 * Object id.
	 */
	private int formFilterId;
	
	/**
	 * Form details.
	 */
	private Form form;
	
	/**
	 * List of assigned filters to form.
	 */
	private Set<FormFilterProperty> formFilterProperties=new HashSet<FormFilterProperty>();
	
	//setters and getters 
	
	/**
	 * Getter for form filter id
	 * 
	 * @return formFilterId of FormFilter
	 */
	public int getFormFilterId() {
		return formFilterId;
	}
	
	/**
	 * Setter form filter id.
	 * 
	 * @param formFilterId to sets object id
	 */
	public void setFormFilterId(int formFilterId) {
		this.formFilterId = formFilterId;
	}
	
	/**
	 * Getter for form.
	 * 
	 * @return form details of FormFilter
	 */
	public Form getForm() {
		return form;
	}
	
	/**
	 * Setter for form.
	 * 
	 * @param form to set FormFilter form details
	 */
	public void setForm(Form form) {
		this.form = form;
	}
	
	/**
	 * Getter for form filter properties.
	 * 
	 * @return collection of assigned filters to form
	 */
	public Set<FormFilterProperty> getFormFilterProperties() {
		return formFilterProperties;
	}
	
	/**
	 * Getter for form filter properties.
	 * 
	 * @param FormFilterProperty collection to set FormFilter filter details
	 */
	public void setFormFilterProperties(Set<FormFilterProperty> formFilterProperties) {
		this.formFilterProperties = formFilterProperties;
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	@Override
	public Integer getId() {
		return getFormFilterId();
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	@Override
	public void setId(Integer id) {
		setFormFilterId(id);
	}
	
}
