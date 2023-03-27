package com.github.ckroeger.githubscraper.service;

import org.jsoup.nodes.Document;

import java.util.Optional;

public interface Documentloader {

    Optional<Document> getDocument(String url);
}
