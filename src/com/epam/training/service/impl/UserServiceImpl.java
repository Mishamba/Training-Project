package com.epam.training.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.IOException;

import com.epam.training.model.entity.File;
import com.epam.training.service.FileService;

public class FileServiceImpl implements FileService {
	@Override
	public List<com.epam.training.model.entity.File> openFolder(String givenPath) {
		Path path = Path.of(givenPath);
		if (!path.toFile().exists() || path.toFile().isFile()) {
			// TODO : throw exception
			return new ArrayList<File>();
		}
		
		Stream<Path> paths = Files.list(path);
		
		// TODO : parse paths to File;
		return null;
	}
	
	@Override
	public boolean deleteFolder(String givenPath) {
		Path path = Path.of(givenPath);
		if (!path.toFile().exists()) {
			// TODO : throw exception
			return false;
		}

		deleteDirectoryContent(path);

		return Files.deleteIfExists(path);
	}
	
	private void deleteDirectoryContent(Path givenPath) {
		try (Stream<Path> walk = Files.walk(givenPath)) {
			walk.sorted(Comparator.reverseOrder()).forEach(path -> {
	        	try {
	            	Files.delete(path);
	        	} catch (IOException e) {
	        		// TODO : throw exception
	        	}
	    	});
		}
	}
}
