package org.thevault.videoclubAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thevault.videoclubAPI.model.Film;
import org.thevault.videoclubAPI.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> obtainAllFilms(){
        return filmRepository.findAll();
    }

    public List<Film> obtainFilmByGenre(String genre){
        return filmRepository.findByGenre(genre);
    }
}
