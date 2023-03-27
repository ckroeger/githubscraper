package com.github.ckroeger.jwebscraper.service;

import org.jsoup.nodes.Document;

import java.util.Optional;

public interface Documentloader {

    Optional<Document> getDocument(String url);
}
