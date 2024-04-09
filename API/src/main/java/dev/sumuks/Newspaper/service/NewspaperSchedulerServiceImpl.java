package dev.sumuks.Newspaper.service;

import dev.sumuks.Newspaper.bean.Articles;
import dev.sumuks.Newspaper.bean.NewsApiResponse;
import dev.sumuks.Newspaper.entity.News;
import dev.sumuks.Newspaper.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NewspaperSchedulerServiceImpl implements NewspaperSchedulerService {

    @Value("${news.api.url}")
    String apiUrl;

    @Value("${news.api.key}")
    String apiKey;

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> fetchNews(String country, String category) {

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("country", country)
                .queryParam("category", category)
                .queryParam("pageSize", 6)
                .build()
                .toUriString();

        WebClient webClient = WebClient.builder().baseUrl(url).build();
        List<Articles> articles = webClient.get()
                .header("X-Api-Key", apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(NewsApiResponse.class)
                .map(NewsApiResponse::getArticles)
                .block();

        List<News> newsList = new ArrayList<>();
        for(Articles article : articles) {
            News news = new News();
            BeanUtils.copyProperties(article, news);
            news.setCategory(category);
            newsList.add(news);
        }
        log.info("" + newsList.size());
        return newsList;
    }

    @Override
    public void saveNews(List<News> newsList) {
        newsRepository.saveAll(newsList);
    }

    @Override
    public List<News> returnNews(String category) {
        return newsRepository.findByCategoryOrderByCreatedOnDesc(category);
    }
}
