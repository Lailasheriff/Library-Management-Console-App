package service;

import model.Book;
import model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LibraryStorageService {
    private Map<String, Book> booksMap = new HashMap<>();
    private Map<String, User> usersMap = new HashMap<>();
    private Set<String> uniqueGenres = new HashSet<>();

    public void addBook(Book book){
        booksMap.put(book.getTitle(), book);
        uniqueGenres.add(book.getGenre());
    }

    public void deleteBook(Book book){
        booksMap.remove(book.getTitle());
        uniqueGenres.remove(book.getGenre());
    }

    public void addUser(User user){
        usersMap.put(user.getName(), user);
    }

    public void deleteUser(User user){
        usersMap.remove(user.getName());
    }

    public Book getBook(String title){
        return booksMap.get(title);
    }

    public User getUser(String username){
        return usersMap.get(username);
    }

    public Map<String, Book> getBooksMap() {
        return booksMap;
    }

    public void setBooksMap(Map<String, Book> booksMap) {
        this.booksMap = booksMap;
    }

    public Map<String, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(Map<String, User> usersMap) {
        this.usersMap = usersMap;
    }

    public Set<String> getUniqueGenres() {
        return uniqueGenres;
    }

    public void setUniqueGenres(Set<String> uniqueGenres) {
        this.uniqueGenres = uniqueGenres;
    }
}
