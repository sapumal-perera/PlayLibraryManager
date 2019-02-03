package dao;

import models.dbModels.Author;
import models.dbModels.BookModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookDao {

    /**
     * Get Book by ISBN
     *
     * @param isbn
     * @return
     */
    public BookModel getBook(Integer isbn) {
        BookModel bk = BookModel.find.byId(isbn);
        if (bk == null) {
            return null;
        }
        return bk;
    }

    /**
     * Check whether book exists
     *
     * @param isbn
     * @return
     */
    public boolean isBookExists(Integer isbn) {
        BookModel bk = BookModel.find.byId(isbn);
        if (bk == null) {
            return false;
        }
        return true;
    }

    /**
     * find Book By Title
     *
     * @param title
     * @return
     */
    public BookModel findBook(String title) {
        List<BookModel> dvdStore = BookModel.find.all();
        for (int i = 0; i < dvdStore.size(); i++) {
            if (dvdStore.get(i).getTitle().equalsIgnoreCase(title)) {
                return dvdStore.get(i);
            }
        }
        return null;
    }

    /**
     * Add new Book
     *
     * @param bookModel
     * @return
     */
    public boolean addBook(BookModel bookModel) {
        BookModel bk = bookModel;
        bk.save();

        return true;

    }

    /**
     * Return All Book List
     *
     * @return
     */
    public List<BookModel> getBookStore() {
        List<BookModel> bookStore = BookModel.find.all();
        return bookStore;
    }

    /**
     * Get Total Book Count
     *
     * @return
     */
    public int getBookCount() {
        List<BookModel> bookStore = BookModel.find.all();
        return bookStore.size();
    }

    /**
     * Update Book
     *
     * @param isbn
     * @param bookModel
     * @return
     */
    public boolean updateBook(Integer isbn, BookModel bookModel) {

        BookModel bk = BookModel.find.byId(isbn);
        if (bk == null) {
            return false;
        }
        //   bk.delete();
        bookModel.save();
        return true;
    }

    /**
     * Delete Book
     *
     * @param isbn
     * @return
     */
    public boolean deleteBook(Integer isbn) {


        BookModel bk = BookModel.find.byId(isbn);
        if (bk == null) {
            return false;
        }
        bk.delete();
        return true;

    }

    /**
     * Get Authors List
     *
     * @param isbn
     * @return
     */
    public ArrayList<Author> getAuthors(Integer isbn) {
        ArrayList<Author> auth = new ArrayList<Author>();
        List<Author> authors = Author.find.all();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getIsbn().equals(isbn)) {
                auth.add(authors.get(i));
            }
        }
        return auth;
    }
}
