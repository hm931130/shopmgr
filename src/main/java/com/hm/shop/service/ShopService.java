package com.hm.shop.service;

import com.hm.shop.bean.ArticleType;

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

}
