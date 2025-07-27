package model;

import dao.BookDao;
import dao.UserDao;

import javax.persistence.DiscriminatorValue;
import java.util.List;

@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private final BookDao bookDao;
    private final UserDao userDao;

    public Admin(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public void addBook(String title, String author, String genre, int copies) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setAvailableCopies(copies);
        bookDao.saveBook(book);
        System.out.println("Book added");
    }

    public void deleteBook(int id) {
        Book book = bookDao.getBookById(id);
        if (book == null) {
            System.out.println("Book not found");
        } else {
            List<User> users = userDao.getUsersBorrowingBook(book);
            if (users.isEmpty()) {
                bookDao.deleteBook(book);
                System.out.println("Book deleted");
            }
            else{
                System.out.println("Can't delete borrowed book");
            }
        }
    }

    public void registerUser(String username) {
        User user = new RegularUser(bookDao, userDao);
        user.setName(username);
        userDao.saveUser(user);
        System.out.println("Regular user registered");
    }

    public void editBook(int id, String title, String author, String genre, int copies) {
        Book book = bookDao.getBookById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setAvailableCopies(copies);
        bookDao.updateBook(book);
        System.out.println("Book updated");
    }
}
