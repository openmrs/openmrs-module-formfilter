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
import org.openmrs.module.formfilter.FormFilter;
import org.openmrs.module.formfilter.FormFilterProperty;
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
	 * Tests formFilter service functionality to save a formFilter.
	 */
	@Test
	public void shouldSaveFormFilter(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		FormFilter formFilter=new FormFilter();
		formFilter.setForm(Context.getFormService().getForm(2));
		formFilter.setFormFilterId(4);
		filterService.saveFormFilter(formFilter);
		assertNotNull("Should save the formFilter",filterService.getFormFilter(4));
		
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
	
	
	/**
	 * Test formFilterService functionality to add a filter to Form. 
	 */
	@Test
	public void shouldAddFormFilterProperty(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		FormFilterProperty filterProperty=new FormFilterProperty();
		filterProperty.setClassName("org.openmrs.module.formfilter.impl.PrivilegeFormFilter");
		filterProperty.setFilterName("Privilege Filter");
		filterProperty.setProperties("privilege=Administrator");
		filterService.addFormFilterProperty(1, filterProperty);
		Assert.assertEquals("FormFilter should have mentioned number of fillter", filterService.getFormFilter(1).getFormFilterProperties().size() , 3);
	
	}
	
	
	/**
	 * Tests formFilterService functionality to remove a filter to a form.
	 */
	@Test
	public void shouldPurgeFormFilterProperty(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		filterService.purgeFormFilterProperty(2);
		Assert.assertEquals(filterService.getFormFilter(1).getFormFilterProperties().size() , 1);
	}
	
	
	/**
	 * Tests the FormFilterService functionality to return a FormFilterProperty. 
	 */
	@Test
	public void shouldReturnFormFilterProperty(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		Assert.assertNotNull(filterService.getFormFilterProperty(1));
	}
	
	/**
	 * Tests the FormFilterService functionality to return a FormFilterProperty. 
	 */
	@Test
	public void shouldNotReturnFormFilterProperty(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		Assert.assertNull(filterService.getFormFilterProperty(10));
	}
	
	/**
	 * Tests the FormFilterService functionality to update a FormFilterProperty
	 */
	@Test
	public void shouldUpdateFormFilterProperty(){
		FormFilterService filterService=Context.getService(FormFilterService.class);
		FormFilterProperty formFilterProperty=filterService.getFormFilterProperty(1);
		formFilterProperty.setFilterName("New Filter");
		filterService.updateFormFilterProperty(formFilterProperty);
		
		Assert.assertEquals(filterService.getFormFilterProperty(1).getFilterName(), "New Filter");
	}
	
	
}