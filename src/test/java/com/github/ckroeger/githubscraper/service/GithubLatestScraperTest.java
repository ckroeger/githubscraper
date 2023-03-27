package com.github.ckroeger.githubscraper.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class GithubLatestScraperTest {

    @Test
    void getLatestTag_spring_boot() {
        GithubLatestScraper uut = new GithubLatestScraper(new MockDocumentProvider());
        Optional<Tag> latestTag = uut.getLatestTag("spring-projects", "spring-boot");
        assertThat(latestTag).isNotEmpty();
        log.info(latestTag.get().toString());
    }

    @Test
    void getLatestTag_spring_framework() {
        GithubLatestScraper uut = new GithubLatestScraper(new MockDocumentProvider());
        Optional<Tag> latestTag = uut.getLatestTag("spring-projects", "spring-framework");
        assertThat(latestTag).isNotEmpty();
        Tag actual = latestTag.get();
        log.info(actual.toString());
        assertThat(actual.getVersion()).isEqualTo("v6.0.7");
    }
    @Test
    void getLatestTag_kubernetes() {
        GithubLatestScraper uut = new GithubLatestScraper(new MockDocumentProvider());
        Optional<Tag> latestTag = uut.getLatestTag("kubernetes", "kubernetes");
        assertThat(latestTag).isNotEmpty();
        Tag actual = latestTag.get();
        log.info(actual.toString());
        assertThat(actual.getVersion()).isEqualTo("v1.26.3");
        assertThat(actual.getAge()).isEqualTo("0 years, 0 months and 11 days");
    }

}