package com.epam.training.model.entity;

import com.epam.training.model.filetype.FileType;

// TODO : rename class
public class File {
	private FileType fileType;
	private String fileName;

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		int prime = 5;
		int hash = 1;
		hash *= this.fileName.hashCode() * prime;
		hash *= this.fileType.hashCode() * prime;
		
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		File that = (File) obj;
		return this.getFileName().equals(that.getFileName()) &&
				this.getFileType().equals(that.getFileType());
	}

	@Override
	public String toString() {
		return "File { " +
				"fileName: \'" + this.fileName + "\', " +
				"fileType: " + this.fileType.name() + 
				" }";
	}
}
