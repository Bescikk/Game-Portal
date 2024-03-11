package pl.portalgier.gamesinfo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.portalgier.gamesinfo.model.Game;

import java.util.List;

@Repository
public class GamesRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Game> getAll() {
        return jdbcTemplate.query("SELECT id, title, description, author FROM games",
                BeanPropertyRowMapper.newInstance(Game.class));
    }

    public Game getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, title, description, author FROM games WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Game.class), id);
    }

    public int addGame(List<Game> games) {
        games.forEach(game ->
            jdbcTemplate.update("INSERT INTO games (title, description, author) VALUES (?, ?, ?)",
                    game.getTitle(), game.getDescription(), game.getAuthor()));
        return 1;
    }

    public int deleteGame(int id) {
        return jdbcTemplate.update("DELETE FROM games WHERE id = ?", id);
    }

    public int update(Game game) {
        return jdbcTemplate.update("UPDATE games SET title = ?, description = ?, author = ? WHERE id = ?",
                game.getTitle(), game.getDescription(), game.getAuthor(), game.getId());
    }

    public int saveImagePath(int id, String imagePath) {
        return jdbcTemplate.update("UPDATE games SET image_path = ? WHERE id = ?", imagePath, id);
    }
}
