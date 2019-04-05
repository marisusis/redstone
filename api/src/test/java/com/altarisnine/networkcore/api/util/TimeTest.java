package com.altarisnine.networkcore.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    void testMilitary() {
        Assertions.assertEquals(6, Time.convert12to24(6, Time.Period.AM));
        Assertions.assertEquals(18, Time.convert12to24(6, Time.Period.PM));
        Assertions.assertEquals(0, Time.convert12to24(12, Time.Period.AM));
        Assertions.assertEquals(12, Time.convert12to24(12, Time.Period.PM));
    }

    @Test
    void testNormalParse() {
        Assertions.assertEquals(18000, Time.parseTime("12:00am").getTicks());
        Assertions.assertEquals(0, Time.parseTime("6:00am").getTicks());
        Assertions.assertEquals(23000, Time.parseTime("5:00am").getTicks());
        Assertions.assertEquals(13000, Time.parseTime("7:00pm").getTicks());
    }

    @Test
    void testMilitaryParse() {
        Assertions.assertEquals(18000, Time.parseTime("00:00").getTicks());
        Assertions.assertEquals(0, Time.parseTime("6:00").getTicks());
        Assertions.assertEquals(23000, Time.parseTime("5:00").getTicks());
        Assertions.assertEquals(13000, Time.parseTime("19:00").getTicks());
    }

    @Test
    void testUnitParse() {
        Assertions.assertEquals(Time.of(Time.HOUR * 5), Time.parseInterval("5h"));
        Assertions.assertEquals(Time.of(Time.DAY * 2), Time.parseInterval("2d"));
        Assertions.assertEquals(Time.of(Time.MINUTE * 3), Time.parseInterval("3m"));
    }

    @Test
    void testValidation() {
        Assertions.assertFalse(Time.validateTime("32:00pm"));
        Assertions.assertFalse(Time.validateTime("8:60pm"));
        Assertions.assertTrue(Time.validateTime("3:50am"));
        Assertions.assertTrue(Time.validateTime("02:00pm"));
        Assertions.assertTrue(Time.validateInterval("3h"));
        Assertions.assertFalse(Time.validateInterval("3hh"));
    }
}
