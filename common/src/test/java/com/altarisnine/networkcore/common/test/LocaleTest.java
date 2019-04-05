package com.altarisnine.networkcore.common.test;

import com.altarisnine.networkcore.api.util.UTF8Control;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTest {
    static Locale en = new Locale.Builder().setLanguage("en").build();
    static Locale es = new Locale.Builder().setLanguage("es").build();

    @Test
    void testOne() {
        ResourceBundle textEN = ResourceBundle.getBundle("NetworkCore", en);
        ResourceBundle textES = ResourceBundle.getBundle("NetworkCore", es, new UTF8Control());
        System.out.println(textES.getString("greeting"));
    }
}
