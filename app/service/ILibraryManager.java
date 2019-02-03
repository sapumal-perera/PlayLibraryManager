package service;

import com.fasterxml.jackson.databind.JsonNode;
import models.Book;
import models.Dvd;
import models.dbModels.BookModel;
import models.dbModels.DvdModel;
import models.OverDueItem;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILibraryManager {
    String addNewBook(JsonNode newBook);

    String deleteBook(Integer isbn);

    ArrayList<Book> displayBookList();

    ArrayList<Dvd> displayDvdList();

    String borrowBook(Integer isbn, int daysToReturn, Integer borrower);

    String returnBook(Integer isbn);

    String addNewDvd(JsonNode newDvd);

    String deleteDvd(Integer isbn);

    String borrowDVD(Integer dvdId, int daysToReturn, Integer borrower);

    String returnDvd(Integer isbn);

    ArrayList<OverDueItem> generateReport();


}
