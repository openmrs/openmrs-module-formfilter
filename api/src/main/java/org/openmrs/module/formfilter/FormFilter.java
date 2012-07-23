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
import java.util.Set;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Form;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or
 * {@link BaseOpenmrsMetadata}.
 */
public class FormFilter extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int formFilterId;
	
	private Form form;
	
	private Set<FormFilterProperty> formFilterProperties;
	
	public int getFormFilterId() {
		return formFilterId;
	}
	
	public void setFormFilterId(int formFilterId) {
		this.formFilterId = formFilterId;
	}
	
	public Form getForm() {
		return form;
	}
	
	public void setForm(Form form) {
		this.form = form;
	}
	
	public Set<FormFilterProperty> getFormFilterProperties() {
		return formFilterProperties;
	}
	
	public void setFormFilterProperties(Set<FormFilterProperty> formFilterProperties) {
		this.formFilterProperties = formFilterProperties;
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
