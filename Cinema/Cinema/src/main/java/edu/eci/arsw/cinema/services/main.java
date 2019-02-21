/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class main {

    public static void main(String[] args) throws CinemaException, CinemaPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Context.xml");
        CinemaServices cn = ac.getBean(CinemaServices.class);
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
        String functionDate = "2018-14-19 17:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogota", functions);
        cn.addNewCinema(c);
        cn.getCps().saveCinema(c);
        System.out.println(cn.getCinemaByName("Movies Bogota"));
        cn.buyTicket(2, 2,"Movies Bogota", functionDate,"The Night");
        try{
            cn.buyTicket(2, 2,"Movies Bogota", functionDate,"The Night");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(cn.getFunctionsbyCinemaAndDate("Movies Bogota", functionDate));
        /*
        //el cine no existe
        try{
             System.out.println(cn.getFunctionsbyCinemaAndDate("Movies Venezuela", functionDate));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }*/
    }
}
