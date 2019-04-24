package ch.hearc.springwater.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ch.hearc.springwater.config.FileConfig;
import ch.hearc.springwater.exceptions.FileException;

//https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
@Service
public class FileService {

	private final Path fileStorageLocation;

	private static final String NOT_FOUND = "File not found ";
	@Autowired
	public FileService(FileConfig config) throws FileException {
		this.fileStorageLocation = Paths.get(config.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileException("couldn't create directory");
		}
	}

	public String storeFile(MultipartFile file) throws FileException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileException(NOT_FOUND + fileName);
			}

			// TODO Lucas Change filename
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileException(NOT_FOUND + fileName);
		}
	}

	public Resource loadFileAsResource(String fileName) throws FileException {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileException(NOT_FOUND + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileException(NOT_FOUND + fileName);
		}
	}
}