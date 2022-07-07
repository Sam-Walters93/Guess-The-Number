/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stwal
 */

@Repository
public class databaseDao implements gameDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public databaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public game addGame(game game) {
       final String sql = "INSERT INTO game(answer, status) VALUES(?, ?);";
       GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
       
       jdbcTemplate.update((Connection conn) -> {

          PreparedStatement statement = conn.prepareStatement(
            sql, 
            Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isStatus());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());
        
        return game;
    }

    @Override
    public List<game> getAll() {
       final String sql = "SELECT gameId, answer, status FROM game;";
       return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public game getById(int id) {
        final String sql = "SELECT * FROM game WHERE gameId = ?;";
        System.out.println("Dao game id");
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public void updateStatus(game game) {
       final String sql = "UPDATE game SET status = ? WHERE gameId = ?";
        jdbcTemplate.update(sql, game.isStatus(), game.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
       final String sql = "DELETE FROM round WHERE gameId = ?;";
       final String sqlGame = "DELETE FROM game WHERE gameId = ?";
        jdbcTemplate.update(sql, id);
        return jdbcTemplate.update(sqlGame, id) == 1;
    }
    
   private static final class GameMapper implements RowMapper<game> {
        
        @Override
        public game mapRow(ResultSet rs, int index) throws SQLException {
            game gm = new game();
            gm.setId(rs.getInt("gameId"));
            System.out.println("Mapper setId");
            gm.setStatus(rs.getBoolean("status"));
            System.out.println("Mapper status");
            gm.setAnswer(rs.getString("answer"));
            System.out.println("Mapper answer ");
            return gm;
        }
    }
    
}
