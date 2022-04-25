package com.company.TypeFileStrategy;

import com.company.engine.TreeNode;

import java.util.Locale;

public interface StrategyFile {
    TreeNode executeRead();
    void executeWrite(TreeNode root);
    default String getLanguageType(Locale locale) {
        if (locale.getLanguage().equals("en")) {
            return  "animals_en";
        } else if (locale.getLanguage().equals("eo")) {
            return  "animals_eo";
        } else {
            return "animals";
        }
    }
}
