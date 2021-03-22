package com.epam.training.service;

import java.util.List;

import com.epam.training.model.entity.File;

// TODO : add throws
public interface FileService {
	List<File> openFolder(String path);
	boolean deleteFolder(String path);
}
