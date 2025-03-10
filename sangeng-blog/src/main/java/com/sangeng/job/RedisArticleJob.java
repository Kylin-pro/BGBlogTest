package com.sangeng.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RedisArticleJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "* 0/10 * * * ? ")
//    @Scheduled(cron = "0/10 * * * * ? ")
    public void RedisArticleViewCount() {
        //3.每隔十分钟将redis中的访问量写入数据库
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articleList = viewCountMap.entrySet().stream()
                .map(stringIntegerEntry -> new Article(Long.valueOf(stringIntegerEntry.getKey()), stringIntegerEntry.getValue().longValue())).collect(Collectors.toList());
        articleService.updateBatchById(articleList);


    }
}
