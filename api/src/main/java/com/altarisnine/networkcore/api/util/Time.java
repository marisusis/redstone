package com.altarisnine.networkcore.api.util;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Time {

    /* MINECRAFT TIME UNITS -> TICKS */
    public static final double SECOND = 0.27; // There is really no reason to use this, it's just here for reference
    public static final double MINUTE = 16.66;
    public static final int HOUR = 1000;
    public static final int DAY = 24000;
    public static final int WEEK = 168000;
    public static final int MONTH = 720000;
    public static final int YEAR = 8766000;


    public static final int NOON = 6000;
    public static final int SUNSET = 12000;
    public static final int MIDNIGHT = 18000;
    public static final int SUNRISE = 23000;


    // TODO only match times that don't overflow (eg. 0[0-9] 1[0-2] for hours and [0-5][0-9] for minutes
    private static final Pattern AM_PM = Pattern.compile("^(?<hour>(([01]?[0-9]))|(2[0-3]))(?::(?<minute>([0-5][0-9])))?(?<period>am|pm)?");
//    private static final Pattern AM_PM = Pattern.compile("^(?<hour>[01]?\\d)(?::(?<minute>\\d\\d))?(?<period>am|pm)?");
    private static final Pattern UNITS = Pattern.compile("^(?<value>\\d+)(?<unit>[tsmhdw])");

    @Getter private final long ticks;

    private Time(long ticks) {
        this.ticks = ticks;
    }

    public static Time of(long ticks) {
        return new Time(ticks);
    }

    public static Time of(int ticks) {
        return new Time(ticks);
    }

    public static Time of(double ticks) {
        return new Time((long) Math.floor(ticks));
    }

    public Time add(Time time) {
        return Time.of(this.ticks + time.ticks);
    }

    public static Time parseTime(String time) {
        if (time.matches(AM_PM.toString())) {
            Matcher matcher = AM_PM.matcher(time);

            matcher.find();

            String hourString = matcher.group("hour");
            String minuteString = matcher.group("minute");
            String periodString = matcher.group("period");

            int hour = 0;
            int minute = 0;

            hour = Integer.parseInt(hourString);

            if (minuteString != null) minute = Integer.parseInt(minuteString);
            if (hour > 23) throw new IllegalArgumentException("hour cannot be greater than 23");

            if (periodString != null) {
                Period period = Period.valueOf(periodString.toUpperCase());

                hour = convert12to24(hour, period);
            }

            final int hourTicks = (convert(hour, Unit.HOURS) + 18000) % 24000;

            return Time.of((int) (hourTicks + convert(minute, Unit.MINUTES)));
        } else throw new IllegalArgumentException("unable to parse time");
    }

    // MUST Different time types: interval (3mins, 5hours), time (3:00pm, 2:34am), tod (sunrise, noon, sunset, midnight)
    public static Time parseInterval(String time) {
        if (time.matches(UNITS.toString())) {
            // Match the units
            Matcher matcher = UNITS.matcher(time);
            matcher.find();


            String valueString = matcher.group("value");
            Objects.requireNonNull(valueString, "value cannot be null");

            String unitString = matcher.group("unit");
            Unit unit = Unit.TICKS;

            if (unitString != null) {
                unit = Unit.parse(unitString.charAt(0));
            }

            int value = Integer.parseInt(valueString);
            char unitChar = unitString.charAt(0);

            return Time.of(convert(value, Unit.parse(unitChar)));
        } else throw new IllegalArgumentException("unable to parse time");
    }

    public static boolean validateInterval(String time) {
        return UNITS.matcher(time).matches();
    }

    public static boolean validateTime(String time) {
        return AM_PM.matcher(time).matches();
    }

    static int convert(int value, Unit unit) {
        Objects.requireNonNull(value, "value cannot be null");
        Objects.requireNonNull(unit, "unit cannot be null");
        switch (unit) {
            case TICKS:
                return value;
            case SECONDS:
                return (int) Math.floor(SECOND * value);
            case MINUTES:
                return (int) Math.floor(MINUTE * value);
            case HOURS:
                // Apply tick offset 18000
                return HOUR * value;
            case DAYS:
                return DAY * value;
            default:
                throw new IllegalStateException("This shouldn't happen!");
        }
    }

    static int convert12to24(int hours, Period period) {
        if (period.equals(Period.AM)) {
            // Return original value if 1-11
            if (hours == 12) return 0;
            else return hours;
        } else if (period.equals(Period.PM)) {
            if (hours == 12) return 12;
            else return hours + 12;
        }

        return 0;
    }

    public enum Period {
        AM,
        PM
    }

    public enum Unit {
        TICKS,
        SECONDS,
        MINUTES,
        HOURS,
        DAYS;

        static Unit parse(char c) {
            switch (c)  {
                case 't':
                    return Unit.TICKS;
                case 's':
                    return Unit.SECONDS;
                case 'm':
                    return Unit.MINUTES;
                case 'h':
                    return Unit.HOURS;
                case 'd':
                    return Unit.DAYS;
                default:
                    throw new IllegalArgumentException("That is not a valid unit!");
            }
        }
    }

    @Override
    public String toString() {
        return "Time{" +
                "ticks=" + ticks +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;
        Time time = (Time) o;
        return ticks == time.ticks;
    }
}
