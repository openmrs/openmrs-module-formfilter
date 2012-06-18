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
@RequestMapping(value = "/module/formfilter/addformproperty")
public class AddFormFilterPropertyController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public void AddFormFilter(ModelMap model,@RequestParam("formFilterId") Integer formFilterId) {
		System.out.println("IN GET");
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		model.addAttribute("formfilter",formFilterService.getFormFilter(formFilterId));
		
			
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public String onSubmit(@ModelAttribute("formfilterproperty")FormFilterProperty formFilterProperty,BindingResult result, SessionStatus status,HttpServletRequest request){
		System.out.println(request.getParameter("\n\n\n"+request.getParameter("formFilterId")));
		FormService formService = Context.getFormService();
		
		if (request.getParameter("propertyType")== "AgeProperty") {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
			formFilterProperty.setProperties("minimumAge="+request.getParameter("minimumAge")+"&maximumAge="+request.getParameter("maximumAge"));	        
        }else if(request.getParameter("propertyType")== "GenderProperty"){
        	formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
        	formFilterProperty.setProperties("gender="+request.getParameter("gender"));
        }
		
		
		return "manage.list";
	}
	

}
