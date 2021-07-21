package com.example.boilerplate.ModelClasses;

import java.io.Serializable;

public class Directory implements Serializable {
    String dirName;

    public Directory(String dirName) {
        this.dirName = dirName;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
