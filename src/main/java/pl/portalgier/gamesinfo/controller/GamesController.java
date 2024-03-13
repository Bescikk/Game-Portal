package pl.portalgier.gamesinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.portalgier.gamesinfo.model.Game;
import pl.portalgier.gamesinfo.repository.GamesRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    GamesRepository gamesRepository;

    @GetMapping("")
    public List<Game> getAll() {
        return gamesRepository.getAll();
    }

    @GetMapping("/{id}")
    public Game getById(@PathVariable ("id") int id) {
        return gamesRepository.getById(id);
    }

    @PostMapping("/add-game")
    public int add(@RequestBody List<Game> games) {
        return gamesRepository.addGame(games);
    }

    @DeleteMapping("/delete-game/{id}")
    public int delete(@PathVariable ("id") int id) {
        return gamesRepository.deleteGame(id);
    }

    @PostMapping("/update-game/{id}")
    public int update(@PathVariable ("id") int id, @RequestBody Game updatedGame) {
        Game game = gamesRepository.getById(id);

        if (game != null) {
            game.setTitle(updatedGame.getTitle());
            game.setDescription(updatedGame.getDescription());
            game.setAuthor(updatedGame.getAuthor());

            gamesRepository.update(game);

            return 1;
        }
        else {
            return -1;
        }
    }

    @PatchMapping("/update-game/{id}")
    public int partiallyUpdate(@PathVariable ("id") int id, @RequestBody Game updatedGame) {
        Game game = gamesRepository.getById(id);

        if (game != null) {
            if (updatedGame.getTitle() != null) {
                game.setTitle(updatedGame.getTitle());
            }
            if (updatedGame.getDescription() != null) {
                game.setDescription(updatedGame.getDescription());
            }
            if (updatedGame.getAuthor() != null) {
                game.setAuthor(updatedGame.getAuthor());
            }

            gamesRepository.update(game);

            return 1;
        }
        else {
            return -1;
        }
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("id") int id){
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get("src/main/resources/images/" + image.getOriginalFilename());
            Files.write(path, bytes);

            gamesRepository.saveImagePath(id, path.toString());
            return "Obraz został pomyślnie przesłany!";
        } catch (Exception e) {
            return "Wystąpił błąd podczas przesyłania obrazu: " + e.getMessage();
        }
    }
}
