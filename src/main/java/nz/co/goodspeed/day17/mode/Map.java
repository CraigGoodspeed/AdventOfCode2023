package nz.co.goodspeed.day17.mode;

import nz.co.goodspeed.day16.Model.Coordinates;

public class Map {
    int[][] heatLostOnThisBlock;

    public Map(String allData) {
        String[] rawInput = allData.split("\n");
        heatLostOnThisBlock = new int[rawInput.length][rawInput[0].length()];
        for(int x = 0; x < rawInput.length; x++) {
            for(int y = 0; y < rawInput[x].length(); y++) {
                heatLostOnThisBlock[x][y] = Integer.parseInt(String.valueOf(rawInput[x].charAt(y)));
            }
        }
    }

    public int[][] getHeatLostOnThisBlock() {
        return heatLostOnThisBlock;
    }
}
