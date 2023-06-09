package com.github.ckroeger.githubscraper.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.github.ckroeger.githubscraper.TimeUtil.calcTimeDiff;

@AllArgsConstructor
@ToString
@Getter
public class Tag {
    String url;
    String version;
    String releaseDate;

    @Getter(lazy = true)
    private final String age = calcTimeDiff(LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE_TIME), LocalDate.now());
}
