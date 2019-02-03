package service;

import com.fasterxml.jackson.databind.JsonNode;
import dao.BookDao;
import dao.DvdDao;
import dao.MemberDao;
import models.*;
import models.dbModels.*;
import java.text.DecimalFormat;
import java.util.*;

public class WestminsterLibraryManager implements ILibraryManager {

    private ArrayList<OverDueItem> overdueArray = new ArrayList<OverDueItem>();
    private BookDao bookDao = new BookDao();
    private DvdDao dvdDao = new DvdDao();
    private MemberDao memberDao = new MemberDao();

    /**
     * Add new book
     * Update though model
     *
     * @param newBook
     */
    public String addNewBook(JsonNode newBook) {
        if (bookDao.getBookCount() < 100) {
            if (!bookDao.isBookExists(newBook.get("isbn").asInt())) {
                BookModel bookModel = new BookModel();
                bookModel.setIsbn(newBook.get("isbn").asInt());
                bookModel.setPublisher(newBook.get("publisher").toString().replace("\"", ""));
                this.addNewAuthors(newBook.get("author").toString().replace("\"", ""), newBook.get("isbn").asInt());
                bookModel.setTitle(newBook.get("title").toString().replace("\"", ""));
                bookModel.setTotalNumberOfPages(newBook.get("pageCount").asInt());
                bookDao.addBook(bookModel);
                return "Book Added Successfully";
            } else {
                return "Book with this ISBN Already Exists";
            }
        } else {
            return "No Space Left for a New Book";
        }
    }

    /**
     * Add new Dvd
     * Update though model
     *
     * @param newDvd
     */
    public String addNewDvd(JsonNode newDvd) {
        if (dvdDao.getDvdCount() < 50) {
            if (!dvdDao.isDvdExists(newDvd.get("dvdId").asInt())) {
                DvdModel dvdModel = new DvdModel();
                dvdModel.setDvdId(newDvd.get("dvdId").asInt());
                dvdModel.setTitle(newDvd.get("title").toString().replace("\"", ""));
                dvdModel.setProducer(newDvd.get("producer").toString().replace("\"", ""));
                this.addNewActors(newDvd.get("actors").toString().replace("\"", ""), newDvd.get("dvdId").asInt());
                this.addNewLanguages(newDvd.get("languages").toString().replace("\"", ""), newDvd.get("dvdId").asInt());
                this.addNewSubtitles(newDvd.get("subtitles").toString().replace("\"", ""), newDvd.get("dvdId").asInt());
                dvdDao.addDvd(dvdModel);
                return "DVD Added Successfully";
            } else {
                return "Dvd with this Id Already Exists";
            }
        } else {
            return "No Space Left for a New DVD";
        }
    }

    /**
     * Add new authors to Book
     * Update though model
     *
     * @param authors
     * @param isbn
     */
    public void addNewAuthors(String authors, Integer isbn) {

        String[] authorList = authors.split(",");
        for (int i = 0; i < authorList.length; i++) {
            Author aut = new Author();
            aut.setName(authorList[i]);
            aut.setIsbn(isbn);
            aut.save();
        }

    }

    /**
     * Add new Actors to DVD
     * Update though model
     *
     * @param actors
     * @param isbn
     */
    public void addNewActors(String actors, Integer isbn) {

        String[] authorList = actors.split(",");
        for (int i = 0; i < authorList.length; i++) {
            Actor act = new Actor();
            act.setName(authorList[i]);
            act.setIsbn(isbn);
            act.save();
        }

    }

    /**
     * Add new Languages to DVD
     * Update though model
     *
     * @param languages
     * @param isbn
     */
    public void addNewLanguages(String languages, Integer isbn) {
        String[] languageList = languages.split(",");
        for (int i = 0; i < languageList.length; i++) {
            Language lan = new Language();
            lan.setName(languageList[i]);
            lan.setIsbn(isbn);
            lan.save();
        }

    }

    /**
     * Add new Subtitles to DVD
     * Update though model
     *
     * @param subtitle
     * @param isbn
     */
    public void addNewSubtitles(String subtitle, Integer isbn) {
        String[] subList = subtitle.split(",");
        for (int i = 0; i < subList.length; i++) {
            Subtitle sub = new Subtitle();
            sub.setName(subList[i]);
            sub.setIsbn(isbn);
            sub.save();
        }

    }

    /**
     * Delete Book
     * Update though model
     *
     * @param isbn
     */
    public String deleteBook(Integer isbn) {
        if (bookDao.isBookExists(isbn)) {
            if (bookDao.deleteBook(isbn)) {
                return "Book ISBN: " + isbn + " Deleted Successfully";
            } else {
                return "Error in Delete";
            }
        }
        return "No Book With ISBN: " + isbn;

    }


    /**
     * delete dvd
     * Update though model
     *
     * @param id
     */
    public String deleteDvd(Integer id) {
        if (dvdDao.isDvdExists(id)) {
            if (dvdDao.deleteDvd(id)) {
                return "Dvd Id: " + id + " Deleted Successfully";
            } else {
                return "Error in Delete";
            }
        }
        return "No Dvd With Id: " + id;

    }

    /**
     * display list of library Items
     */
    public ArrayList<Book> displayBookList() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        List<BookModel> bookObjs = bookDao.getBookStore();
        for (int i = 0; i < bookObjs.size(); i++) {
            Book book = new Book();
            book.setIsbn(bookObjs.get(i).getIsbn());
            book.setTitle(bookObjs.get(i).getTitle());
            book.setAvailable(bookObjs.get(i).isAvailable());
            if (book.isAvailable()) {
                book.setSector("Available");
            } else {
                book.setSector("Unavailable");
            }
            book.setBorrowedDate(bookObjs.get(i).getBorrowedDate());
            ArrayList<Author> auth = bookDao.getAuthors(book.getIsbn());
            String authors = "";
            for (int j = 0; j < auth.size(); j++) {
                authors += auth.get(j).getName() + " | ";
            }
            book.setAuthors(authors);
            book.setCurrentReader(bookObjs.get(i).getBorrower());
            book.setBorrowedHour(bookObjs.get(i).getBorrowedHour());
            book.setTotalNumberOfPages(bookObjs.get(i).getTotalNumberOfPages());
            book.setBorrowedTime(bookObjs.get(i).getBorrowedTime());
            book.setBorrowedYear(bookObjs.get(i).getBorrowedYear());
            book.setPublicationDate(bookObjs.get(i).getPublicationDate());
            book.setReturnedDate(bookObjs.get(i).getReturnDate());
            book.setBorrowedCurrentDaysCount(bookObjs.get(i).getBorrowedCurrentDaysCount());
            book.setPublisher(bookObjs.get(i).getPublisher());
            bookList.add(book);
        }

        return bookList;

    }

    public ArrayList<Dvd> displayDvdList() {
        ArrayList<Dvd> dvdList = new ArrayList<Dvd>();
        List<DvdModel> dvdModelObj = dvdDao.getDvdStore();
        for (int i = 0; i < dvdModelObj.size(); i++) {
            Dvd dvd = new Dvd();

            dvd.setDvdId(dvdModelObj.get(i).getDvdId());
            dvd.setTitle(dvdModelObj.get(i).getTitle());
            dvd.setAvailable(dvdModelObj.get(i).isAvailable());
            if (dvd.isAvailable()) {
                dvd.setSector("Available");
            } else {
                dvd.setSector("Unavailable");
            }
            dvd.setCurrentReader(dvdModelObj.get(i).getBorrower());
            ArrayList<Actor> act = dvdDao.getActors(dvd.getDvdId());
            String actors = "";
            for (int j = 0; j < act.size(); j++) {
                actors += act.get(j).getName() + " | ";
            }
            ArrayList<Language> lan = dvdDao.getLanguages(dvd.getDvdId());
            String langs = "";
            for (int j = 0; j < lan.size(); j++) {
                langs += lan.get(j).getName() + " | ";
            }
            ArrayList<Subtitle> sub = dvdDao.getSubtitles(dvd.getDvdId());
            String subs = "";
            for (int j = 0; j < sub.size(); j++) {
                subs += sub.get(j).getName() + " | ";
            }
            dvd.setActors(actors);
            dvd.setAvailableSubtitles(subs);
            dvd.setAvailableLanguages(langs);
            dvd.setBorrowedDate(dvdModelObj.get(i).getBorrowedDate());
            dvd.setBorrowedHour(dvdModelObj.get(i).getBorrowedHour());
            dvd.setBorrowedTime(dvdModelObj.get(i).getBorrowedTime());
            dvd.setBorrowedYear(dvdModelObj.get(i).getBorrowedYear());
            dvd.setPublicationDate(dvdModelObj.get(i).getPublicationDate());
            dvd.setReturnedDate(dvdModelObj.get(i).getReturnDate());
            dvd.setProducer(dvdModelObj.get(i).getProducer());
            dvd.setBorrowedCurrentDaysCount(dvdModelObj.get(i).getBorrowedCurrentDaysCount());
            dvdList.add(dvd);
        }
        return dvdList;
    }

    /**
     * Borrow a book
     * Update though model
     *
     * @param isbn
     * @param daysToReturn
     */
    public String borrowBook(Integer isbn, int daysToReturn, Integer borrower) {
        if (bookDao.isBookExists(isbn)) {
            if(memberDao.isUserExists(borrower)){
            DateTime dateTime = new DateTime();
            int currentDaysCount = dateTime.getCurrentDaysCount();
            int dayOfYear = dateTime.getDayOfYear();
            String time = dateTime.getGMTTime();
            int year = dateTime.getYears();
            BookModel bookModel = bookDao.getBook(isbn);
            if (bookModel.isAvailable()) {
                bookModel.setBorrower(borrower);
                bookModel.setBorrowedHour(dateTime.getHours());
                bookModel.setBorrowedDate(dateTime.getDayOfYear());
                bookModel.setBorrowedYear(dateTime.getYears());
                bookModel.setReturnDate(daysToReturn + currentDaysCount);
                bookModel.setBorrowedTime(dateTime.getGMTTime());
                bookModel.setBorrowedCurrentDaysCount(currentDaysCount);
                bookModel.setAvailable(false);
                if (bookDao.updateBook(isbn, bookModel)) {
                    return "Book ISBN: " + isbn + " Issued Successfully.";
                } else {
                    return "Error in Book Issue";
                }
            } else {
                return "This Book is Already Borrowed.";
            }
            } else {
                return "User Id is not valid";
            }
        }
        return "No Book with ISBN:" + isbn;
    }

    /**
     * Reserve book for user
     * @param isbn
     * @param daysToReturn
     * @param borrower
     * @return
     */
    public String reserveBook(Integer isbn, int daysToReturn, Integer borrower) {
        if (bookDao.isBookExists(isbn)) {
            if(memberDao.isUserExists(borrower)){
                DateTime dateTime = new DateTime();
                int currentDaysCount = dateTime.getCurrentDaysCount();
             BookReserveModel bookReserveModel = new BookReserveModel();
             bookReserveModel.setBorrower(borrower);
             bookReserveModel.setIsbn(isbn);
             bookReserveModel.setStartDate(currentDaysCount);
             bookReserveModel.setTotalDays(daysToReturn);
             bookReserveModel.save();
             return "User id: "+ borrower+" reserved book ISBN: "+isbn;
            } else {
                return "User Id is not valid";
            }
        }
        return "No Book with ISBN:" + isbn;
    }
    /**
     * Borrow a Dvd
     * Update though model
     *
     * @param dvdId
     * @param daysToReturn
     */
    public String borrowDVD(Integer dvdId, int daysToReturn, Integer borrower) {
        if (dvdDao.isDvdExists(dvdId)) {
            if(memberDao.isUserExists(borrower)) {
                DateTime dateTime = new DateTime();
                int currentDaysCount = dateTime.getCurrentDaysCount();
                int dayOfYear = dateTime.getDayOfYear();
                String time = dateTime.getGMTTime();
                int year = dateTime.getYears();
                DvdModel dvdModel = dvdDao.getDvd(dvdId);
                if (dvdModel.isAvailable()) {
                    dvdModel.setBorrower(borrower);

                    dvdModel.setBorrowedHour(dateTime.getHours());
                    dvdModel.setBorrowedDate(dateTime.getDayOfYear());
                    dvdModel.setBorrowedYear(dateTime.getYears());
                    dvdModel.setReturnDate(daysToReturn + currentDaysCount);
                    dvdModel.setBorrowedTime(dateTime.getGMTTime());
                    dvdModel.setBorrowedCurrentDaysCount(currentDaysCount);
                    dvdModel.setAvailable(false);
                    if (dvdDao.updateDvd(dvdId, dvdModel)) {
                        return "DVD Id: " + dvdId + " Issued Successfully.";
                    } else {
                        return "Error in DVD Issue";
                    }
                } else {
                    return "This Dvd is Already Borrowed.";
                }
            } else {
                return "User Id is not valid";
            }
        }
        return "No DVD with Id:" + dvdId;

    }

    /**
     * Return Book
     *
     * @param isbn
     */
    public String returnBook(Integer isbn) {
        if (bookDao.isBookExists(isbn)) {
            DateTime dateTime = new DateTime();
            int currentDaysCount = dateTime.getCurrentDaysCount();
            int hour = dateTime.getHours();
            int penaltyDays = 0;
            double totalFee = 0;
            BookModel bookModel = bookDao.getBook(isbn);
            if (!bookModel.isAvailable()) {
                int returnDate = bookModel.getReturnDate();

                bookModel.setBorrower(null);
                bookModel.setBorrowedCurrentDaysCount(0);
                bookModel.setBorrowedHour(0);
                bookModel.setBorrowedTime("");
                bookModel.setBorrowedYear(0);
                bookModel.setReturnDate(0);
                penaltyDays = currentDaysCount - returnDate;
                if (penaltyDays > 0) {
                    if (penaltyDays < 4) {
                        totalFee = (penaltyDays * 0.2 * 24 + hour * 0.2);

                    } else {
                        totalFee = (penaltyDays * 0.5 * 24 + hour * 0.5);
                    }
                }
                bookModel.setAvailable(true);

                if (bookDao.updateBook(isbn, bookModel)) {
                    return "Book ISBN: " + isbn + " Returned Successfully. Member has to pay: " + totalFee;
                } else {
                    return "Error in Book Issue";
                }
            }
            return "This Book was not Borrowed";
        }

        return "No Book with ISBN:" + isbn;
    }

    /**
     * Return Dvd
     * Update though model
     *
     * @param isbn
     */
    public String returnDvd(Integer isbn) {
        if (dvdDao.isDvdExists(isbn)) {
            DateTime dateTime = new DateTime();
            int currentDaysCount = dateTime.getCurrentDaysCount();
            int hour = dateTime.getHours();
            int penaltyDays = 0;
            double totalFee = 0;
            DvdModel dvdModel = dvdDao.getDvd(isbn);
            if (!dvdModel.isAvailable()) {
            int returnDate = dvdModel.getReturnDate();
                dvdModel.setBorrowedCurrentDaysCount(0);
                dvdModel.setBorrowedHour(0);
                dvdModel.setBorrower(null);
                dvdModel.setBorrowedTime("");
                dvdModel.setBorrowedYear(0);
                dvdModel.setReturnDate(0);
                penaltyDays = currentDaysCount - returnDate;
                if (penaltyDays > 0) {
                    if (penaltyDays < 4) {
                        totalFee = (penaltyDays * 0.2 * 24 + hour * 0.2);

                    } else {
                        totalFee = ((3* 0.2 * 24 + hour * 0.2) + ((penaltyDays - 3) * 0.5 * 24 + hour * 0.5));
                    }
                }

                dvdModel.setAvailable(true);
                if (dvdDao.updateDvd(isbn, dvdModel)) {
                    return "DVD Id: " + isbn + " Returned Successfully.Member has to pay: " + totalFee;
                } else {
                    return "Error in DVD Return";
                }
            }
            return "This DVD was not Borrowed";
        }
        return "No DVD with Id:" + isbn;
    }

    /**
     * Generate report about overdue items
     * Update though model
     */
    public ArrayList<OverDueItem> generateReport() {
        ArrayList<OverDueItem> overdueArray = new ArrayList<OverDueItem>();
        DecimalFormat df2 = new DecimalFormat(".##");
        DateTime dateTime = new DateTime();
        int currentDaysCount = dateTime.getCurrentDaysCount();
        int hour = dateTime.getHours();

        int penaltyDays = 0;
        double totalFee = 0;
        for (BookModel bk : bookDao.getBookStore()) {
            if (!bk.isAvailable()) {
                List<String> x = new ArrayList<String>();
                int returnDate = bk.getReturnDate();
                penaltyDays = currentDaysCount - returnDate;
                if (penaltyDays > 0) {
                    if (penaltyDays < 4) {
                        totalFee = (penaltyDays * 0.2 * 24 + hour * 0.2);
                    } else {
                        totalFee = ((3* 0.2 * 24 + hour * 0.2) + ((penaltyDays - 3) * 0.5 * 24 + hour * 0.5));
                    }
                    OverDueItem od = new OverDueItem();
                    od.setIsbn(bk.getIsbn());
                    od.setTotalFee(df2.format(totalFee));
                    od.setBorrowerId(bk.getBorrower());
                    od.setType("Book");
                    overdueArray.add(od);


                }
            }
        }
        for (DvdModel dvdModel : dvdDao.getDvdStore()) {
            if (!dvdModel.isAvailable()) {
                List<String> y = new ArrayList<String>();
                int returnDate = dvdModel.getReturnDate();
                penaltyDays = currentDaysCount - returnDate;
                if (penaltyDays > 0) {
                    if (penaltyDays < 4) {
                        totalFee = (penaltyDays * 0.2 * 24 + hour * 0.2);

                    } else {
                        totalFee = ((3* 0.2 * 24 + hour * 0.2) + ((penaltyDays - 3) * 0.5 * 24 + hour * 0.5));
                    }
                    OverDueItem od = new OverDueItem();
                    od.setIsbn(dvdModel.getDvdId());
                    od.setTotalFee(df2.format(totalFee));
                    od.setBorrowerId(dvdModel.getBorrower());
                    od.setType("DVD");
                    overdueArray.add(od);
                }
            }
        }
        OverdueItemComparator comp = new OverdueItemComparator();
        overdueArray.sort(comp);
        return overdueArray;
    }


    /**
     * Find Book By Title
     *
     * @param title
     * @return
     */
    public Book findBook(String title) {
        BookModel bk = bookDao.findBook(title);
        Book book = new Book();
        if (bk != null) {
            book.setIsbn(bk.getIsbn());
            book.setTitle(bk.getTitle());
            book.setAvailable(bk.isAvailable());
            if (book.isAvailable()) {
                book.setSector("Available");
            } else {
                book.setSector("Unavailable");
            }
            book.setBorrowedDate(bk.getBorrowedDate());
            ArrayList<Author> auth = bookDao.getAuthors(book.getIsbn());
            String authors = "";
            for (int j = 0; j < auth.size(); j++) {
                authors += auth.get(j).getName() + " | ";
            }
            book.setAuthors(authors);
            book.setCurrentReader(bk.getBorrower());
            book.setBorrowedHour(bk.getBorrowedHour());
            book.setTotalNumberOfPages(bk.getTotalNumberOfPages());
            book.setBorrowedTime(bk.getBorrowedTime());
            book.setBorrowedYear(bk.getBorrowedYear());
            book.setPublicationDate(bk.getPublicationDate());
            book.setReturnedDate(bk.getReturnDate());
            book.setBorrowedCurrentDaysCount(bk.getBorrowedCurrentDaysCount());
            book.setPublisher(bk.getPublisher());
            return book;
        }
        return null;
    }

    /**
     * Find DVD by by Title
     *
     * @param title
     * @return
     */
    public Dvd findDvd(String title) {
        Dvd dvd = new Dvd();
        DvdModel dv = dvdDao.findDvd(title);
        if (dv != null) {
            dvd.setDvdId(dv.getDvdId());
            dvd.setTitle(dv.getTitle());
            dvd.setAvailable(dv.isAvailable());
            if (dvd.isAvailable()) {
                dvd.setSector("Available");
            } else {
                dvd.setSector("Unavailable");
            }
            dvd.setCurrentReader(dv.getBorrower());
            ArrayList<Actor> act = dvdDao.getActors(dvd.getDvdId());
            String actors = "";
            for (int j = 0; j < act.size(); j++) {
                actors += act.get(j).getName() + " | ";
            }
            ArrayList<Language> lan = dvdDao.getLanguages(dvd.getDvdId());
            String langs = "";
            for (int j = 0; j < lan.size(); j++) {
                langs += lan.get(j).getName() + " | ";
            }
            ArrayList<Subtitle> sub = dvdDao.getSubtitles(dvd.getDvdId());
            String subs = "";
            for (int j = 0; j < sub.size(); j++) {
                subs += sub.get(j).getName() + " | ";
            }
            dvd.setActors(actors);
            dvd.setAvailableSubtitles(subs);
            dvd.setAvailableLanguages(langs);
            dvd.setBorrowedDate(dv.getBorrowedDate());
            dvd.setBorrowedHour(dv.getBorrowedHour());
            dvd.setBorrowedTime(dv.getBorrowedTime());
            dvd.setBorrowedYear(dv.getBorrowedYear());
            dvd.setPublicationDate(dv.getPublicationDate());
            dvd.setReturnedDate(dv.getReturnDate());
            dvd.setProducer(dv.getProducer());
            dvd.setBorrowedCurrentDaysCount(dv.getBorrowedCurrentDaysCount());
            return dvd;
        }
        return null;

    }

    /**
     * Add new member
     *
     * @param memberModel
     * @return
     */
    public boolean addNewMember(MemberModel memberModel) {
        memberDao.addMember(memberModel);
        return true;
    }

    /**
     *
     * @param id
     * @return
     */
    public String deleteMem(Integer id) {
        if (memberDao.isMemExists(id)) {
            if (memberDao.deleteMember(id)) {
                return "Dvd Id: " + id + " Deleted Successfully";
            } else {
                return "Error in Delete";
            }
        }
        return "No Dvd With Id: " + id;

    }
    public String deleteResEntry(Integer id) {
        BookReserveModel brm = BookReserveModel.find.byId(id);
        if (brm == null) {
            return "Cant find the entry";
        }
        brm.delete();
        return "Entry Delete Successfully";
    }
    /**
     * get All member s Lst
     *
     * @return
     */
    public ArrayList<Member> viewMembers() {
        ArrayList<Member> memList = new ArrayList<Member>();
        List<MemberModel> memObj = memberDao.getMemberStore();
        for (int i = 0; i < memObj.size(); i++) {
            Member mv = new Member();
            mv.setId(memObj.get(i).getId());
            mv.setEmail(memObj.get(i).getEmail());
            mv.setMobileNumber(memObj.get(i).getMobileNumber());
            mv.setName(memObj.get(i).getName());
            memList.add(mv);
        }

        return memList;

    }
    public ArrayList<BookReserve> getReserveDetails() {
        ArrayList<BookReserve> resList = new ArrayList<BookReserve>();
        List<BookReserveModel> rsvObj = BookReserveModel.find.all();
        for (int i = 0; i < rsvObj.size(); i++) {
            BookReserve bookRes = new BookReserve();
            bookRes.setId(rsvObj.get(i).getId());
            bookRes.setBorrower(rsvObj.get(i).getBorrower());
            bookRes.setIsbn(rsvObj.get(i).getIsbn());
            bookRes.setStartDate(rsvObj.get(i).getStartDate());
            bookRes.setTotalDays(rsvObj.get(i).getTotalDays());
            bookRes.setRemainingDays(DateTime.getCurrentDaysCount()-rsvObj.get(i).getStartDate());
            resList.add(bookRes);
        }
        return resList;
    }

}

class OverdueItemComparator implements Comparator<OverDueItem> {

    public int compare(OverDueItem e1, OverDueItem e2) {
       int num = (int) (Double.parseDouble(e1.getTotalFee()) - Double.parseDouble(e2.getTotalFee()));
        return num;
    }

    public String toString() {
        return "OverdueItemComparator";
    }
}