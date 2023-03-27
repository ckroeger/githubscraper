package com.github.ckroeger.jwebscraper.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

public class GithubLatestScraper {

    public static final String HTTPS_GITHUB_COM = "https://github.com";

    private final Documentloader documentloader;

    public GithubLatestScraper(Documentloader documentloader) {
        this.documentloader = documentloader;
    }

    public GithubLatestScraper() {
        documentloader = url -> {
            try {
                return Optional.of(Jsoup.connect(url).get());
            } catch (IOException e) {
                return Optional.empty();
            }
        };
    }

    public Optional<Tag> getLatestTag(String root, String repo) {

        String url = String.format(HTTPS_GITHUB_COM + "/%s/%s", root, repo);
        Optional<Document> document = documentloader.getDocument(url);
        if (document.isPresent()) {
            Optional<Element> releaseURLElement = document.get().getElementsByTag("a").stream()
                    .filter(element -> element.attributes().getIgnoreCase("href").contains("releases/tag")).findFirst();
            if (releaseURLElement.isEmpty()) {
                return Optional.empty();
            }
            Element element = releaseURLElement.get();
            String datetime = element.getElementsByTag("relative-time").attr("datetime");
            String href = element.attr("href");
            return Optional.of(new Tag(String.format(HTTPS_GITHUB_COM + "%s", href), StringUtils.substringAfterLast(href, "/"), datetime));
        }
        return Optional.empty();
    }
}
