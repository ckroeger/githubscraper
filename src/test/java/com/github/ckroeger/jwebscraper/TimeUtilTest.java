package com.github.ckroeger.jwebscraper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class TimeUtilTest {

    @Test
    void calcTimeDiff() {
        LocalDate st = LocalDate.parse("2023-03-23T11:56:40Z", DateTimeFormatter.ISO_DATE_TIME);
        LocalDate en = LocalDate.parse("2023-03-27");
        Assertions.assertThat(TimeUtil.calcTimeDiff(st,en)).isEqualTo("0 years, 0 months and 4 days");
    }
}