package com.github.ckroeger.jwebscraper.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GithubLatestScraperTest {

    @Test
    void getLatestTag_spring_boot() {
        GithubLatestScraper uut = new GithubLatestScraper();
        Optional<Tag> latestTag = uut.getLatestTag("spring-projects", "spring-boot");
        Assertions.assertThat(latestTag).isNotEmpty();
    }

    @Test
    void getLatestTag_spring_frsmework() {
        GithubLatestScraper uut = new GithubLatestScraper();
        Optional<Tag> latestTag = uut.getLatestTag("spring-projects", "spring-framework");
        Assertions.assertThat(latestTag).isNotEmpty();
        Tag actual = latestTag.get();
        Assertions.assertThat(actual.getVersion()).isEqualTo("v6.0.7");
    }
}