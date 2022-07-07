/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dto;

import java.util.Objects;

/**
 *
 * @author stwal
 */
public class game {
    private int gameId;
    private String answer;
    private boolean status;

    public int getId() {
        return gameId;
    }

    public void setId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.gameId;
        hash = 31 * hash + Objects.hashCode(this.answer);
        hash = 31 * hash + (this.status ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final game other = (game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.answer, other.answer);
    }       
}