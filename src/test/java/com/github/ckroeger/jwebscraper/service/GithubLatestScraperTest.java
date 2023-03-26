package com.github.ckroeger.jwebscraper.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GithubLatestScraperTest {

    @Test
    void getLatestTag() {
        GithubLatestScraper uut = new GithubLatestScraper();
        Optional<Tag> latestTag = uut.getLatestTag("spring-projects", "spring-boot");
        Assertions.assertThat(latestTag).isNotEmpty();
    }
}