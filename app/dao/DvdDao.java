package dao;

import models.dbModels.Actor;
import models.dbModels.DvdModel;
import models.dbModels.Language;
import models.dbModels.Subtitle;
//import models.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class DvdDao {

    /**
     * Get DVD by ID
     *
     * @param id
     * @return
     */
    public DvdModel getDvd(Integer id) {
        DvdModel dv = DvdModel.find.byId(id);
        if (dv == null) {
            return null;
        }
        return dv;

    }

    /**
     * Check dvd Exists
     *
     * @param id
     * @return
     */
    public boolean isDvdExists(Integer id) {
        DvdModel dv = DvdModel.find.byId(id);
        if (dv == null) {
            return false;
        }
        return true;

    }

    /**
     * Find DVD by Title
     *
     * @param title
     * @return
     */
    public DvdModel findDvd(String title) {

        List<DvdModel> dvdStore = DvdModel.find.all();
        for (int i = 0; i < dvdStore.size(); i++) {
            if (dvdStore.get(i).getTitle().equalsIgnoreCase(title)) {
                return dvdStore.get(i);
            }
        }
        return null;
    }

    /**
     * Add new DVD
     *
     * @param dvdModel
     * @return
     */
    public boolean addDvd(DvdModel dvdModel) {
        DvdModel lt = dvdModel;
        lt.save();
        return true;
    }

    /**
     * Get All DVD List
     *
     * @return
     */
    public List<DvdModel> getDvdStore() {
        List<DvdModel> dvdStore = DvdModel.find.all();
        return dvdStore;
    }

    /**
     * Get Total DVD Count
     *
     * @return
     */
    public int getDvdCount() {
        List<DvdModel> dvdStore = DvdModel.find.all();
        return dvdStore.size();
    }

    /**
     * Update DVD
     *
     * @param id
     * @param dvdModel
     * @return
     */
    public boolean updateDvd(Integer id, DvdModel dvdModel) {

        DvdModel dv = DvdModel.find.byId(id);
        if (dv == null) {
            return false;
        }
        //    dv.delete();
        dvdModel.save();

        return true;
    }

    /**
     * Delete DVD
     *
     * @param id
     * @return
     */
    public boolean deleteDvd(Integer id) {
        DvdModel dv = DvdModel.find.byId(id);
        if (dv == null) {
            return false;
        }
        dv.delete();

        return true;
    }

    /**
     * Get Actors List
     *
     * @param id
     * @return
     */
    public ArrayList<Actor> getActors(Integer id) {
        ArrayList<Actor> actor = new ArrayList<Actor>();
        List<Actor> actors = Actor.find.all();
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getIsbn().equals(id)) {
                actor.add(actors.get(i));
            }
        }
        return actor;
    }

    /**
     * Get available languages list
     *
     * @param id
     * @return
     */
    public ArrayList<Language> getLanguages(Integer id) {
        ArrayList<Language> lang = new ArrayList<Language>();
        List<Language> languages = Language.find.all();
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getIsbn().equals(id)) {
                lang.add(languages.get(i));
            }
        }
        return lang;
    }

    /**
     * Get available subtitle list
     *
     * @param id
     * @return
     */
    public ArrayList<Subtitle> getSubtitles(Integer id) {
        ArrayList<Subtitle> subs = new ArrayList<Subtitle>();
        List<Subtitle> subtitles = Subtitle.find.all();
        for (int i = 0; i < subtitles.size(); i++) {
            if (subtitles.get(i).getIsbn().equals(id)) {
                subs.add(subtitles.get(i));
            }
        }
        return subs;
    }
}
