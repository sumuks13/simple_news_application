package dev.sumuks.Newspaper.service;

import dev.sumuks.Newspaper.entity.News;

import java.util.List;

public interface NewspaperSchedulerService {

    List<News> fetchNews(String country, String category);

    void saveNews(List<News> newsList);

    List<News> returnNews(String category);
}
