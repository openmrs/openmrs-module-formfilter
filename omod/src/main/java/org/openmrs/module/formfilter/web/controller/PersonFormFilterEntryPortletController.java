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

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Form;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.Extension;
import org.openmrs.module.Extension.MEDIA_TYPE;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterHandler;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.module.web.FormEntryContext;
import org.openmrs.module.web.extension.FormEntryHandler;
import org.openmrs.util.OpenmrsUtil;
import org.openmrs.web.controller.PersonFormEntryPortletController;

/**
 *
 */
public class PersonFormFilterEntryPortletController extends PersonFormEntryPortletController  {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
		
		if (model.containsKey("formToEntryUrlMap")) {
			return;
		}
		Person person = (Person) model.get("person");
		if (person == null)
			throw new IllegalArgumentException("This portlet may only be used in the context of a Person");
		FormEntryContext fec = new FormEntryContext(person);
		Map<Form, FormEntryHandler> entryUrlMap = new TreeMap<Form, FormEntryHandler>(new Comparator<Form>() {
			
			public int compare(Form left, Form right) {
				int temp = left.getName().toLowerCase().compareTo(right.getName().toLowerCase());
				if (temp == 0)
					temp = OpenmrsUtil.compareWithNullAsLowest(left.getVersion(), right.getVersion());
				if (temp == 0)
					temp = OpenmrsUtil.compareWithNullAsGreatest(left.getId(), right.getId());
				return temp;
			}
		});
		List<Extension> handlers = ModuleFactory.getExtensions("org.openmrs.module.web.extension.FormEntryHandler",
		    MEDIA_TYPE.html);
		if (handlers != null) {
			for (Extension ext : handlers) {
				FormEntryHandler handler = (FormEntryHandler) ext;
				Collection<Form> toEnter = handler.getFormsModuleCanEnter(fec);
				if (toEnter != null) {
					for (Form form : toEnter) {
						if(filterForm(form, (Patient)model.get("patient"))){							
							entryUrlMap.put(form, handler);
						}
					}
				}
			}
		}
		model.put("formToEntryUrlMap", entryUrlMap);
		model.put("anyUpdatedFormEntryModules", handlers != null && handlers.size() > 0);
	}
	
	private boolean filterForm(Form form,Patient patient) {
		
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		FormFilter formFilter=formFilterService.getFormFilter(form);
		
		if(formFilter!=null)
		{
		for (FormFilterProperty formFilterProperty : formFilter.getFormFilterProperties()) {
		try {
				FormFilterHandler filterHandler = (FormFilterHandler) Class.forName(formFilterProperty.getClassName()).getConstructor(String.class).newInstance(formFilterProperty.getProperties());
					if (filterHandler.shouldDisplayForm(patient, Context.getAuthenticatedUser())==false){
							return false;
					}
				}
				catch (Exception e) {
				// TODO Auto-generated catch block
				log.info(e);
				}     
        	}
		}		
		
	    return true;
    }
	
}
