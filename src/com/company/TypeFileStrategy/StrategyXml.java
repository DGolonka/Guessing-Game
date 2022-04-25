package com.company.TypeFileStrategy;

import com.company.engine.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class StrategyXml implements StrategyFile {
    private final String fileName;
    private final ObjectMapper objectMapper = new XmlMapper();

    public StrategyXml(Locale locale) {
        this.fileName = getLanguageType(locale) + ".xml";
    }

    @Override
    public TreeNode executeRead() {
        File file = new File(fileName);
        TreeNode root = null;
        if (file.exists()) {
            try {
                root = objectMapper.readValue(file, TreeNode.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    @Override
    public void executeWrite(TreeNode root) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
