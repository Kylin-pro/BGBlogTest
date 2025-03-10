package com.sangeng.runner;

import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.ArticleListVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class redisCommandLineRunner implements CommandLineRunner {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void run(String... args) throws Exception {
    //应用启动时，将文章访问量写入redis中保存
        List<Article> articleList = articleMapper.selectList(null);
        Map<String, Integer> collect = articleList.stream()
                .collect(Collectors.toMap(article -> article.getId().toString()
                        , article -> article.getViewCount().intValue()));

        redisCache.setCacheMap("article:viewCount",collect);

    }
}
