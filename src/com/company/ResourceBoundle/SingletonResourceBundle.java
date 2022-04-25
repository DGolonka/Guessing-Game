package com.company.ResourceBoundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class SingletonResourceBundle {
    private static ResourceBundle resourceBundle;


    public static ResourceBundle getInstance() {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("com.company.resources.App",
                    Locale.ENGLISH);
        }
        return resourceBundle;
    }
}
