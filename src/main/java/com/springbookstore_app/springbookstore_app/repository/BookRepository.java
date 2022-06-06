package com.springbookstore_app.springbookstore_app.repository;

import com.springbookstore_app.springbookstore_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book_store where book_name like :bookName%", nativeQuery = true)
    List<Book> findByBookName(String bookName);

    @Query(value = "select * from book_store where auther_name like :authorName%", nativeQuery = true)
    List<Book> findByBookAuthorName(String authorName);

    @Query(value = "select *from book_store order by book_name asc", nativeQuery = true)
    List<Book> sortingInAsce();

    @Query(value = "select *from address_book_table order by book_name desc ", nativeQuery = true)
    List<Book> sortingInDesc();

}