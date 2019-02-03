package models;

public class Dvd extends LibraryItem {
    private Integer dvdId;
    private String availableSubtitles;
    private String availableLanguages;
    private String actors;
    private String producer;

    public String getAvailableSubtitles() {
        return availableSubtitles;
    }

    public void setAvailableSubtitles(String availableSubtitles) {
        this.availableSubtitles = availableSubtitles;
    }

    public String getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(String availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getDvdId() {
        return dvdId;
    }

    public void setDvdId(Integer dvdId) {
        this.dvdId = dvdId;
    }
}
