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
package org.openmrs.module.formfilter.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Tests {@link $ FormFilterService} .
 */
public class FormFilterServiceTest extends BaseModuleContextSensitiveTest {
	
	/**
	 * Creates sample data set in context for testing.
	 * 
	 * @throws Exception
	 */
	@Before
	public void loadDataSet() throws Exception {
		executeDataSet("formFilterTestDataSyncCreateTest.xml");
	}
	
	/**
	 * Tests whether form filter service loads success fully or not.
	 */
	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(FormFilterService.class));
	}
	
	/**
	 * Test condition to load formfilter with form as parameter.
	 */
	@Test
	public void shouldGetFormFilterWithForm(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		Form form=new Form(1);
		Assert.assertNotNull(filterService.getFormFilter(form));
	}
	
	/**
	 * Test condition to load formfilter with formFilterId as input parameter.
	 */
	@Test
	public void shouldGetFormFilterWithFilterId(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		Assert.assertNotNull(filterService.getFormFilter(1));
	}
	
	/**
	 * Test condition to load formfilter with formFilterId as input parameter.
	 */
	@Test
	public void shouldNotGetFormFilterWithFilterId(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		Assert.assertNull(filterService.getFormFilter(2));
	}
	
	
}