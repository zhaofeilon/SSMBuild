package com.xuan.dao;

import com.xuan.pojo.Books;

import java.util.List;

public interface BookMapper {
//    增加一本书
    int addBook(Books books);
//    根据id删除一本书
    int deleteBookById(int id);
//    更新book
    int updateBook(Books books);
//    根据ID查询书
    Books queryBookById(int id);
//    查询所有的书
    List<Books> queryAllBook();

    Books queryBookByName(String bookName);
}
