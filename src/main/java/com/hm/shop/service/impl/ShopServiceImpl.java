package com.hm.shop.service.impl;

import com.hm.shop.bean.Article;
import com.hm.shop.bean.ArticleType;
import com.hm.shop.bean.User;
import com.hm.shop.mapper.ArticleMapper;
import com.hm.shop.mapper.ArticleTypeMapper;
import com.hm.shop.mapper.UserMapper;
import com.hm.shop.service.ShopService;
import com.hm.shop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/1/001 22:59
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {


 @Autowired
 private ArticleTypeMapper articleTypeMapper;

 @Autowired
 private ArticleMapper articleMapper;

 @Autowired
 private UserMapper userMapper;

 @Override
 public List<ArticleType> getArticleTypes() {
  return articleTypeMapper.getArticleTypes();
 }

 @Override
 public Map<String, Object> login(String loginName, String passWord) {

  Map<String, Object> map = new HashMap<String, Object>();
  User user = userMapper.findUserByName(loginName);

  //用户不存在
  if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)) {
   map.put("code", 1);
   map.put("msg", "请输入账户或密码");
  } else {
   if (user == null) {
    map.put("code", 2);
    map.put("msg", "该用户不存在");
   } else {
    if (!user.getPassword().equals(passWord)) {
     map.put("code", 3);
     map.put("msg", "密码错误");
    } else {
     map.put("code", 0);
     map.put("msg", user);
    }
   }

  }
  return map;
 }

 @Override
 public List<ArticleType> findFirstArticleTypes() {
  return articleTypeMapper.findFirstArticleTypes();
 }

 @Override
 public List<ArticleType> findSecondArticleTypes(String typeCode) {
  return articleTypeMapper.findSecondArticleTypes(typeCode + "%", typeCode.length() + 4);
 }

 @Override
 public List<Article> findAllArticles(String typeCode, String secondType, String title, Pager pager) {
  return articleMapper.findAllArticles(typeCode, secondType, title,pager);
 }

 @Override
 public int findAllArticlesCount(String typeCode, String secondTypeCode, String title) {
  return articleMapper.findAllArticlesCount(typeCode, secondTypeCode, title);
 }

 @Override
 public void deleteArticleById(String id) {
  articleMapper.deleteArticleById(id);
 }

 @Override
 public Article getArticleById(String id) {
  return articleMapper.getArticleById(id);
 }
}
