package org.thevault.videoclubAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thevault.videoclubAPI.model.Film;
import org.thevault.videoclubAPI.service.FilmService;

import java.util.List;

@RestController
@RequestMapping(value = "/thevault/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> obtainAll(){
        return filmService.obtainAllFilms();
    }

}
