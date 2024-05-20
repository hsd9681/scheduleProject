package org.sparta.scheduleproject.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FileUploadRequestDto {
    private String fileName;

    @JsonProperty("fileContent")
    private String fileContentBase64;

    // getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentBase64() {
        return fileContentBase64;
    }

    public void setFileContentBase64(String fileContentBase64) {
        this.fileContentBase64 = fileContentBase64;
    }
}
