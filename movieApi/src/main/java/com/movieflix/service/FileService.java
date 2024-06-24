package com.movieflix.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

	String uploadfile(String path,MultipartFile file) throws IOException;
	
	InputStream getResourceFile(String path,String fileName) throws FileNotFoundException;
}
