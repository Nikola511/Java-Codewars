package kyu_4;

import java.util.*;
import java.util.stream.*;
public class BattleShips {
    public static Map<String, Double> damagedOrSunk(int[][] board, int[][] attacks) {
        int oneCount = 0, twoCount = 0, threeCount = 0;
        for(int i = 0; i < board.length; ++i) {
            oneCount += IntStream.of(board[i]).filter(x -> x == 1).count();
            twoCount += IntStream.of(board[i]).filter(x -> x == 2).count();
            threeCount += IntStream.of(board[i]).filter(x -> x == 3).count();
        }
        Ship ship1 = oneCount > 0 ? new Ship(oneCount) : null;
        Ship ship2 = twoCount > 0 ? new Ship(twoCount) : null;
        Ship ship3 = threeCount > 0 ? new Ship(threeCount) : null;

        Map<String, Double> map = new HashMap<String, Double>();
        map.put("damaged", .0);
        map.put("notTouched", .0);
        map.put("points", .0);
        map.put("sunk", .0);

        for(int[] arr : attacks) {
            int x = arr[0] - 1;
            int y = board.length - arr[1];
            switch(board[y][x]) {
                case 1: ship1.hitting(); break;
                case 2: ship2.hitting(); break;
                case 3: ship3.hitting();
            }
            board[y][x] = 0;
        }

        if(ship1 != null) {
            String state = ship1.getState();
            map.put(state, map.get(state) + 1);

            if(state.equals("sunk"))
                map.put("points", map.get("points") + 1);
            else if(state.equals("damaged"))
                map.put("points", map.get("points") + 0.5);
            else
                map.put("points", map.get("points") - 1);
        }

        if(ship2 != null) {
            String state = ship2.getState();
            map.put(state, map.get(state) + 1);

            if(state.equals("sunk"))
                map.put("points", map.get("points") + 1);
            else if(state.equals("damaged"))
                map.put("points", map.get("points") + 0.5);
            else
                map.put("points", map.get("points") - 1);
        }

        if(ship3 != null) {
            String state = ship3.getState();
            map.put(state, map.get(state) + 1);

            if(state.equals("sunk"))
                map.put("points", map.get("points") + 1);
            else if(state.equals("damaged"))
                map.put("points", map.get("points") + 0.5);
            else
                map.put("points", map.get("points") - 1);
        }

        return map;
    }
}

class Ship {
    private int length;
    private String state;
    public Ship(int length) {
        this.length = length;
        this.state = "notTouched";
    }
    public void hitting() {
        if(--length == 0) state = "sunk";
        else state = "damaged";
    }
    public String getState() {
        return state;
    }
}

/*
Battle ships: Sunk damaged or not touched?
Task:
Your task in the kata is to determine how many boats are sunk damaged and untouched from a set amount of attacks. You will need to create a function that takes two arguments, the playing board and the attacks.

Example Game
Boats are places either horizontally, vertically or diagonally on the board. 0 represents a space not occupied by a boat. Digits 1-3 represent boats which vary in length 1-4 spaces long. There will always be at least 1 boat up to a maximum of 3 in any one game. Boat sizes and board dimentions will vary from game to game.

Attacks

Attacks are calculated from the bottom left, first the X coordinate then the Y. There will be at least one attack per game.
{ {2, 1}, {1, 3}, {4, 2} };
First attack      [2, 1] = 3
Second attack [1, 3] = 0
Third attack     [4, 2] = 1
Function Initialization

int[][] board   = new int[][] {new int[] {0,0,1,0},
                               new int[] {0,0,1,0},
                               new int[] {0,0,1,0}};
int[][] attacks = new int[][] {new int[] {3,1},new int[] {3,2},new int[] {3,3}};
BattleShips.damagedOrSunk(board, attacks);
Scoring

1 point for every whole boat sank.
0.5 points for each boat hit at least once (not including boats that are sunk).
-1 point for each whole boat that was not hit at least once.
Sunk or Damaged

sunk = all boats that are sunk
damaged = all boats that have been hit at least once but not sunk
notTouched/not_touched = all boats that have not been hit at least once
Output

You should return a hash with the following data

Example Game Output

In our above example..

First attack: boat 3 was damaged, which increases the points by 0.5
Second attack: miss nothing happens
Third attack: boat 1 was damaged, which increases the points by 0.5
boat 2 was untouched so points -1 and notTouched +1 in Javascript/Java/C# and not_touched +1 in Python/Ruby.
No whole boats sank
*/