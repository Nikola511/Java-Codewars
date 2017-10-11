package kyu_4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.util.stream.Collectors;

public class BattleShipsTest {
    @Test
    public void example1() {
        int[][] board   = new int[][] {new int[] {0,0,1,0},
                new int[] {0,0,1,0},
                new int[] {0,0,1,0}};
        int[][] attacks = new int[][] {new int[] {3,1},new int[] {3,2},new int[] {3,3}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 1.0);
        expected.put("damaged", .0);
        expected.put("notTouched", .0);
        expected.put("points", 1.0);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void example2() {
        int[][] board   = new int[][] {new int[] {3, 0, 1},
                new int[] {3, 0, 1},
                new int[] {0, 2, 1},
                new int[] {0, 2, 0}};
        int[][] attacks = new int[][] {new int[] {2,1},new int[] {2,2},new int[] {3,2},new int[] {3,3}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 1.0);
        expected.put("damaged", 1.0);
        expected.put("notTouched", 1.0);
        expected.put("points", 0.5);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void MainGame1() {
        int[][] board   = new int[][] {new int[] {0,0,1,2,2,0},
                new int[] {0,3,0,1,0,0},
                new int[] {0,3,0,0,0,0},
                new int[] {0,3,0,0,0,0}};
        int[][] attacks = new int[][] {new int[] {3,4}, new int[] {4,3}, new int[] {4,4}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 1.0);
        expected.put("damaged", 1.0);
        expected.put("notTouched", 1.0);
        expected.put("points", 0.5);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void MainGame2() {
        int[][] board   = new int[][] {new int[] {0,0,1},
                new int[] {0,0,0},
                new int[] {0,2,0},
                new int[] {0,2,0}};
        int[][] attacks = new int[][] {new int[] {3,4}, new int[] {2,1}, new int[] {2,2}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 2.);
        expected.put("damaged", 0.);
        expected.put("notTouched", 0.);
        expected.put("points", 2.);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void MainGame3() {
        int[][] board   = new int[][] {new int[] {0,0,1},
                new int[] {0,0,1},
                new int[] {0,2,0},
                new int[] {0,2,0}};
        int[][] attacks = new int[][] {new int[] {3,4}, new int[] {2,1}, new int[] {2,2}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 1.);
        expected.put("damaged", 1.);
        expected.put("notTouched", 0.);
        expected.put("points", 1.5);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    @Test
    public void MainGame4() {
        int[][] board   = new int[][] {new int[] {1,1,1},
                new int[] {0,0,0},
                new int[] {0,2,0},
                new int[] {0,2,0}};
        int[][] attacks = new int[][] {new int[] {3,4}, new int[] {2,4}, new int[] {1,4}};

        Map<String,Double> expected = new HashMap<String,Double>();
        expected.put("sunk", 1.);
        expected.put("damaged", 0.);
        expected.put("notTouched", 1.);
        expected.put("points", 0.);

        assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
    }

    /*  INTERNAL SOLUTION   */

    private Map<String,Double> res;
    private static final Map<String,Double> STATIC_RES = Arrays.asList("sunk", "damaged", "notTouched", "points")
            .stream().collect(Collectors.toMap( s->s, s-> 0.));


    private Map<String,Double> soluce(final int[][] board, final int[][] attacks) {

        Set<List<Integer>> setAttacks = Arrays.stream(attacks)
                .map( pos -> Arrays.stream(pos).boxed().collect(Collectors.toList()) )
                .collect(Collectors.toSet());

        Map<Integer,Integer> lenShips = new HashMap<Integer,Integer>(),
                remains  = new HashMap<Integer,Integer>();
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] != 0) {
                    List<Integer> pos = Arrays.asList(j+1, board.length-i);
                    int lenShip = board[i][j];

                    lenShips.put(lenShip, lenShips.getOrDefault(lenShip, 0)+1);
                    if (!setAttacks.contains(pos))
                        remains.put(lenShip, remains.getOrDefault(lenShip, 0)+1);
                }
            }
        }
        res = new HashMap<String,Double>(STATIC_RES);
        for (int ship: lenShips.keySet())
            updateRes(lenShips.get(ship), remains.getOrDefault(ship, 0));

        return res;
    }

    private void updateRes(final int lenShip, final int lenRem) {
        String key = lenShip == lenRem ? "notTouched" : lenRem == 0 ? "sunk" : "damaged";    // key
        double pts = lenShip == lenRem ? -1.0         : lenRem == 0 ?  1.0   :  0.5;         // value (points)

        res.put(key, res.get(key) + 1.0);
        res.put("points", res.get("points") + pts);
    }

    /*   RANDOM TESTS   */

    private Random rand = new Random();

    @Test
    public void RandomTests() {
        // Meaning of x and Y in the code of the random tests is the usual one, not the one describe in the description.
        for (int n = 0 ; n < 100 ; n++) {
            int x = 5 + rand.nextInt(4),
                    y = 5 + rand.nextInt(4);
            int[][] board   = getRandomBored(x,y);
            int[][] attacks = getRandomAttacks(x,y);

            Map<String,Double> expected = soluce(board, attacks);
            assertEquals(expected, BattleShips.damagedOrSunk(board, attacks));
        }
    }

    private int[][] getRandomAttacks(int x, int y) {
        int lenAtt = 2 + rand.nextInt(15);

        Set<int[]> posAtt = new HashSet<int[]>();
        do { int[] pos = new int[] {1+rand.nextInt(y), 1+rand.nextInt(x)};    // invert x and y there, to match the description
            if (!posAtt.contains(pos)) posAtt.add(pos);
        } while (posAtt.size() < lenAtt);

        Iterator<int[]> it = posAtt.iterator();
        int[][] attacks = new int[lenAtt][2];
        for (int n = 0 ; n < lenAtt ; n++) attacks[n] = it.next();

        return attacks;
    }

    private int[][] dirs = new int[][] {new int[] {0,1}, new int[] {1,0}, new int[] {1,1}, new int[] {-1,1}};

    private int[][] getRandomBored(int x, int y) {
        int[][] board = new int[x][y];
        int nBoats = 1+rand.nextInt(3);
        boolean hasDiag = false;

        for (int nb = 1 ; nb <= nBoats ; nb++) {
            while (true) {
                int[] dir = dirs[ rand.nextInt(hasDiag ? 2:4) ];

                int sizeBoat = 1 + rand.nextInt(5);
                if (sizeBoat>4) sizeBoat -= 2;            // minimize the number of occurence of boats of size 1 or 2

                int initY = rand.nextInt( y - (sizeBoat-1) * (dir[1]!=0 ? 1:0) );
                int initX = rand.nextInt( x - (sizeBoat-1) * (dir[0]!=0 ? 1:0) ) + (sizeBoat-1) * (dir[0]<0 ? 1:0);

                boolean validBoat = true;
                for (int z = 0 ; z < sizeBoat ; z++) if (board[initX + dir[0]*z][initY + dir[1]*z] != 0) validBoat = false;      // check the validity of the positions
                if (validBoat) {
                    for (int z = 0 ; z < sizeBoat ; z++) board[initX + dir[0]*z][initY + dir[1]*z] = nb;                         // Assign the positions
                    hasDiag = hasDiag  || (dir[0] != 0 && dir[1] != 0);                                                          // Allow only 1 boat in diagonal, to avoid crossing ships
                    break;
                }
            }
        }
        return board;
    }
}