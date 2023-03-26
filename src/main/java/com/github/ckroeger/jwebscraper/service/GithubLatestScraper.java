package com.github.ckroeger.jwebscraper.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

public class GithubLatestScraper {

    public static final String HTTPS_GITHUB_COM = "https://github.com";

    public Optional<Tag> getLatestTag(String root, String repo) {

        try {            //loading the HTML to a Document Object
            String url = String.format(HTTPS_GITHUB_COM + "/%s/%s", root, repo);
            Document document = Jsoup.connect(url).get();
            Optional<Element> releaseURLElement = document.getElementsByTag("a").stream()
                    .filter(element -> element.attributes().getIgnoreCase("href").contains("releases/tag")).findFirst();
            if (releaseURLElement.isEmpty()) {
                return Optional.empty();
            }
            Element element = releaseURLElement.get();
            String datetime = element.getElementsByTag("relative-time").attr("datetime");
            String href = element.attr("href");
            return Optional.of(new Tag(String.format(HTTPS_GITHUB_COM + "%s", href), StringUtils.substringAfterLast(href, "/"), datetime));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
