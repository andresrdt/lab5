/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.flitro.filter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristian
 */
public class InMemoryCinemaPersistence implements CinemaPersitence {

    private final Map<String, Cinema> cinemas = new HashMap<>();
    
    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("cinemaX", functions);
        cinemas.put("cinemaX", c);
    }

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        try {
            for (CinemaFunction cf : getCinema(cinema).getFunctions()) {
                if (cf.getMovie().getName().equals(movieName) || cf.getDate().equals(date)) {
                    cf.buyTicket(row, col);
                }
            }
        } catch (CinemaPersistenceException ex) {
            ex.getMessage();
        }

    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> functions = new ArrayList<CinemaFunction>();
        try {
            for (CinemaFunction cf : getCinema(cinema).getFunctions()) {
                if (cf.getDate().equals(date)) {
                    functions.add(cf);
                }
            }
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(InMemoryCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return functions;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())) {
            throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
        } else {
            cinemas.put(c.getName(), c);
        }
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        if (cinemas.containsKey(name)) {
            return cinemas.get(name);
        } else {
            throw new CinemaPersistenceException("El cine no existe " + name);
        }
    }

}
