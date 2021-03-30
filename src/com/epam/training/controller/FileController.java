package com.epam.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.controller.reponse.Response;
import com.epam.training.exception.ControllerException;
import com.epam.training.exception.ServiceException;
import com.epam.training.model.dto.FileDTO;
import com.epam.training.model.entity.FileEntity;
import com.epam.training.service.FileService;

@RestController
@RequestMapping("/")
public class FileController {
	
	private FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@PermitAll
	@GetMapping("/{path}")
	public List<FileDTO> findByPath(@PathVariable("path") String path) throws ControllerException {
		try {
			List<FileEntity> fileEntities = fileService.openFolder(path);
			
			return fileEntities.stream().map(FileEntity::toDTO).collect(Collectors.toList());
		} catch(ServiceException exception) {
			throw new ControllerException("can't get content", exception);
		}
	}
	
	@PreAuthorize("hasAuthority('delete_file')")
	@PostMapping("/delete-file/{path}")
	public ResponseEntity<Response> deleteFile(@PathVariable("path") String path) throws ControllerException {
		try {
			if (fileService.deleteFolder(path)) {
				return new ResponseEntity<Response>(new Response("sucessfully deleted file"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("can't delete file"), HttpStatus.OK);
			}
		} catch (ServiceException exception) {
			throw new ControllerException("can't delete file", exception);
		}
	}
}
