/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dto.game;
import com.sg.guessthenumber.dto.round;
import java.util.List;

/**
 *
 * @author stwal
 */
public interface gameServiceLayer {
    public int begin();
    
    public List<game> getAllGame();
    
    public game findById(int id);
    
    public round guess(round round);
    
    public List<round> getAllRoundsForGame(int id);
    
    public String guessResult(String answer, String guess);
}
