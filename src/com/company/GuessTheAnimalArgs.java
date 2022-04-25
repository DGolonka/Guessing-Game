package com.company;

import com.beust.jcommander.Parameter;

public class GuessTheAnimalArgs {
    @Parameter(
            names = "--type",
            description = "Type file to save",
            required = true
    )
    private String typeFile;

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }
}
