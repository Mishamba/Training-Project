package com.epam.training.service;

import java.util.List;

import com.epam.training.exception.ServiceException;
import com.epam.training.model.entity.FileEntity;

public interface FileService {
	List<FileEntity> openFolder(String path) throws ServiceException;
	boolean deleteFolder(String path) throws ServiceException;
}
