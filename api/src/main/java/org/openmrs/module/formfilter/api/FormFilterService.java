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
 * This service has methods related to form filter.
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
     
	/*
	 * 
	 * 
	 */
	public void saveFormFilter(FormFilter formFilter);
	
	public FormFilter getFormFilter(Form form);
	
	public FormFilter getFormFilter(int formFilterId);
	
	public void addFormFilterProperty(int formFilterId,FormFilterProperty formFilterProperty);
	
	public void purgeFormFilterProperty(int formFilterPropertyId);
	
	public FormFilterProperty getFormFilterProperty(int formFilterPropertyId);
	
	public void updateFormFilterProperty(FormFilterProperty formFilterProperty);


	
	
}