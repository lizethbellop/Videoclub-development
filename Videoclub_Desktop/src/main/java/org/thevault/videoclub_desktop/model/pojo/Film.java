package org.thevault.videoclub_desktop.model.pojo;

public class Film {
    private int idFilm;
    private String name;
    private String genre;
    private String subgenre;
    private String director;
    private int premierYear;
    private int existingUnits;
    private String productionCompany;
    private String imageUrl;

    public Film() {
    }

    public Film(int idFilm, String name, String genre, String subgenre, String director, int premierYear, int existingUnits, String productionCompany, String imageUrl) {
        this.idFilm = idFilm;
        this.name = name;
        this.genre = genre;
        this.subgenre = subgenre;
        this.director = director;
        this.premierYear = premierYear;
        this.existingUnits = existingUnits;
        this.productionCompany = productionCompany;
        this.imageUrl = imageUrl;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getPremierYear() {
        return premierYear;
    }

    public void setPremierYear(int premierYear) {
        this.premierYear = premierYear;
    }

    public int getExistingUnits() {
        return existingUnits;
    }

    public void setExistingUnits(int existingUnits) {
        this.existingUnits = existingUnits;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
