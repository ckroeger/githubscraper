package com.github.ckroeger.githubscraper;

import java.time.LocalDate;
import java.time.Period;

public class TimeUtil {

    private TimeUtil() {
    }

    public static String calcTimeDiff(LocalDate startDate, LocalDate endDate) {

        Period diff
                = Period
                .between(startDate,
                        endDate);
        return String.format(
                "%d years, %d months"
                        + " and %d days",
                diff.getYears(),
                diff.getMonths(),
                diff.getDays());
    }
}
