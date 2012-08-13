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
package org.openmrs.module.formfilter.api;

import org.openmrs.Form;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service has methods related to form filter module.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(FormFilterService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface FormFilterService extends OpenmrsService {
	
	/**
	 * Insert or update given form filter.
	 * 
	 * @param formFilter to save to db.
	 * @should save the FormFilter
	 */
	public void saveFormFilter(FormFilter formFilter);
	
	/**
	 * Gets form filter based on form. If no form filter is present ,will create and return
	 * respective FormFilter.
	 * 
	 * @param Form details to returns its FormFilter
	 * @return FormFilter
	 * @should get FormFilter with given Form
	 */
	@Transactional(readOnly = true)
	public FormFilter getFormFilter(Form form);
	
	/**
	 * Get form filter based on form filter id.
	 * 
	 * @param formFilterId of FormFilter which needs to be returned
	 * @return FormFilter
	 * @should get FormFilter with given FilterId
	 */
	@Transactional(readOnly = true)
	public FormFilter getFormFilter(int formFilterId);
	
	/**
	 * Adds a new FormFilterProperty to database with respective to given FormFilter.
	 * 
	 * @param formFilterId of FormFilter to which FormFilterProperty need to be added
	 * @param formFilterProperty with all filter details
	 * @should add FormFilterProperty
	 */
	public void addFormFilterProperty(int formFilterId, FormFilterProperty formFilterProperty);
	
	/**
	 * Deletes Form Filter Property from database.
	 * 
	 * @param formFilterPropertyId of FormFilterProperty which needs to be removed from database completely
	 * @should purge FormFilterProperty with given id
	 */
	public void purgeFormFilterProperty(int formFilterPropertyId);
	
	/**
	 * Returns formFilterProperty based on its id.
	 * 
	 * @param formFilterPropertyId of FormFilterProperty which is to be returned
	 * @return FormFilterProperty
	 * @should return FormFilterProperty with given id
	 */
	@Transactional(readOnly = true)
	public FormFilterProperty getFormFilterProperty(int formFilterPropertyId);
	
	/**
	 * Updates existing FormFilterProperty to database.
	 * 
	 * @param formFilterProperty which is to be saved to database
	 * @should save FormFilterProperty
	 */
	public void saveFormFilterProperty(FormFilterProperty formFilterProperty);
	
}
