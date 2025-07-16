package org.thevault.videoclubAPI.model;

import jakarta.persistence.*;


@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFilm")
    private int idFilm;
    private String name;
    private String genre;
    private String subgenre;
    private String director;

    @Column(name = "premierYear")
    private int premierYear;

    @Column(name = "existingUnits")
    private int existingUnits;

    @Column(name = "productionCompany")
    private String productionCompany;

    private String format;
    @Column(name = "imageurl")
    private String imageUrl;

    public Film() {
    }

    public Film(int idFilm, String name, String genre, String subgenre, String director, int premierYear, int existingUnits, String productionCompany, String format, String imageUrl) {
        this.idFilm = idFilm;
        this.name = name;
        this.genre = genre;
        this.subgenre = subgenre;
        this.director = director;
        this.premierYear = premierYear;
        this.existingUnits = existingUnits;
        this.productionCompany = productionCompany;
        this.format = format;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return name + '\'' + genre + ' ' + subgenre + '\'' +
                director + '\'' + premierYear +
                " "  + productionCompany + ' ' + format;
    }
}
