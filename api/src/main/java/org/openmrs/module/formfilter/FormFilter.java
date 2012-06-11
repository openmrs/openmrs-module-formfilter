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
import java.util.Date;
import java.util.Set;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Form;
import org.openmrs.User;


/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class FormFilter extends BaseOpenmrsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int formFilterId;
	
	private Form form;
	
	private User creator;
	
    private Date dateCreated;
    
    private User changedBy;
    
    private Date dateChanged;
    
    private Set<FormFilterProperty> formFilterProperties;
    
    

	/**
     * @return the formFilterId
     */
    public int getFormFilterId() {
    	return formFilterId;
    }

	
    /**
     * @param formFilterId the formFilterId to set
     */
    public void setFormFilterId(int formFilterId) {
    	this.formFilterId = formFilterId;
    }

	
    /**
     * @return the form
     */
    public Form getForm() {
    	return form;
    }

	
    /**
     * @param form the form to set
     */
    public void setForm(Form form) {
    	this.form = form;
    }

	
    /**
     * @return the creator
     */
    public User getCreator() {
    	return creator;
    }

	
    /**
     * @param creator the creator to set
     */
    public void setCreator(User creator) {
    	this.creator = creator;
    }

	
    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
    	return dateCreated;
    }

	
    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
    	this.dateCreated = dateCreated;
    }

	
    /**
     * @return the changedBy
     */
    public User getChangedBy() {
    	return changedBy;
    }

	
    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(User changedBy) {
    	this.changedBy = changedBy;
    }

	
    /**
     * @return the dateChanged
     */
    public Date getDateChanged() {
    	return dateChanged;
    }

	
    /**
     * @param dateChanged the dateChanged to set
     */
    public void setDateChanged(Date dateChanged) {
    	this.dateChanged = dateChanged;
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