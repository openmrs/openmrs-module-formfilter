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

import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.FormService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilterUtil;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 * Responsible for the functional requirement  of viewformfilter.jsp
 */
@Controller
public class ViewFormFilterController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Adds property to web page to include Jquery or not. 
	 * 
	 * @see FormFilterUtil#includeJquery()	 
	 */
	@ModelAttribute("includeJquery")
	public boolean useJquery(WebRequest request){
		return FormFilterUtil.includeJquery();
	}
	
	/**
	 * Returns Form Filter Object with assigned filters to it. If form filter does not exists for a
	 * form , it create's and returns FormFilter object to web page. Returns Form Filter for a given
	 * form Id or form filter Id.
	 * 
	 * @param model
	 * @param formId
	 * @param formFilterId
	 * <strong>Should</strong> return FormFilter by form id
	 * <strong>Should</strong> return FormFilter by form filter id
	 */
	@RequestMapping(value = "/module/formfilter/viewformfilter", method = RequestMethod.GET)
	public void viewFormFilter(ModelMap model,
	                           @RequestParam(value = "formId", required = false) String formId,
	                           @RequestParam(value = "formFilterId", required = false) String formFilterId) {
		FormFilterService formFilterService = (FormFilterService) Context.getService(FormFilterService.class);
		if (formId != null) {
			FormService formService = Context.getFormService();
			model.addAttribute("formfilter", formFilterService.getFormFilter(formService.getForm(Integer.parseInt(formId))));
		} else {
			model.addAttribute("formfilter", formFilterService.getFormFilter(Integer.parseInt(formFilterId)));
		}
		
	}
	
	/**
	 * Handles request in deleting(permanently) a filter with respective to a form.
	 * 
	 * @param model
	 * @param httpSession
	 * @param formFilterPropertyId
	 * @param formFilterId
	 * @return to formfilter
	 * <strong>Should</strong> delete FormFilterProperty by id
	 */
	@RequestMapping(value = "/module/formfilter/deleteFilter", method = RequestMethod.GET)
	public String deleteFormFilterProperty(ModelMap model, HttpSession httpSession,
	                                       @RequestParam(value = "formFilterPropertyId") int formFilterPropertyId,
	                                       @RequestParam(value = "formFilterId") int formFilterId) {
		
		FormFilterService formFilterService = (FormFilterService) Context.getService(FormFilterService.class);
		formFilterService.purgeFormFilterProperty(formFilterPropertyId);
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "formfilter.filterDeleted");
		return "redirect:viewformfilter.form?formFilterId=" + formFilterId;
		
	}
	
}
