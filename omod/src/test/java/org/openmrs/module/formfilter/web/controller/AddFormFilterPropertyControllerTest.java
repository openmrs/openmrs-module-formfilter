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

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.test.Verifies;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;

/**
 * Tests the {@link AddFormFilterPropertyController}
 */
public class AddFormFilterPropertyControllerTest extends BaseModuleWebContextSensitiveTest {
	
	/**
	 * Creates sample data set in context for testing.
	 * 
	 * @throws Exception
	 */
	@Before
	public void loadDataSet() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("formFilterTestDataSyncCreateTest.xml");
		authenticate();
	}
	
	/**
	 * @see {@link AddFormFilterPropertyController#addFormFilter(ModelMap, Integer, int)}
	 */
	@Test
	@Verifies(value = "should support add new filter functionality", method = "addFormFilter(ModelMap,Integer,int)")
	public void addFormFilter_shouldSupportAddNewFilterFunctionality() {
		
		AddFormFilterPropertyController controller = new AddFormFilterPropertyController();
		ModelMap model = new ModelMap();
		controller.addFormFilter(model, 1, null);
		Assert.assertNotNull(((FormFilter) model.get("formFilter")));
		Assert.assertNull(((FormFilterProperty) model.get("formFilterProperty")).getFilterName());
	}
	
	/**
	 * @see {@link AddFormFilterPropertyController#addFormFilter(ModelMap, Integer, int)}
	 */
	@Test
	@Verifies(value = "should support edit filter functionality", method = "addFormFilter(ModelMap,Integer,int)")
	public void addFormFilter_shouldSupportEditFilterFunctionality() {
		
		AddFormFilterPropertyController controller = new AddFormFilterPropertyController();
		ModelMap model = new ModelMap();
		controller.addFormFilter(model, 1, "1");
		Assert.assertNotNull(((FormFilter) model.get("formFilter")));
		Assert.assertNotNull(((FormFilterProperty) model.get("formFilterProperty")));
	}
	
	/**
	 * @see {@link AddFormFilterPropertyController#onSubmit(FormFilterProperty, org.springframework.validation.BindingResult, org.springframework.web.bind.support.SessionStatus, javax.servlet.http.HttpServletRequest) }
	 */
	@Test
	@Verifies(value = "should add new FormFilterProperty", method = "onSubmit(FormFilterProperty, org.springframework.validation.BindingResult, org.springframework.web.bind.support.SessionStatus, javax.servlet.http.HttpServletRequest)")
	public void onSubmit_shouldAddNewFormFilterProperty() {
		
		AddFormFilterPropertyController controller = new AddFormFilterPropertyController();
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("formFilterId", "1");
		request.addParameter("propertyType", "AgeProperty");
		request.addParameter("minimumAge", "10");
		request.addParameter("maximumAge", "30");
		
		FormFilterProperty formFilterProperty = new FormFilterProperty();
		formFilterProperty.setFilterName("Test Filter");
		formFilterProperty.setFilterDescription("This filter used for testing.");
		
		controller.onSubmit(formFilterProperty, null, null, request);
		
		FormFilterService formFilterService = Context.getService(FormFilterService.class);
		
		FormFilterProperty temp = new FormFilterProperty();
		Set<FormFilterProperty> assignedFilters = formFilterService.getFormFilter(1).getFormFilterProperties();
		for (FormFilterProperty filterProperty : assignedFilters) {
			if (filterProperty.getFilterName() == "Test Filter")
				temp = filterProperty;
		}
		
		Assert.assertNotNull(temp);
	}
	
	/**
	 * @see {@link AddFormFilterPropertyController#onSubmit(FormFilterProperty, org.springframework.validation.BindingResult, org.springframework.web.bind.support.SessionStatus, javax.servlet.http.HttpServletRequest) }
	 */
	@Test
	@Verifies(value = "should update FormFilterProperty", method = "onSubmit(FormFilterProperty, org.springframework.validation.BindingResult, org.springframework.web.bind.support.SessionStatus, javax.servlet.http.HttpServletRequest)")
	public void onSubmit_shouldUpdateFormFilterProperty() {
		
		AddFormFilterPropertyController controller = new AddFormFilterPropertyController();
		FormFilterService formFilterService = Context.getService(FormFilterService.class);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("formFilterId", "1");
		request.addParameter("propertyType", "AgeProperty");
		request.addParameter("minimumAge", "10");
		request.addParameter("maximumAge", "30");
		
		FormFilterProperty formFilterProperty = formFilterService.getFormFilterProperty(2);
		
		controller.onSubmit(formFilterProperty, null, null, request);
		
		Assert.assertEquals("org.openmrs.module.formfilter.impl.AgeFormFilter", formFilterService.getFormFilterProperty(2)
		        .getClassName());
	}
	
}
