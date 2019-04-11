package ch.hearc.springwater.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.exceptions.ResourceNotFoundException;
import ch.hearc.springwater.service.impl.FileService;

@Controller
@RequestMapping(value = "/image")
public class ImageController
{
	@Autowired
	private FileService fileStorageService;
	
	@GetMapping("/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
	{
		Resource resource = null;
		try
		{
			resource = fileStorageService.loadFileAsResource(fileName);
		}
		catch (Exception e)
		{
			throw new ResourceNotFoundException();
		}

		String contentType = null;
		try
		{
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}
		catch (IOException ex)
		{
			System.out.println("Could not determine file type.");
		}

		if(contentType == null)
		{
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

}
