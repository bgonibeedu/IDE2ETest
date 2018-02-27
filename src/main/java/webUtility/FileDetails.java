package webUtility;

import java.io.File;

public class FileDetails {
    private String filename;
    private String fileMimeType;
    private String fileSize;
    private String fileExtension;
    private File filePath;
    
    public FileDetails(File filePath, String fileMimeType, String fileSize, String filename, String fileExtension) {
        this.filePath = filePath;
        this.fileMimeType = fileMimeType;
        this.fileSize = fileSize;
        this.filename = filename;
        this.fileExtension = fileExtension;
    }
    
    public File getFilePath() {
        return filePath;
    }
    public void setFilePath() {
        this.filePath=filePath;
    }
    
    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilename() {
        return filename;

    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
   

    public void setFileExtension(String fileExtension) {
        this.filename = fileExtension;
    }
	public String getFileExtension() {
        return fileExtension;
	}
}
