package com.emart.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emart.model.PictureModel;

@RestController
@RequestMapping(value = "/picture")
public class PictureController {
	private static final Logger log = LoggerFactory.getLogger(PictureController.class);
	
	@Value("${picture.upload-path}")
	private String uploadPath;
    
	/**
	 * Upload picture
	 * @param file
	 * @return PictureModel
	 */
	@PostMapping
    public ResponseEntity<PictureModel> upload(@RequestParam(value = "file") MultipartFile file) {
		PictureModel model = new PictureModel();
		
		if (file.isEmpty()) {
            model.setMessageCode("E004");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(model);
        }
		
		//Original filename
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); 
        
        //New filename
        fileName = UUID.randomUUID() + suffixName;
        
        File dest = new File(uploadPath + fileName);
        
        //If parent dir is not exist then create it
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        
        try {
            file.transferTo(dest);
        } catch (IOException e) {
        	log.info(e.toString());
        	model.setMessageCode("E999");
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(model);
        }
        
        model.setPath("/picture/" + fileName);
        
        return ResponseEntity.ok(model);

    }
	
	
	
}