package org.openmrs.module.formfilter.web.controller;


import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.FormService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.api.FormFilterService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewFormFilterController  {

	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/formfilter/viewformfilter", method = RequestMethod.GET)
	public void editFormFilter(ModelMap model,
	                           @RequestParam(value="formId", required =false ,defaultValue="0") Integer formId,
	                           @RequestParam(value="formFilterId", required =false ,defaultValue="0") Integer formFilterId
	                           ) {
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		if(formId != 0){
		FormService formService = Context.getFormService();				
		model.addAttribute("formfilter",formFilterService.getFormFilter(formService.getForm(formId)));
		}else
		{
			model.addAttribute("formfilter",formFilterService.getFormFilter(formFilterId));
		}
				
	}
	
	
	
	@RequestMapping(value = "/module/formfilter/deleteFilter", method = RequestMethod.GET)
	public String deleteFormFilterProperty(ModelMap model,HttpSession httpSession,
	                           @RequestParam(value="formFilterPropertyId") int formFilterPropertyId,
	                           @RequestParam(value="formFilterId") int formFilterId
	                           ) {
		
		FormFilterService formFilterService =(FormFilterService)Context.getService(FormFilterService.class);
		formFilterService.purgeFormFilterProperty(formFilterPropertyId);
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "formfilter.filterDeleted");
		return "redirect:viewformfilter.form?formFilterId="+formFilterId;
				
	}
	
	
	

	
}
