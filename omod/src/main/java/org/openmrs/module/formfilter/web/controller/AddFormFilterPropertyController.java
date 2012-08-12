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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.CohortService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller handling requests to add or edit filter.
 */
@Controller
public class AddFormFilterPropertyController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Handles request related to adding Form filter.
	 * 
	 * @param model
	 * @param formFilterId
	 * @param formFilterPropertyId if , 0 adds new property or else returns respective property.
	 * @should support add new filter functionality
	 * @should support edit filter functionality
	 */
	@RequestMapping(value = "/module/formfilter/addformproperty", method = RequestMethod.GET)
	public void addFormFilter(ModelMap model,
	                          @RequestParam("filterId") Integer formFilterId,
	                          @RequestParam(value = "filterPropertyId", required = false, defaultValue = "0") int formFilterPropertyId) {
		
		FormFilterService formFilterService = (FormFilterService) Context.getService(FormFilterService.class);
		
		FormFilterProperty formFilterProperty;
		Map<String, String> map = new HashMap<String, String>();
		
		model.addAttribute("formFilter", formFilterService.getFormFilter(formFilterId));
		
		//Adding list of available roles and privileges.
		UserService userService = Context.getUserService();
		model.addAttribute("roles", userService.getAllRoles());
		model.addAttribute("privileges", userService.getAllPrivileges());
		
		//Adding cohort list. 
		CohortService cohortService = Context.getCohortService();
		model.addAttribute("cohorts", cohortService.getAllCohorts());
		
		/*If formFilterPropertyId is not equal to 0 , then will add formFilterProperty to show 
		  page as edit filter option or else pass new object to add a new filter.*/

		if (formFilterPropertyId != 0) {
			formFilterProperty = formFilterService.getFormFilterProperty(formFilterPropertyId);
			for (String string : formFilterProperty.getProperties().split("&")) {
				String str[] = string.split("=");
				map.put(str[0], str[1]);
			}
		} else {
			formFilterProperty = new FormFilterProperty();
		}
		
		model.addAttribute("properties", map);
		model.addAttribute("formFilterProperty", formFilterProperty);
		
	}
	
	/**
	 * Handles request to save or update a filter to db.
	 * 
	 * @param formFilterProperty
	 * @param request
	 * @return to viewformfilter page to see list of all assigned filter's to a form.
	 * @should add new FormFilterProperty
	 * @should update FormFilterProperty
	 */
	@RequestMapping(value = "/module/formfilter/addformproperty", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("formfilterproperty") FormFilterProperty formFilterProperty,
	                       BindingResult result, SessionStatus status, HttpServletRequest request) {
		
		String propertyType = request.getParameter("propertyType");
		int formFilterId = Integer.parseInt(request.getParameter("formFilterId"));
		FormFilterService formFilterService = (FormFilterService) Context.getService(FormFilterService.class);
		
		//Based on selected propertyType option in form , className and properties of formFilterPropertyType
		//object are set manually.
		if (propertyType.equalsIgnoreCase("AgeProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
			formFilterProperty.setProperties("minimumAge=" + request.getParameter("minimumAge") + "&maximumAge="
			        + request.getParameter("maximumAge"));
		} else if (propertyType.equalsIgnoreCase("GenderProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.GenderFormFilter");
			formFilterProperty.setProperties("gender=" + request.getParameter("gender"));
		} else if (propertyType.equalsIgnoreCase("DateProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.DateFormFilter");
			formFilterProperty.setProperties("date=" + request.getParameter("date") + "&show="
			        + request.getParameter("show"));
		} else if (propertyType.equalsIgnoreCase("RoleProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.RoleFormFilter");
			formFilterProperty.setProperties("role=" + request.getParameter("role"));
		} else if (propertyType.equalsIgnoreCase("PrivilegeProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.PrivilegeFormFilter");
			formFilterProperty.setProperties("privilege=" + request.getParameter("privilege"));
		} else if (propertyType.equalsIgnoreCase("CohortProperty")) {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.CohortFormFilter");
			formFilterProperty.setProperties("cohort=" + request.getParameter("cohort"));
		}
		
		//if id of formFilterProperty object is 0 , add filter as new or else update it.
		if (formFilterProperty.getFormFilterPropertyId() == 0) {
			formFilterService.addFormFilterProperty(formFilterId, formFilterProperty);
		} else {
			formFilterService.saveFormFilterProperty(formFilterProperty);
		}
		
		return "redirect:viewformfilter.form?formFilterId=" + formFilterId;
		
	}
	
}
