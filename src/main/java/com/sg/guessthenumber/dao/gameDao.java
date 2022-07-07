/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;
import com.sg.guessthenumber.dto.game;
import java.util.List;

/**
 *
 * @author stwal
 */
public interface gameDao {
   game addGame(game game);
   
   List<game> getAll();
   
   game getById(int id);
   
   public void updateStatus(game game);
   
   public boolean deleteById(int id);
}
