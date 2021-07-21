package com.example.boilerplate.ModelClasses;

import java.io.Serializable;

public class SubDirectory implements Serializable {
    String direName;

    public SubDirectory(String direName) {
        this.direName = direName;
    }

    public String getDireName() {
        return direName;
    }

    public void setDireName(String direName) {
        this.direName = direName;
    }
}
