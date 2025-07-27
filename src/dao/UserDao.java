package dao;

import model.Book;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDao {
    private final Session session;

    public UserDao(Session session) {
        this.session = session;
    }

    public User getUserById(int id) {
        return session.get(User.class, id);
    }

    public void saveUser(User user) {
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void updateUser(User user) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public List<User> getUsersByName(String name) {
        return (List<User>) session.createQuery("FROM User WHERE name LIKE :name", User.class).setParameter("name", "%" + name + "%").list();
    }

    public List<User> getUsersBorrowingBook(Book book) {
        return session.createQuery("SELECT u FROM User u JOIN u.borrowedBooks b WHERE b.id = :bookId",User.class).setParameter("bookId", book.getId()).list();
    }
}
