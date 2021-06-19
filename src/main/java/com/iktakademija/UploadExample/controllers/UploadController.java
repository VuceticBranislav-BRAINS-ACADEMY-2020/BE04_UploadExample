package com.iktakademija.UploadExample.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktakademija.UploadExample.services.FileUploadService;

@Controller
@RequestMapping(path = "/")
public class UploadController {

	@JsonIgnore
	private final Logger logger = LoggerFactory.getLogger(UploadController.class); // Uppload.Controller.class === this.getClass()
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(method = RequestMethod.GET, path = "")
	public String index() {
		return "upload";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/upload")
	public String fileUpload(@RequestParam(name = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
		// invoke service
		String retVal = null;
		
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");	
		
		try {
			retVal = fileUploadService.singleFileUpload(file, redirectAttributes);
		} catch (IOException e) {
			e.printStackTrace(); // redirektovati na stranicu sa porukom o gresci
		}
	
		// return service retVal
		return retVal;
	}
}
