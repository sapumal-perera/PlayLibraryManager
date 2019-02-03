package models;


import io.ebean.Finder;

public abstract class LibraryItem {

    private String title;

    private String sector;

    private String publicationDate;

    private Integer borrowedDate;

    private Integer borrowedHour;

    private String borrowedTime;

    private Integer borrowedYear;

    private Integer borrowedCurrentDaysCount;

    private Integer returnDate;

    private Integer currentReader;

    private boolean isAvailable = true;


    public static Finder<Integer, LibraryItem> find = new Finder<Integer, LibraryItem>(LibraryItem.class);

    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    /**
     * @param sector
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getReturnedDate() {
        return returnDate;
    }

    /**
     * @param returnedDateTme
     */
    public void setReturnedDate(Integer returnedDateTme) {
        this.returnDate = returnedDateTme;
    }


    public Integer getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Integer borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getBorrowedTime() {
        return borrowedTime;
    }

    public void setBorrowedTime(String borrowedTime) {
        this.borrowedTime = borrowedTime;
    }

    public Integer getBorrowedYear() {
        return borrowedYear;
    }

    public void setBorrowedYear(Integer borrowedYear) {
        this.borrowedYear = borrowedYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getBorrowedCurrentDaysCount() {
        return borrowedCurrentDaysCount;
    }

    public void setBorrowedCurrentDaysCount(Integer borrowedCurrentDaysCount) {
        this.borrowedCurrentDaysCount = borrowedCurrentDaysCount;
    }

    public Integer getBorrowedHour() {
        return borrowedHour;
    }

    public void setBorrowedHour(Integer borrowedHour) {
        this.borrowedHour = borrowedHour;
    }

    public Integer getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Integer currentReader) {
        this.currentReader = currentReader;
    }
}
