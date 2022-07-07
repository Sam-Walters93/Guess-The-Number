/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.round;
import java.util.List;

/**
 *
 * @author stwal
 */
public interface roundDao {
    public round newRound(round round);
    
    public List<round> getAllRounds(int id);
    
    public boolean deleteById(int id);
    
}
