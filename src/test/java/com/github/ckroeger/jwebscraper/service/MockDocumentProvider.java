package com.github.ckroeger.jwebscraper.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class MockDocumentProvider implements Documentloader {

    @Override
    public Optional<Document> getDocument(String url) {
        try {
            return Optional.of(getDocumentFromFileOrWeb(url));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Document getAndStore(String url, String path) throws IOException {
        Connection.Response html = Jsoup.connect(url).execute();
        String body = html.body();
        Files.write(Path.of(path),body.getBytes(StandardCharsets.UTF_8));
        return Jsoup.parse(body);
    }

    public Document getDocumentFromFileOrWeb(String url) throws IOException {
        // https://github.com/spring-projects/spring-boot
        // getSubstringAfter ".com/" => spring-projects/spring-boot
        String suffix = StringUtils.substringAfter(url, "github.com/");
        // replace / to __ => spring-projects__spring-boot
        suffix = suffix.replaceAll("/","__");
        // check if file src/test/resources/spring-projects__spring-boot.html exists
        String first = "src/test/resources/" + suffix;
        if(Files.exists(Path.of(first))){
            //   yes => Jsoup.parse(html)
            return Jsoup.parse(new File(first));
        }

        //   no  => Fetch from website and store under src/test/resources/spring-projects__spring-boot.html
        return getAndStore(url,first);
    }
}
