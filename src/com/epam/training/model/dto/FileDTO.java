package com.epam.training.model.dto;

import com.epam.training.model.filetype.FileType;

public class FileDTO {
	private String fileName;
	private FileType fileType;
	
	public FileDTO(String fileName, FileType fileType) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the fileType
	 */
	public FileType getFileType() {
		return fileType;
	}
}
