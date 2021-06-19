package com.iktakademija.UploadExample.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class FileUploadImpl implements FileUploadService {

	@Override
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		// check if file is empty
		if (file.isEmpty()) {
			// if empty, redirect user to upload status and show error message
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		// if note empty, save file to disk
		byte[] bytes = file.getBytes();
		// bolje za naiv fajla GUID ili tmestamp + extenzija
		Path path = Paths.get("c:\\temp\\" + file.getOriginalFilename());
		Files.write(path, bytes);

		// when done, redirect user to uploadStaus page, with success message
		redirectAttributes.addFlashAttribute("message", "File uploaded successfily: " + file.getOriginalFilename());
		return "redirect:uploadStatus";
	}

}
