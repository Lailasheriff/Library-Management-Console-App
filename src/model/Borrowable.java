package model;

public interface Borrowable {
    void borrowBook(Book book);
    void returnBook(Book book);
}
