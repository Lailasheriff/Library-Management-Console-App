package model;

import dao.BookDao;
import dao.UserDao;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User implements Borrowable {

    @Transient
    Session session;

    @Transient
    private BookDao bookDao = new BookDao(session);

    @Transient
    private UserDao userDao = new UserDao(session);

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public RegularUser(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public RegularUser() {
    }

    public void borrowBook(Book book) {
        BookDao bookDao = new BookDao(session);
        UserDao userDao = new UserDao(session);
        RegularUser user = (RegularUser) userDao.getUserById(this.getId());
        Book wantedBook = bookDao.getBookById(book.getId());
        if (wantedBook == null) {
            System.out.println("Book was not found");
        } else if (wantedBook.getAvailableCopies() <= 0) {
            System.out.println("No available copies of this book");
        } else if (user.getBorrowedBooks().contains(wantedBook)) {
            System.out.println("You already borrowed this book");
        } else if (user.getBorrowedBooks().size() >= 3) {
            System.out.println("You cannot borrow more than 3 books");
        } else {
            wantedBook.setAvailableCopies(wantedBook.getAvailableCopies() - 1);
            user.getBorrowedBooks().add(wantedBook);
            bookDao.updateBook(wantedBook);
            userDao.updateUser(user);
            System.out.println("Book borrowed");
        }
    }

    public void returnBook(Book book) {
        BookDao bookDao = new BookDao(session);
        UserDao userDao = new UserDao(session);
        RegularUser user = (RegularUser) userDao.getUserById(this.getId());
        Book finishedBook = bookDao.getBookById(book.getId());
        if (finishedBook == null) {
            System.out.println("You didn't borrow any book");
        } else if (!user.getBorrowedBooks().contains(finishedBook)) {
            System.out.println("You didn't borrow this book");
        } else {
            finishedBook.setAvailableCopies(finishedBook.getAvailableCopies() + 1);
            user.getBorrowedBooks().remove(finishedBook);

            bookDao.updateBook(finishedBook);
            userDao.updateUser(user);

            System.out.println("Book returned");
        }
    }

    public void viewBooks() {
        BookDao bookDao = new BookDao(session);
        List<Book> books = bookDao.getAllBooks();
        books.sort(Comparator.comparing(Book::getTitle));
        if (books.isEmpty()) {
            System.out.println("No books in the catalog.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                System.out.printf("ID:%d %s by %s, genre: %s, Available: %d %n", book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailableCopies());
            }
        }
    }

}
