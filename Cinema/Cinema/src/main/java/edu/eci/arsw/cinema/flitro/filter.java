/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.flitro;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.Movie;
import java.util.List;
/**
 *
 * @author Andres
 */
public interface filter {
        public List<Movie> filtro (Cinema cinema, String date ,String parametro);
}
