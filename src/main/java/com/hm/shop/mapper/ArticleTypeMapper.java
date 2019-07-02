package com.hm.shop.mapper;

import com.hm.shop.bean.ArticleType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/1/001 23:05
 */
public interface ArticleTypeMapper {

 @Select("select * from ec_article_type")
 List<ArticleType> getArticleTypes();

 @Select("select * from ec_article_type where length(CODE)=4")
 List<ArticleType> findFirstArticleTypes();

 @Select("select * from ec_article_type where CODE like #{typeCode} and length(CODE) = #{len}")
 List<ArticleType> findSecondArticleTypes(@Param("typeCode") String typeCode, @Param("len") int length);
}
