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
import org.junit.Test;
import org.openmrs.web.test.BaseWebContextSensitiveTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ModelMap;

/**
 * Tests the {@link AddFormFilterPropertyController}
 */
public class AddFormFilterPropertyControllerTest extends BaseWebContextSensitiveTest {
	
	public AddFormFilterPropertyControllerTest() {
	}
	
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
	
	@Test
	public void shouldAddFormFilter() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setParameter("filterId", "1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		AddFormFilterPropertyController controller = new AddFormFilterPropertyController();
		controller.addFormFilter(new ModelMap(), 1, 0);
		Assert.assertTrue(response.getContentAsString().contains("male cohort"));
		
	}
	
}
