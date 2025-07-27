package dao;

import model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BookDao {
    private final Session session;

    public BookDao(Session session) {
        this.session = session;
    }

    public void saveBook(Book book) {
            Transaction tx = session.beginTransaction();
            try {
                session.save(book);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
    }

    public void updateBook(Book book) {
            Transaction tx = session.beginTransaction();
            try {
                session.merge(book);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
    }

    public void deleteBook(Book book) {
            Transaction tx = session.beginTransaction();
            try {
                session.delete(book);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
    }

    public Book getBookById(int id) {
            return (Book) session.get(Book.class, id);
    }

    public List<Book> getAllBooks() {
            return (List<Book>) session.createQuery("FROM Book", Book.class).list();
    }

    public List<Book> getBooksByAuthor(String author) {
            return session.createQuery("FROM Book WHERE author LIKE :author", Book.class).setParameter("author", "%" + author + "%").list();
    }

    public List<Book> getBooksByTitle(String title) {
            return (List<Book>) session.createQuery("FROM Book WHERE title LIKE :title", Book.class).setParameter("title", "%" + title + "%").list();
    }

    public List<Book> getBooksByGenre(String genre) {
            return (List<Book>) session.createQuery("FROM Book WHERE genre LIKE :genre", Book.class).setParameter("genre", "%" + genre + "%").list();
    }
}
