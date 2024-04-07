package dev.sumuks.Newspaper.bean;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("articles")
public class Articles {

    @JsonProperty(value = "publishedAt")
    private LocalDateTime createdOn;

    @JsonProperty(value = "title")
    private String headlines;

    @JsonProperty(value = "content")
    private String shortDescription;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "urlToImage")
    private String imageUrl;

    private String websiteName;

    @JsonProperty("source")
    private void unpackNameFromNestedObject(Map<String, String> source) {
        websiteName = source.get("name");
    }
}
