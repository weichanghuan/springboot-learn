package com.learn.model.base;


import com.learn.exception.ArgumentException;

import java.io.Serializable;


public class Page implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2557385716251322428L;

    private static final int DEFAULT_PAGESIZE = 20;

    /**
     * 当前页
     */
    private int currPage = 1;
    /**
     * 每页记录数
     */
    private int pageSize = DEFAULT_PAGESIZE;;
    /**
     * 总记录数
     */
    private int count;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 是否有前一页
     */
    private Boolean hasPre;
    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 页面显示用的字符串
     */
    private String pageStr;

    /**
     * 分页中起始记录
     */
    private int startRow;
    /**
     * 分页中结束记录
     */
    private int endRow;

    /**
     * 创建分页信息对象。
     *
     * @param pageSize
     *        每页记录数。必须大于0。
     * @param currPage
     *        第几页。Page index从1开始递增，第1页的page index为1，第2页的page
     *        index为2，以此类推第n页的page index为n。
     */
    public Page(int pageSize, int currPage) {
        if (currPage <= 0) {
            throw new ArgumentException("分页信息错误，page index必须从1开始递增");
        }
        if (pageSize <= 0) {
            throw new ArgumentException("分页信息错误，page size必须大于0");
        }
        this.currPage = currPage;
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        if (totalPage < 1) {
            if (count % pageSize > 0) {
                totalPage = count / pageSize + 1;
            } else {
                totalPage = count / pageSize;
            }
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getHasPre() {
        if (currPage > 1) {
            hasPre = true;
        } else {
            hasPre = false;
        }
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    public Boolean getHasNext() {
        if (currPage < getTotalPage()) {
            hasNext = true;
        } else {
            hasNext = false;
        }
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getPageStr() {
        return pageStr;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    @Override
    public String toString() {
        return "Page [currPage=" + currPage + ", pageSize=" + pageSize + ", count=" + count + ", totalPage="
                + totalPage + ", hasPre=" + hasPre + ", hasNext=" + hasNext + ", pageStr=" + pageStr
                + ", startRow=" + startRow + ", endRow=" + endRow + "]";
    }

    /**
     * @param pagerInfo
     *        分页信息
     * @return boolean 是否非法
     */
    public static boolean isInValid(Page page) {
        if (page == null) {
            return true;
        }
        if (page.getCurrPage() < 0 || page.getPageSize() <= 0 || page.getCount() < 0 || page.getStartRow() < 0) {
            return true;
        }
        return false;
    }

    /**
     * 取MySQL数据库 limit m,n 语句的开始索引值m。
     *
     * @return start
     */
    public int getStartRow() {
        startRow = (currPage > 0 ? (currPage - 1) : 0) * pageSize;
        return startRow;
    }

    public int getEndRow() {
        endRow = (currPage > 0 ? currPage : 1) * pageSize;
        return endRow;
    }
}

