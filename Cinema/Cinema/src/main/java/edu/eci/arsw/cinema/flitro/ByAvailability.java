/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.flitro;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class ByAvailability implements filter{

    @Override
    public List<Movie> filtro(Cinema cinema, String date, String parametro) {
        List<Movie> hermanos = new ArrayList();
        List<CinemaFunction> functions = cinema.getFunctions();
        int dispo = Integer.parseInt(parametro);
        for (CinemaFunction i : functions) {
            if (i.Avility() > dispo) {
                hermanos.add(i.getMovie());
            }
        }
        return hermanos;

    }

    
}
