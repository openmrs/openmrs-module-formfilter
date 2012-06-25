package org.openmrs.module.formfilter.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	public void AddFormFilter(ModelMap model,@RequestParam("filterId") Integer formFilterId,
	                          @RequestParam(value="filterPropertyId" ,required=false,defaultValue="0") int formFilterPropertyId) {
		
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		
		FormFilterProperty formFilterProperty;
		Map map=new HashMap();
		
		model.addAttribute("formFilter",formFilterService.getFormFilter(formFilterId));
		
		if(formFilterPropertyId != 0){
			formFilterProperty=formFilterService.getFormFilterProperty(formFilterPropertyId);
		    for (String string : formFilterProperty.getProperties().split("&")) {
		    	String str[]=string.split("=");
		    	map.put(str[0], str[1]);	        
	        }
		}else{
			formFilterProperty=new FormFilterProperty();
		}	
		
		model.addAttribute("properties", map);		
		model.addAttribute("formFilterProperty",formFilterProperty);
		
		
				
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
		if (formFilterProperty.getFormFilterPropertyId() == 0) {	
			formFilterService.addFormFilterProperty(formFilterId, formFilterProperty);
		} else {
			formFilterService.updateFormFilterProperty(formFilterProperty);
		}
		
		return "redirect:viewformfilter.form?formFilterId="+formFilterId;
		
		
		
	}
	

}
