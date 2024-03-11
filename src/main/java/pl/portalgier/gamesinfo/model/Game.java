package pl.portalgier.gamesinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private int id;
    private String title;
    private String description;
    private String author;
    private String image;
}
