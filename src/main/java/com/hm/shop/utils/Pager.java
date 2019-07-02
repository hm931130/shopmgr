package com.hm.shop.utils;

/**
 * @program: shopmgr
 * @Date: 2019/7/2 15:02
 * @Author: Mr.Han
 * @Description:
 */
public class Pager {

 private int pageIndex;

 private int pageSize = 9;

 private int totalPages;

 private int totalCount;


 public Pager(int pageIndex, int totalCount) {
  this.pageIndex = pageIndex;
  this.totalCount = totalCount;
  this.totalPages = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
  pageIndex = pageIndex <= 0 ? 1 : pageIndex;
  pageIndex = pageIndex >= getTotalPages() ? getTotalPages() : pageIndex;
  this.pageIndex = pageIndex;
  if (this.pageIndex == 0) {
   this.pageIndex = 1;
  }
 }

 public int getPageIndex() {
  return pageIndex;
 }

 public void setPageIndex(int pageIndex) {
  this.pageIndex = pageIndex;
 }

 public int getPageSize() {
  return pageSize;
 }

 public void setPageSize(int pageSize) {
  this.pageSize = pageSize;
 }

 public int getTotalPages() {
  return totalPages;
 }

 public void setTotalPages(int totalPages) {
  this.totalPages = totalPages;
 }

 public int getTotalCount() {
  return totalCount;
 }

 public void setTotalCount(int totalCount) {
  this.totalCount = totalCount;
 }

 public int getFirstParam() {
  return (getPageIndex() - 1) * getPageSize();
 }

 @Override
 public String toString() {
  return "Pager{" +
   "pageIndex=" + pageIndex +
   ", pageSize=" + pageSize +
   ", totalPages=" + totalPages +
   ", totalCount=" + totalCount +
   '}';
 }
}
