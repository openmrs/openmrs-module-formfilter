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
package org.openmrs.module.formfilter.api.impl;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Form;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.module.formfilter.api.db.FormFilterDAO;

/**
 * It is a default implementation of {@link FormFilterService}.
 */
public class FormFilterServiceImpl extends BaseOpenmrsService implements FormFilterService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private FormFilterDAO dao;
	
	/**
	 * @param dao the dao to set
	 */
	public void setDao(FormFilterDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * @return the dao
	 */
	public FormFilterDAO getDao() {
		return dao;
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#saveFormFilter(org.openmrs.module.formfilter.FormFilter)
	 */
	@Override
	public void saveFormFilter(FormFilter formFilter) {
		// TODO Auto-generated method stub
		
		formFilter.setUuid(UUID.randomUUID().toString());
		dao.saveFormFilter(formFilter);
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilter(org.openmrs.Form)
	 */
	@Override
	public FormFilter getFormFilter(Form form) {
		// TODO Auto-generated method stub
		
		FormFilter formFilter;
		try {
			formFilter = dao.getFormFilter(form);
			
		}
		catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			
			formFilter = new FormFilter();
			formFilter.setForm(form);
			formFilter.setUuid(UUID.randomUUID().toString());
			dao.saveFormFilter(formFilter);
			formFilter = dao.getFormFilter(form);
		}
		
		return formFilter;
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilter(int)
	 */
	@Override
	public FormFilter getFormFilter(int formFilterId) {
		// TODO Auto-generated method stub
		return dao.getFormFilter(formFilterId);
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#AddFormFilterProperty(int,
	 *      org.openmrs.module.formfilter.FormFilterProperty)
	 */
	@Override
	public void addFormFilterProperty(int formFilterId, FormFilterProperty formFilterProperty) {
		// TODO Auto-generated method stub
		FormFilter formFilter = dao.getFormFilter(formFilterId);
		formFilterProperty.setUuid(UUID.randomUUID().toString());
		formFilter.getFormFilterProperties().add(formFilterProperty);
		dao.saveFormFilter(formFilter);
		
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#purgeFormFilterProperty(int)
	 */
	@Override
	public void purgeFormFilterProperty(int formFilterPropertyId) {
		// TODO Auto-generated method stub		
		dao.purgeFormFilter(formFilterPropertyId);
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#getFormFilterProperty(int)
	 */
	@Override
	public FormFilterProperty getFormFilterProperty(int formFilterPropertyId) {
		// TODO Auto-generated method stub
		return dao.getFormFilterProperty(formFilterPropertyId);
	}
	
	/**
	 * @see org.openmrs.module.formfilter.api.FormFilterService#updateFormFilterProperty(org.openmrs.module.formfilter.FormFilterProperty)
	 */
	@Override
	public void updateFormFilterProperty(FormFilterProperty formFilterProperty) {
		// TODO Auto-generated method stub
		dao.updateFormFilterProperty(formFilterProperty);
		
	}
}
