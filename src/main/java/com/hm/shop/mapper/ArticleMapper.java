package com.hm.shop.mapper;

import com.hm.shop.bean.Article;
import com.hm.shop.utils.Pager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/1/001 23:00
 */
public interface ArticleMapper {


 List<Article> findAllArticles(@Param("typeCode") String typeCode,
                               @Param("secondType") String secondType, @Param("title") String title, @Param("pager") Pager pager);

 int findAllArticlesCount(@Param("typeCode") String typeCode,
                          @Param("secondType") String secondType, @Param("title") String title);

 @Delete("delete from ec_article where ID=#{XX}")
 void deleteArticleById(String id);

 @Select("select * from ec_article where ID=#{XXX}")
 Article getArticleById(String id);
}
