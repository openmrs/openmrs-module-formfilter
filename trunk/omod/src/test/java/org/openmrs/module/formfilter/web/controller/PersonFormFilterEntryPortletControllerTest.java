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

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.api.context.Context;
import org.openmrs.module.web.extension.FormEntryHandler;
import org.openmrs.test.Verifies;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;

/**
 * Tests {@link PersonFormFilterEntryPortletController}
 */
public class PersonFormFilterEntryPortletControllerTest extends BaseModuleWebContextSensitiveTest {
	
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
	 * @see {@link PersonFormFilterEntryPortletController#populateModel(HttpServletRequest, Map)}
	 */
	@Test
	@Verifies(value = "should return FormList", method = "populateModel(HttpServletRequest, Map)")
	public void populateModel_shouldReturnFormList() {
		PersonFormFilterEntryPortletController controller = new PersonFormFilterEntryPortletController();
		ModelMap model = new ModelMap();
		MockHttpServletRequest request = new MockHttpServletRequest();
		model.addAttribute("patient", Context.getPatientService().getPatient(2));
		model.addAttribute("person", Context.getPersonService().getPerson(2));
		controller.populateModel(request, model);
		//Its returns 0 number of forms as PersonFormFilterEntryPortletController looks for org.openmrs.module.web.extension.FormEntryHandler extensions.
		Assert.assertEquals(0, ((Map<Form, FormEntryHandler>) model.get("formToEntryUrlMap")).size());
		
	}
	
}
