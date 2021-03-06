package com.xuan.controller;

import com.xuan.pojo.Books;
import com.xuan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;
//查询所有书籍
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allbook";
    }
//    跳转到添加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "addBook";
    }
//    添加书籍
    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }
//    跳转到修改书籍界面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book",books );
        return "updateBook";
    }
//  修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);
        bookService.updateBook(book);
        Books books = bookService.queryBookById(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";
    }
//    删除书籍
@RequestMapping("/del/{bookId}")
public String deleteBook(@PathVariable("bookId") int id) {
    bookService.deleteBookById(id);
    return "redirect:/book/allBook";
}
//查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName ,Model model){
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<>();
        list.add(books);
        if (books==null){
            list=bookService.queryAllBook();
            model.addAttribute("erro","未查到");
        }
       // System.out.println(list);
        model.addAttribute("list", list);
        return "allbook";

    }

}
