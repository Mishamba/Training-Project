package com.epam.training.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.FileEntity;
import com.epam.training.model.filetype.FileType;
import com.epam.training.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Override
	public List<FileEntity> openFolder(String givenPath) throws ServiceException {
		Path path = Path.of(givenPath);
		if (!path.toFile().exists() || path.toFile().isFile()) {
			throw new ServiceException("invalid path or path goes to file");
		}
		
		try {
			return Files.list(path).map(FileServiceImpl::parsePathToFileEntity).collect(Collectors.toList());
		} catch (IOException exception) {
			throw new ServiceException("can't get files list");
		}
	}
	
	private static FileEntity parsePathToFileEntity(Path path) {
		FileEntity fileEntity = new FileEntity();
		
		File file = path.toFile();
		
		if (file.isDirectory()) {
			fileEntity.setFileType(FileType.FOLDER);
		} else if (file.isFile()) {
			fileEntity.setFileType(FileType.FILE);
		}
		
		fileEntity.setFileName(file.getName());
		
		return fileEntity;
	}
	
	@Override
	public boolean deleteFolder(String givenPath) throws ServiceException {
		Path path = Path.of(givenPath);
		if (!path.toFile().exists()) {
			throw new ServiceException("invalid path");
		}

		try {
			deleteDirectoryContent(path);
			return Files.deleteIfExists(path);
		} catch (IOException exception) {
			return false;
		}
	}
	
	private void deleteDirectoryContent(Path givenPath) throws IOException {
		try (Stream<Path> walk = Files.walk(givenPath)) {
			walk.sorted(Comparator.reverseOrder()).forEach(path -> {
	        	try {
	            	Files.delete(path);
	        	} catch (IOException e) {
	        		// TODO : log or smth else
	        	}
	    	});
		}
	}
}
