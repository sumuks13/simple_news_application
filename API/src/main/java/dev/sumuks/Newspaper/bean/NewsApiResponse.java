package dev.sumuks.Newspaper.bean;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsApiResponse {
    private String status;
    private int totalResults;

    @JsonUnwrapped()
    private List<Articles> articles;
}
