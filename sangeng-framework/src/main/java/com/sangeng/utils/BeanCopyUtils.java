package com.sangeng.utils;

import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.ArticleListVo;
import com.sangeng.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }

    public static <V> V copyBean(Object source ,Class<V> clazz) {
        V target;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <O,V> List<V> copyBeanList(List<O> source, Class<V> clazz) {

        return source.stream()
                .map(o -> BeanCopyUtils.copyBean(o, clazz))
                .collect(Collectors.toList());

    }


    public static void main(String[] args) {
        Article article = new Article();
        article.setId(111L);
        article.setTitle("wenzhang");
        article.setViewCount(123L);
        HotArticleVo hotArticleVo = BeanCopyUtils.copyBean(article, HotArticleVo.class);
        System.out.println("hotArticleVo = " + hotArticleVo);
    }


}
