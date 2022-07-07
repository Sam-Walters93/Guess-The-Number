/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.databaseDao;
import com.sg.guessthenumber.dao.roundDbDao;
import com.sg.guessthenumber.dto.game;
import com.sg.guessthenumber.dto.round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author stwal
 */
@Service
public class gameServiceLayerImpl implements gameServiceLayer {
    databaseDao Dao;
    roundDbDao rdbDao;
    
    @Autowired
    public gameServiceLayerImpl(databaseDao Dao, roundDbDao rdbDao){
        this.Dao = Dao;
        this.rdbDao = rdbDao; 
    }

    @Override
    public int begin() {
        game game = new game();
        String answer = newAnswer();
        game.setAnswer(answer);
        game = Dao.addGame(game);
        return game.getId();   
    }

    @Override
    public List<game> getAllGame() {
        List<game> games = Dao.getAll();
        
//        Iterator<game> itr = games.iterator();
//        
//        while (itr.hasNext()) {
//            if (games[itr].isStatus == true) {
//                
//            }
//        }
        for (int i = 0; i < games.size(); i++) {
            if(games.get(i).isStatus() ==  false) {
                games.get(i).setAnswer("Nuh uh uh, no peeking");
            }
        }
        return games;
    }

    @Override
    public game findById(int id) {
        System.out.println("Serv game id");
        game game = Dao.getById(id);
        
        if (game.isStatus() == false) {
            game.setAnswer("Nuh uh uh, no peeking");
        }
        
        return game;
    }

    @Override
    public round guess(round round) {
        int gameId = round.getGameId();
        game game = Dao.getById(gameId);
        
        String answer = game.getAnswer();
        String guess = round.getGuess();
        
        String result = guessResult(answer, guess);
        
        round.setResult(result);
        
        if (answer.equals(guess)) {
            game.setStatus(false);
            Dao.updateStatus(game);
        }
        
        round = rdbDao.newRound(round);
        
        return round;  
    }

    @Override
    public List<round> getAllRoundsForGame(int id) {
        List<round> rounds = rdbDao.getAllRounds(id);
        return rounds;
    }

    @Override
    public String guessResult(String answer, String guess) {
        int p = 0;
        int e = 0;
        String result = "";
        
//        String[] answerSpl = answer.split("");
//        String[] guessSpl = guess.split("");
//        
//        ArrayList<String> answerList = new ArrayList<String>(Arrays.asList(answerSpl));
//        ArrayList<String> guessList = new ArrayList<String>(Arrays.asList(guessSpl));

        char[] answerChar = answer.toCharArray();
        char[] guessChar = guess.toCharArray();
        
       for (int i = 0; i < answerChar.length; i++) {
           if (guessChar[i] == answerChar[i]) {
               e++;
               continue;
           }
           
           for (int j = 0; j < answerChar.length; j++) {
               if (guessChar[j] == answerChar[i]) {
                   p++;
                   break;
               }
           }
       }
       
        result = "e: " + e + " p: " + p;
        return result;
     }
    
    private String newAnswer(){
       Random random = new Random(); 
       
       String answer = "";
       
       int int1 = random.nextInt(10);
       
       int int2 = random.nextInt(10);
       
       while (int2 == int1) {
         int2 = random.nextInt(10);
       }
       
       int int3 = random.nextInt(10);
       
       while (int3 == int1 || int3 == int2) {
         int3 = random.nextInt(10);
       }
       
       int int4 = random.nextInt(10);
       
        while (int4 == int1 || int4 == int2 || int4 == int3) {
         int4 = random.nextInt(10);
       }
       
       answer += String.valueOf(int1);
       answer += String.valueOf(int2);
       answer += String.valueOf(int3);
       answer += String.valueOf(int4);
       
       return answer;
    }
    
}
