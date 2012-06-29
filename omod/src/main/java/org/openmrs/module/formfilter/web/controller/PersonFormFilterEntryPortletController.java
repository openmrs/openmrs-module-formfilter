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
package org.openmrs.module.formfilter.web.controller;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Form;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterHandler;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.module.web.extension.FormEntryHandler;
import org.openmrs.web.controller.PersonFormEntryPortletController;

/**
 * Extends PersonFormEntryProtletController to manipulate the form list based on assigned filters to
 * it.
 */
public class PersonFormFilterEntryPortletController extends PersonFormEntryPortletController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
		
		super.populateModel(request, model);
		
		//Code to remove forms from formToEntryUrlMap based on assigned filters. 
		Map<Form, FormEntryHandler> entryUrlMap = (Map<Form, FormEntryHandler>) model.get("formToEntryUrlMap");
		
		for (Iterator<Form> iterator = entryUrlMap.keySet().iterator(); iterator.hasNext();) {
			Form form = (Form) iterator.next();
			if (filterForm(form, (Patient) model.get("patient")) == false) {
				iterator.remove();
			}
		}
		
		model.put("formToEntryUrlMap", entryUrlMap);
		
	}
	
	/**
	 * Functions checks whether a form can be listed under form entry tab of patient dashboard.
	 * Logic of filtering is based upon "ANDING".
	 * 
	 * @param form
	 * @param patient
	 * @return True, if all assigned filters return true or if no filters are assigned to a form.
	 * @return False, if any one filter fails condition.
	 */
	private boolean filterForm(Form form, Patient patient) {
		
		FormFilterService formFilterService = (FormFilterService) Context.getService(FormFilterService.class);
		FormFilter formFilter = formFilterService.getFormFilter(form);
		
		if (formFilter != null) {
			for (FormFilterProperty formFilterProperty : formFilter.getFormFilterProperties()) {
				try 
				{
					FormFilterHandler filterHandler = (FormFilterHandler) Class.forName(formFilterProperty.getClassName())
					        .getConstructor(String.class).newInstance(formFilterProperty.getProperties());
					if (filterHandler.shouldDisplayForm(patient, Context.getAuthenticatedUser()) == false) {
						return false;
					}
				}
				catch (Exception e) {
					log.info(e);
				}
			}
		}
		
		return true;
	}
	
}
