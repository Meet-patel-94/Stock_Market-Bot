package com.meetpatel.loginregister2.Model;

public class FirebaseDataModel {


    private String fileName;
    private String fileExtension;
    private String fileURL;

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
