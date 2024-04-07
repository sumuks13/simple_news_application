package dev.sumuks.Newspaper;


import dev.sumuks.Newspaper.config.TenantContext;
import dev.sumuks.Newspaper.entity.Categories;
import dev.sumuks.Newspaper.entity.News;
import dev.sumuks.Newspaper.entity.Tenants;
import dev.sumuks.Newspaper.repository.CategoriesRepository;
import dev.sumuks.Newspaper.repository.TenantsRepository;
import dev.sumuks.Newspaper.service.NewspaperSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@EnableScheduling
@Component
@Slf4j
public class NewsScheduler {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    TenantsRepository tenantsRepository;

    @Autowired
    NewspaperSchedulerService newspaperSchedulerService;

    @Scheduled(cron = "0 0 4 * * *")
    @Async
    public void runNewsScheduler(){

        TenantContext.setCurrentTenant("def");
        List<Tenants> tenantsList = tenantsRepository.findAll();
        ExecutorService executorService = Executors.newFixedThreadPool(tenantsList.size());

        for(Tenants tenant : tenantsList){
            if(!tenant.getTenantId().equals("def")) {
                TenantContext.setCurrentTenant(tenant.getTenantId());

                CompletableFuture.runAsync(()->{
                    TenantContext.setCurrentTenant(tenant.getTenantId());
                    for(Categories category : categoriesRepository.findAll()) {
                        log.info("Loading {} news for {}", category.getCategory(), tenant.getTenantId());
                        List<News> newsList = newspaperSchedulerService.fetchNews(tenant.getTenantId(), category.getCategory());
                        log.info("Thread : {}", Thread.currentThread().getId());
                        newspaperSchedulerService.saveNews(newsList);
                    }
                }, executorService);
            }
        }
    }
}
