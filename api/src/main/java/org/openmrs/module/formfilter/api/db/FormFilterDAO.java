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
package org.openmrs.module.formfilter.api.db;

import org.openmrs.Form;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;

/**
 *  Database methods for {@link FormFilterService}.
 */
public interface FormFilterDAO {
	
	/*
	 * 
	 */
		
	public void saveFormFilter(FormFilter formFilter);
	
	public FormFilter getFormFilter(Form form);
	
	public FormFilter getFormFilter(int formFilterId);
	
	public void purgeFormFilter(int formFilterPropertyId);
	
	public FormFilterProperty getFormFilterProperty(int formFilterPropertyId);
	
	
}