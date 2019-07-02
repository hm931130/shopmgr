package com.hm.shop.service;

import com.hm.shop.bean.Article;
import com.hm.shop.bean.ArticleType;
import com.hm.shop.utils.Pager;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/1/001 22:59
 */
public interface ShopService {
 List<ArticleType> getArticleTypes();

 Map<String, Object> login(String loginName, String passWord);

 List<ArticleType> findFirstArticleTypes();

 List<ArticleType> findSecondArticleTypes(String typeCode);

 List<Article> findAllArticles(String typeCode, String secodeType, String title, Pager pager);

 int findAllArticlesCount(String typeCode, String secondTypeCode, String title);

 void deleteArticleById(String id);

 Article getArticleById(String id);
}
