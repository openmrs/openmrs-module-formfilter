package org.openmrs.module.formfilter.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.FormService;
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

@Controller
public class AddFormFilterPropertyController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	/** 
	 *  
	 * @param model
	 * @param formFilterId
	 */	
	@RequestMapping(value = "/module/formfilter/addformproperty",method = RequestMethod.GET)
	public void AddFormFilter(ModelMap model,@RequestParam("filterId") Integer formFilterId) {
		
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		model.addAttribute("formFilter",formFilterService.getFormFilter(formFilterId));			
	}
	
	
	/**
	 * 
	 * @param formFilterProperty
	 * @param result
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/module/formfilter/addformproperty",method = RequestMethod.POST )
	public String onSubmit(@ModelAttribute("formfilterproperty")FormFilterProperty formFilterProperty,BindingResult result, SessionStatus status,HttpServletRequest request){
		
		String propertyType=request.getParameter("propertyType");
		int formFilterId=Integer.parseInt(request.getParameter("formFilterId"));
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		
		if (propertyType.equalsIgnoreCase("AgeProperty")) {			
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
			formFilterProperty.setProperties("minimumAge="+request.getParameter("minimumAge")+"&maximumAge="+request.getParameter("maximumAge"));	        
        }else if(propertyType.equalsIgnoreCase("GenderProperty")){
        	
        	formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
        	formFilterProperty.setProperties("gender="+request.getParameter("gender"));
        }
		
		formFilterService.addFormFilterProperty(formFilterId, formFilterProperty);
		
		return "redirect:viewformfilter.form?formFilterId="+formFilterId;
	}
	

}
