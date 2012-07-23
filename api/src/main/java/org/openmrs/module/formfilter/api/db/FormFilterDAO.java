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
 * Database methods for {@link FormFilterService}.
 */
public interface FormFilterDAO {
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#saveFormFilter(FormFilter)
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilter(Form)
	 * @see org.openmrs.module.formfilter.api.FormFilterService#addFormFilterProperty(int,
	 *      FormFilterProperty)
	 */
	public void saveFormFilter(FormFilter formFilter);
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilter(Form)
	 */
	public FormFilter getFormFilter(Form form);
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilter(int)
	 */
	public FormFilter getFormFilter(int formFilterId);
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#purgeFormFilterProperty(int)
	 */
	public void purgeFormFilter(int formFilterPropertyId);
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilterProperty(int)
	 */
	public FormFilterProperty getFormFilterProperty(int formFilterPropertyId);
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#updateFormFilterProperty(FormFilterProperty)
	 */
	public void updateFormFilterProperty(FormFilterProperty formFilterProperty);
	
}
