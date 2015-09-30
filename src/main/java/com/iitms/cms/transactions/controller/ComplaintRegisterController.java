package com.iitms.cms.transactions.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iitms.cms.transactions.entity.ComplaintRegisterEntity;
import com.iitms.cms.transactions.service.ComplaintRegisterService;
import com.iitms.cms.util.JsonResponse;

@Controller
public class ComplaintRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(ComplaintRegisterController.class);
	@Autowired
	private ComplaintRegisterService complaintRegisterService;
	
	@RequestMapping(value = "/transaction/complaints-register")
	public String getComplaintForm(Model model){
		model.addAttribute("complaint", new ComplaintRegisterEntity());
		
		 List<ComplaintRegisterEntity> list = complaintRegisterService.getAllComplaints();
	      	model.addAttribute("list", list);
		return "/transaction/complaint-register";
	}
	
	@RequestMapping(value = "/transaction/complaint/list", method = RequestMethod.POST)
	public @ResponseBody List<ComplaintRegisterEntity> getAllComplaints(){
		return complaintRegisterService.getAllComplaints();
	}
	
	
	
	@RequestMapping(value = "/transaction/add-complaint-registration", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addRegisterComplaint(@Valid @ModelAttribute("complaint") ComplaintRegisterEntity complaintRegisterEntity,
			BindingResult result, Model model){
		
		logger.info("complaint : " + complaintRegisterEntity + " Error : " + result.hasErrors() +  " - "  + result.hasFieldErrors("itemCode"));
		
	 	JsonResponse response =complaintRegisterService.addUpdateComplaintRegister(complaintRegisterEntity, result, "add");
		return response;
	}
	
	
	
	@RequestMapping(value = "/transaction/complaints/edit")
	private String editComplaint(@ModelAttribute ComplaintRegisterEntity complaint){
		logger.info("Entity : "   + complaint);
		complaintRegisterService.addComplaint(complaint);
		return "redirect:/transaction/complaint";
	}
	
	
	
	
}
