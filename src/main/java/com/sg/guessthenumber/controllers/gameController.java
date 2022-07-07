/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.dto.game;
import com.sg.guessthenumber.dto.round;
import com.sg.guessthenumber.service.gameServiceLayerImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author stwal
 */

@RestController
@RequestMapping("/api/guessgame")
public class gameController {
    
    @Autowired
    private final gameServiceLayerImpl service;
    
    public gameController(gameServiceLayerImpl service){
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin(){
        return service.begin();
    }

    @GetMapping("/games")
    public List<game> allGames(){
        return service.getAllGame();
    }
    
    @GetMapping("/game/{gameId}")
    public game getGameById(@PathVariable int gameId){
        System.out.println("Con Game by id");
        return service.findById(gameId);
    }
    
    @PostMapping("/guess")
    public round guess(@RequestBody round round){
        return service.guess(round);
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<round> allRounds(@PathVariable int gameId){
        return service.getAllRoundsForGame(gameId);
    }
}
