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

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.test.Verifies;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.ui.ModelMap;

/**
 * Tests {@link FormFilterManageController}
 */
@Ignore
public class FormFilterManageControllerTest extends BaseModuleWebContextSensitiveTest {
	
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
	 * @see {@link FormFilterManageController#manage(ModelMap)}
	 */
	@Test
	@Verifies(value = "should return FormList ", method = "manage(ModelMap)")
	public void manage_shouldReturnFormList() {
		FormFilterManageController controller = new FormFilterManageController();
		ModelMap model = new ModelMap();
		controller.manage(model);
		//TODO return form list using FormFilterManageController#manage 
		Assert.assertEquals(0, ((List<Form>) model.get("formList")).size());
	}
	
}
