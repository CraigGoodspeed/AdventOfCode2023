package nz.co.goodspeed.daytwo;


import java.util.Arrays;
import java.util.List;

public class Game {
    List<GameItem> gameItems;
    Integer gameIndex;

    public Game(String toParse) {
        String[] data = toParse.split(":");
        gameIndex = Integer.valueOf(data[0].replace("Game ",""));
        String[] gameItemsToParse = data[1].split(";");
        gameItems = Arrays.stream(gameItemsToParse).map(
                GameItem::new
        ).toList();
    }

    public List<GameItem> getGameItems() {
        return gameItems;
    }

    public Integer getGameIndex() {
        return gameIndex;
    }

    public Integer totalBlue() {
        return gameItems.stream().mapToInt(GameItem::countBlue).sum();
    }

    public Integer totalGreen() {
        return gameItems.stream().mapToInt(GameItem::countGreen).sum();
    }

    public Integer totalRed() {
        return gameItems.stream().mapToInt(GameItem::countRed).sum();
    }

    public Integer maxRed() {
        return
                gameItems.stream()
                        .mapToInt(GameItem::getRed)
                        .max().orElse(0);
    }

    public Integer maxGreen() {
        return gameItems.stream()
                .mapToInt(GameItem::getGreen)
                .max().orElse(0);
    }

    public Integer maxBlue() {
        return gameItems.stream()
                .mapToInt(GameItem::getBlue)
                .max().orElse(0);
    }
}
