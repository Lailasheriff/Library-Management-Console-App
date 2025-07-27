package service;

import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.User;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class SearchService<T> {
    private Session session;
    private Class<T> tableType;

    public SearchService(Class<T> tableType, Session session) {
        this.tableType = tableType;
        this.session = session;
    }

    public T searchById(int id) {
        if (tableType == User.class) {
            UserDao userDao = new UserDao(session);
            User user = userDao.getUserById(id);
            return (T) user;
        } else if (tableType == Book.class) {
            BookDao bookDao = new BookDao(session);
            Book book = bookDao.getBookById(id);
            return (T) book;
        } else {
            return null;
        }
    }

    public List<T> searchByName(String name, String type) {
        if (tableType == Book.class) {
            BookDao bookDao = new BookDao(session);
            List<Book> books = new ArrayList<>();
            if (type.equals("title")) {
                books = bookDao.getBooksByTitle(name);
            } else if (type.equals("author")) {
                books = bookDao.getBooksByAuthor(name);
            } else if (type.equals("genre")) {
                books = bookDao.getBooksByGenre(name);
            }
            return (List<T>) books;
        } else if (tableType == User.class) {
            UserDao userDao = new UserDao(session);
            List<User> users = userDao.getUsersByName(name);
            return (List<T>) users;
        } else {
            return null;
        }
    }
}