package org.openmrs.module.formfilter.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.impl.xpath.regex.REUtil;
import org.openmrs.api.FormService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilterProperty;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void editFormFilter(ModelMap model,@RequestParam("formId") Integer formId) {
		
		FormService formService = Context.getFormService();
		model.addAttribute("form",formService.getForm(formId));
			
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public String onSubmit(@ModelAttribute("formfilterproperty")FormFilterProperty formFilterProperty,BindingResult result, SessionStatus status,HttpServletRequest request){
		FormService formService = Context.getFormService();
		
		if (request.getParameter("propertyType")== "AgeProperty") {
			formFilterProperty.setClassName("org.openmrs.module.formfilter.impl.AgeFormFilter");
			formFilterProperty.setProperties("minimumAge="+request.getParameter("minimumAge")+"&maximumAge="+request.getParameter("maximumAge"));	        
        }	
		
		return "redirect:manage";
	}
	

}
