package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.*;
import models.dbModels.DvdModel;
import models.dbModels.MemberModel;
import service.WestminsterLibraryManager;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;

public class ItemController extends Controller {
    WestminsterLibraryManager libraryManager;

    /**
     * Create Library Manager Instance
     */
    public ItemController() {
        this.libraryManager = new WestminsterLibraryManager();

    }

    /**
     * Add Book
     *
     * @return
     */
    public Result addBook() {
        JsonNode newBook = request().body().asJson();
        String response = libraryManager.addNewBook(newBook);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Add Dvd
     *
     * @return
     */
    public Result addDvd() {

        JsonNode newDvd = request().body().asJson();
        String response = libraryManager.addNewDvd(newDvd);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Get Books list
     *
     * @return
     */
    public Result getBooks() {
        ArrayList<Book> bookMap = libraryManager.displayBookList();
        Gson gson = new Gson();
        String jsonArr = gson.toJson(bookMap);
        return ok(jsonArr);
    }

    /**
     * Get Dvd List
     *
     * @return
     */
    public Result getDvds() {
        ArrayList<Dvd> dvdMap = libraryManager.displayDvdList();
        Gson gson = new Gson();
        String jsonArr = gson.toJson(dvdMap);
        return ok(jsonArr);
    }

    /**
     * Delete a Book
     *
     * @return
     */
    public Result deleteBook() {
        JsonNode isbn = request().body().asJson();
        Integer newIsbn = isbn.get("isbn").asInt();
        String response = libraryManager.deleteBook(newIsbn);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Delete a DVD
     *
     * @return
     */
    public Result deleteDvd() {
        JsonNode isbn = request().body().asJson();
        Integer newIsbn = isbn.get("isbn").asInt();
        String response = libraryManager.deleteDvd(newIsbn);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Issue a Book
     *
     * @return
     */
    public Result borrowBook() {
        JsonNode borrowData = request().body().asJson();
        Integer newIsbn = borrowData.get("isbn").asInt();
        Integer borrower = borrowData.get("borrower").asInt();
        int daysToReturn = borrowData.get("returnDays").asInt();
        String response = libraryManager.borrowBook(newIsbn, daysToReturn, borrower);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }
    public Result reserveBook() {
        JsonNode borrowData = request().body().asJson();
        Integer newIsbn = borrowData.get("isbn").asInt();
        Integer borrower = borrowData.get("borrower").asInt();
        int daysToReturn = borrowData.get("returnDays").asInt();
        String response = libraryManager.reserveBook(newIsbn, daysToReturn, borrower);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }
    /**
     * Issue a DVD
     *
     * @return
     */
    public Result borrowDvd() {
        JsonNode borrowData = request().body().asJson();
        Integer newIsbn = borrowData.get("isbn").asInt();
        Integer borrower = borrowData.get("borrower").asInt();
        int daysToReturn = borrowData.get("returnDays").asInt();
        String response = libraryManager.borrowDVD(newIsbn, daysToReturn, borrower);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Return a Book
     *
     * @return
     */
    public Result returnBook() {
        JsonNode returnData = request().body().asJson();
        Integer newIsbn = returnData.get("isbn").asInt();
        String response = libraryManager.returnBook(newIsbn);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Return a Dvd
     *
     * @return
     */
    public Result returnDvd() {
        JsonNode returnData = request().body().asJson();
        Integer newIsbn = returnData.get("isbn").asInt();
        String response = libraryManager.returnDvd(newIsbn);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }

    /**
     * Generate Report about Overdue Items
     *
     * @return
     */
    public Result getReport() {
        ArrayList<OverDueItem> overdueList = libraryManager.generateReport();
        Gson gson = new Gson();
        String jsonArr = gson.toJson(overdueList);

        return ok(jsonArr);

    }
    public Result getReserveDetails() {
        ArrayList<BookReserve> overdueList = libraryManager.getReserveDetails();
        Gson gson = new Gson();
        String jsonArr = gson.toJson(overdueList);

        return ok(jsonArr);

    }
    /**
     * Find Book By Title
     *
     * @return
     */
    public Result findBook() {
        JsonNode bookTitle = request().body().asJson();
        String title = bookTitle.get("title").toString().replace("\"", "");
        Book book = libraryManager.findBook(title);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(book);
        return ok(jsonArr);
    }

    /**
     * Find Dvd by Title
     *
     * @return
     */
    public Result findDvd() {
        JsonNode dvdData = request().body().asJson();
        String title = dvdData.get("title").toString().replace("\"", "");
        Dvd dvd = libraryManager.findDvd(title);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(dvd);
        return ok(jsonArr);
    }

    /**
     * Add a Member to Library
     *
     * @return
     */
    public Result addMember() {
        MemberModel mem = new MemberModel();
        JsonNode newMember = request().body().asJson();
        mem.setId(newMember.get("id").asInt());
        mem.setName(newMember.get("name").toString().replace("\"", ""));
        mem.setEmail(newMember.get("email").toString().replace("\"", ""));
        mem.setMobileNumber(newMember.get("phone").asInt());
        libraryManager.addNewMember(mem);
        Gson gson = new Gson();
        String jsonArr = gson.toJson("Member Added");
        return ok(jsonArr);
    }

    /**
     *
     * @return
     */
    public Result deleteMember() {
        JsonNode id = request().body().asJson();
        Integer newId = id.get("id").asInt();
        String response = libraryManager.deleteMem(newId);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }
    public Result deleteResEntry() {
        JsonNode id = request().body().asJson();
        Integer newId = id.get("id").asInt();
        String response = libraryManager.deleteResEntry(newId);
        Gson gson = new Gson();
        String jsonArr = gson.toJson(response);
        return ok(jsonArr);
    }
    /**
     * View Existing members
     *
     * @return
     */
    public Result getMembers() {
        ArrayList<Member> memberMap = libraryManager.viewMembers();
        Gson gson = new Gson();
        String jsonArr = gson.toJson(memberMap);

        return ok(jsonArr);


    }
}
