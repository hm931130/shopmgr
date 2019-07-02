package com.hm.shop.action;

import com.hm.shop.bean.Article;
import com.hm.shop.bean.ArticleType;
import com.hm.shop.service.ShopService;
import com.hm.shop.utils.Pager;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

 // 定义业务层对象
 private ShopService shopService;
 private HttpServletRequest request;
 private HttpServletResponse response;

 @Override
 public void init() throws ServletException {
  super.init();
  // 获取sping的容器。然后从容器中得到业务层对象
  ServletContext servletContext = this.getServletContext();
  WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
  shopService = (ShopService) context.getBean("shopService");
 }

 @Override
 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//  super.service(req, resp);
  this.request = req;
  this.response = resp;
  this.request.setCharacterEncoding("UTF-8");
  this.response.setCharacterEncoding("UTF-8");
  String method = req.getParameter("method");

  switch (method) {
   case "getAll":
    getAllArticles();
    break;
   case "deleteById":
    deleteById();
    break;
   case "preArticle":
    preArticle();
    break;
  }

 }

 /**
  * 预览商品
  */
 private void preArticle() throws ServletException, IOException {
  String id = request.getParameter("id");

  Article article = shopService.getArticleById(id);
  request.setAttribute("article", article);
  request.getRequestDispatcher("/WEB-INF/jsp/preArticle.jsp").forward(request, response);

 }

 private void deleteById() throws ServletException, IOException {
  try {
   String id = request.getParameter("id");
   shopService.deleteArticleById(id);
   request.setAttribute("tip", "删除成功");
  } catch (Exception e) {
   request.setAttribute("tip", "删除失败");
   e.printStackTrace();
  }

  request.getRequestDispatcher("/list?method=getAll").forward(request, response);
 }

 /**
  * 获取所有商品
  */
 private void getAllArticles() throws ServletException, IOException {
  int pageIndex = 1;
  try {
   pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
   if (pageIndex == 0) {
    pageIndex = 1;
   }
  } catch (Exception e) {
   e.printStackTrace();
  }

  String typeCode = request.getParameter("typeCode");
  String secondTypeCode = request.getParameter("secondType");
  request.setAttribute("secondType", secondTypeCode);
  String title = request.getParameter("title");
  request.setAttribute("title", title);
  //获取所有一级商品类型
  List<ArticleType> firstArticleTypes = shopService.findFirstArticleTypes();
  request.setAttribute("firstArticleTypes", firstArticleTypes);

  //获取二级商品类型
  //一级商品typeCode存在 便去查询二级商品类型
  if (!StringUtils.isEmpty(typeCode)) {
   List<ArticleType> secondArticleTypes = shopService.findSecondArticleTypes(typeCode);
   request.setAttribute("secondTypes", secondArticleTypes);
   request.setAttribute("typeCode", typeCode);
  }


  int count = shopService.findAllArticlesCount(typeCode, secondTypeCode, title);
  Pager pager = new Pager(pageIndex, count);
  System.out.println("pager = " + pager);
  request.setAttribute("pager", pager);
  //获取所有商品 根据一级类型 二级类型 title pager
  List<Article> articles = shopService.findAllArticles(typeCode, secondTypeCode, title, pager);

  request.setAttribute("articles", articles);
  request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);

 }
}
