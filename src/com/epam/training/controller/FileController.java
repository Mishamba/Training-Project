package com.epam.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.exception.ControllerException;
import com.epam.training.exception.ServiceException;
import com.epam.training.model.dto.FileDTO;
import com.epam.training.model.entity.FileEntity;
import com.epam.training.service.FileService;

@RestController("/")
public class FileController {
	
	private FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@GetMapping("/{path}")
	public List<FileDTO> findByPath(@PathVariable("path") String path) throws ControllerException {
		try {
			List<FileEntity> fileEntities = fileService.openFolder(path);
			
			return fileEntities.stream().map(FileEntity::toDTO).collect(Collectors.toList());
		} catch(ServiceException exception) {
			throw new ControllerException("can't get content", exception);
		}
	}
	
	// TODO : delete method (need to send response. create response entity)
}
