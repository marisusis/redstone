package com.altarisnine.redstone.common.test;

import com.altarisnine.redstone.api.util.UTF8Control;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTest {
    static Locale en = new Locale.Builder().setLanguage("en").build();
    static Locale es = new Locale.Builder().setLanguage("es").build();

    @Test
    void testOne() {
        ResourceBundle textEN = ResourceBundle.getBundle("RedstoneCore", en);
        ResourceBundle textES = ResourceBundle.getBundle("RedstoneCore", es, new UTF8Control());
        System.out.println(textES.getString("greeting"));
    }
}
