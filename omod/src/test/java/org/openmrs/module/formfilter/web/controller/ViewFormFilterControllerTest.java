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

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.test.Verifies;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ModelMap;

/**
 * Tests {@link ViewFormFilterController}
 */
@Ignore
public class ViewFormFilterControllerTest extends
		BaseModuleWebContextSensitiveTest {

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
	 * @see {@link ViewFormFilterController#viewFormFilter(ModelMap, Integer, Integer)}
	 */
	@Test
	@Verifies(value = "should return FormFilter by form id", method = "viewFormFilter(ModelMap, Integer, Integer)")
	public void viewFormFilter_ShouldReturnFormFilterByFormId() {
		ViewFormFilterController controller = new ViewFormFilterController();
		ModelMap model = new ModelMap();
		controller.viewFormFilter(model, "1", null);
		Assert.assertNotNull(((FormFilter) model.get("formfilter")));
	}

	/**
	 * @see {@link ViewFormFilterController#viewFormFilter(ModelMap, Integer, Integer)}
	 */
	@Test
	@Verifies(value = "should return FormFilter by form filter id", method = "viewFormFilter(ModelMap, Integer, Integer)")
	public void viewFormFilter_ShouldReturnFormFilterByFormFilterId() {
		ViewFormFilterController controller = new ViewFormFilterController();
		ModelMap model = new ModelMap();
		controller.viewFormFilter(model, null, "1");
		Assert.assertNotNull(((FormFilter) model.get("formfilter")));
	}

	/**
	 * @see {@link ViewFormFilterController#deleteFormFilterProperty(ModelMap, javax.servlet.http.HttpSession, int, int)}
	 */
	@Test
	@Verifies(value = "should delete FormFilterProperty by id",method="deleteFormFilterProperty(ModelMap, javax.servlet.http.HttpSession, int, int)")
	public void deleteFormFilterProperty_shouldDeleteFormFilterPropertyById() {
		FormFilterService formFilterService = Context
				.getService(FormFilterService.class);
		ViewFormFilterController controller = new ViewFormFilterController();
		controller.deleteFormFilterProperty(null, new MockHttpSession(), 1, 0);
		Assert.assertNull(formFilterService.getFormFilterProperty(1));
	}

}
