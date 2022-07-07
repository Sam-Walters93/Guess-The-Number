/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stwal
 */

@Repository
public class roundDbDao implements roundDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public roundDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    @Transactional
    public round newRound(round round) {
        final String sql = "INSERT INTO round(gameId, guess, result) Values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
                    statement.setInt(1, round.getGameId());
                    statement.setString(2, round.getGuess());
                    statement.setString(3, round.getResult());
                    return statement;                    
        }, keyHolder);
        round.setRoundId(keyHolder.getKey().intValue());
        return round;
//        int roundId = round.getRoundId();
//        final String sql2 = "SELECT * FROM round WHERE roundId = ?";
////        System.out.println("HERE " + jdbcTemplate.queryForObject(sql2, new RoundMapper(), roundId));
//        return jdbcTemplate.queryForObject(sql2, new RoundMapper(), roundId);
    }

    @Override
    public List<round> getAllRounds(int id) {
        final String sql = "SELECT * FROM round WHERE gameId = ? ORDER BY time;";
        return jdbcTemplate.query(sql, new RoundMapper(), id); 
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM round WHERE roundId = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public static final class RoundMapper implements RowMapper<round> {
        
        @Override
        public round mapRow(ResultSet rs, int index) throws SQLException {
            round round = new round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            
            Timestamp timestamp = rs.getTimestamp("time");
            System.out.println("HERE " + rs.getTimestamp("time"));
            round.setTime(timestamp.toLocalDateTime());
            
            round.setResult(rs.getString("result"));
            return round;
        }
    }
       
}
