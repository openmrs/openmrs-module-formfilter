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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

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
		initializeInMemoryDatabase();
		executeDataSet("formFilterTestDataSyncCreateTest.xml");
		authenticate();
	}
	
	/**
	 * Tests whether form filter service loads success fully or not.
	 */
	@Test
	public void shouldSetupContext() {
		Assert.assertNotNull(Context.getService(FormFilterService.class));
	}
	
	/**
	 * Tests formFilter service functionality to save a formFilter.
	 */
	@Test
	@Verifies(value = "should save the FormFilter", method = "saveFormFilter(FormFilter)")
	public void saveFormFilter_shouldSaveFormFilter() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		FormFilter formFilter = new FormFilter();
		formFilter.setForm(Context.getFormService().getForm(2));
		formFilter.setFormFilterId(4);
		filterService.saveFormFilter(formFilter);
		Assert.assertNotNull(filterService.getFormFilter(4));
		
	}
	
	/**
	 * Test condition to load formfilter with form as parameter.
	 */
	@Test
	@Verifies(value = "should get FormFilter with given Form", method = "getFormFilter(Form)")
	public void getFormFilter_shouldGetFormFilterWithGivenForm() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		Form form = new Form(1);
		Assert.assertNotNull(filterService.getFormFilter(form));
	}
	
	/**
	 * Test condition to load formfilter with formFilterId as input parameter.
	 */
	@Test
	@Verifies(value = "should get FormFilter with given FilterId", method = "getFormFilter(FormFilterId)")
	public void getFormFilter_shouldGetFormFilterWithGivenFilterId() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		Assert.assertNotNull(filterService.getFormFilter(1));
	}
	
	
	/**
	 * Test formFilterService functionality to add a filter to Form.
	 */
	@Test
	@Verifies(value = "should add FormFilterProperty", method = "addFormFilterProperty(FormFilterId,FilterProperty)")
	public void addFormFilterProperty_shouldAddFormFilterProperty() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		FormFilterProperty filterProperty = new FormFilterProperty();
		filterProperty.setClassName("org.openmrs.module.formfilter.impl.PrivilegeFormFilter");
		filterProperty.setFilterName("Privilege Filter");
		filterProperty.setProperties("privilege=Administrator");
		filterService.addFormFilterProperty(1, filterProperty);
		Assert.assertEquals(filterService.getFormFilter(1).getFormFilterProperties().size(), 3);
	}
	
	/**
	 * Tests formFilterService functionality to remove a filter to a form.
	 */
	@Test
	@Verifies(value = "should purge FormFilterProperty with given id", method = "purgeFormFilterProperty(FormFilterPropertyId)")
	public void purgeFormFilterProperty_shouldPurgeFormFilterPropertyWithGivenId() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		filterService.purgeFormFilterProperty(2);
		Assert.assertEquals(filterService.getFormFilter(1).getFormFilterProperties().size(), 1);
	}
	
	/**
	 * Tests the FormFilterService functionality to return a FormFilterProperty.
	 */
	@Test
	@Verifies(value = "should return FormFilterProperty with given id", method = "getFormFilterProperty(FormFilterPropertyId)")
	public void getFormFilterProperty_shouldReturnFormFilterPropertyWithGivenId() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		Assert.assertNotNull(filterService.getFormFilterProperty(1));
	}
	
	
	/**
	 * Tests the FormFilterService functionality to save a FormFilterProperty
	 */
	@Test
	@Verifies(value = "should save FormFilterProperty", method = "saveFormFilterProperty(FormFilterProperty)")
	public void saveFormFilterProperty_shouldSaveFormFilterProperty() {
		FormFilterService filterService = Context.getService(FormFilterService.class);
		FormFilterProperty formFilterProperty = filterService.getFormFilterProperty(1);
		formFilterProperty.setFilterName("New Filter");
		filterService.saveFormFilterProperty(formFilterProperty);
		Assert.assertEquals(filterService.getFormFilterProperty(1).getFilterName(), "New Filter");
	}
	
}
