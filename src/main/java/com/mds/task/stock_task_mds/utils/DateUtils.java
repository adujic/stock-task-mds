package com.mds.task.stock_task_mds.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts a string representation of a date into a {@link LocalDate}.
     *
     * @param datevalue the string representation of the date to parse.
     * @return the parsed {@link LocalDate}.
     */
    public static LocalDate localDateFromString(final String datevalue) {
        try {
            return LocalDate.parse(datevalue, dtf);
        } catch (DateTimeParseException e) {
            LOG.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    /**
     * Checks if the specified start date is before the end date.
     *
     * @param startDate the date to compare as the start date.
     * @param endDate the date to compare as the end date.
     * @return {@code true} if the start date is before the end date, {@code false} otherwise.
     */
    public static boolean isDateBefore(final LocalDate startDate, final LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

}
