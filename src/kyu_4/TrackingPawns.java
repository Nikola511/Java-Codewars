package kyu_4;

import java.util.*;
class PawnMoveTracker {
    static String[][] movePawns(String[] moves) {
        String[][] board = new String[][] {
                {".",".",".",".",".",".",".","."},
                {"p","p","p","p","p","p","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P","P","P","P","P","P"},
                {".",".",".",".",".",".",".","."}
        };

        Map<String, Integer> columns = new HashMap<String, Integer>();
        columns.put("a", 0); columns.put("b", 1); columns.put("c", 2); columns.put("d", 3); columns.put("e", 4); columns.put("f", 5); columns.put("g", 6); columns.put("h", 7);

        Map<Integer, Integer> rows = new HashMap<Integer, Integer>();
        rows.put(1, 7); rows.put(2, 6); rows.put(3, 5); rows.put(4, 4); rows.put(5, 3); rows.put(6, 2); rows.put(7, 1); rows.put(8, 0);

        for(int i = 0; i < moves.length; ++i) {
            String move = moves[i];
            if(i % 2 == 0) {
                //white move
                if(move.matches("^[a-h][3-8]$")) {
                    //simple move
                    String column = move.substring(0, 1);
                    int row = Integer.parseInt(move.substring(1));
                    if(move.endsWith("4")) {
                        //to 4 row from 3 or 2
                        if     (board[rows.get(3)][columns.get(column)].equals("P") &&
                                board[rows.get(4)][columns.get(column)].equals(".")) {
                            board[rows.get(3)][columns.get(column)] = ".";
                            board[rows.get(4)][columns.get(column)] = "P";
                        }
                        else if(board[rows.get(2)][columns.get(column)].equals("P") &&
                                board[rows.get(3)][columns.get(column)].equals(".") &&
                                board[rows.get(4)][columns.get(column)].equals(".")) {
                            board[rows.get(2)][columns.get(column)] = ".";
                            board[rows.get(4)][columns.get(column)] = "P";
                        }
                        else return invalid(move);
                    }
                    else {
                        //to other row
                        if(board[rows.get(row-1)][columns.get(column)].equals("P") &&
                                board[rows.get(row)][columns.get(column)].equals(".")) {
                            board[rows.get(row-1)][columns.get(column)] = ".";
                            board[rows.get(row)][columns.get(column)] = "P";
                        }
                        else return invalid(move);
                    }
                }
                else if(move.matches("^[a-h](x)[a-h][3-8]$")) {
                    //beats pawn
                    int row = Integer.parseInt(move.substring(3));
                    String fromColumn = move.substring(0, 1), toColumn = move.substring(2, 3);
                    if(!possibleBeat(fromColumn, toColumn)) return invalid(move);
                    if(board[rows.get(row-1)][columns.get(fromColumn)].equals("P") && board[rows.get(row)][columns.get(toColumn)].equals("p")) {
                        board[rows.get(row-1)][columns.get(fromColumn)] = ".";
                        board[rows.get(row)][columns.get(toColumn)] = "P";
                    }
                    else return invalid(move);
                }
                else return invalid(move);
            }
            else {
                //black move
                if(move.matches("^[a-h][0-6]$")) {
                    //simple move
                    String column = move.substring(0, 1);
                    int row = Integer.parseInt(move.substring(1));
                    if(move.endsWith("5")) {
                        //to 5 row from 6 or 7
                        if     (board[rows.get(6)][columns.get(column)].equals("p") &&
                                board[rows.get(5)][columns.get(column)].equals(".")) {
                            board[rows.get(6)][columns.get(column)] = ".";
                            board[rows.get(5)][columns.get(column)] = "p";
                        }
                        else if(board[rows.get(7)][columns.get(column)].equals("p") &&
                                board[rows.get(6)][columns.get(column)].equals(".") &&
                                board[rows.get(5)][columns.get(column)].equals(".")) {
                            board[rows.get(7)][columns.get(column)] = ".";
                            board[rows.get(5)][columns.get(column)] = "p";
                        }
                        else return invalid(move);
                    }
                    else {
                        //to other row
                        if(board[rows.get(row+1)][columns.get(column)].equals("p") &&
                                board[rows.get(row)][columns.get(column)].equals(".")) {
                            board[rows.get(row+1)][columns.get(column)] = ".";
                            board[rows.get(row)][columns.get(column)] = "p";
                        }
                        else return invalid(move);
                    }
                }
                else if(move.matches("^[a-h](x)[a-h][2-6]$")) {
                    //beats pawn
                    int row = Integer.parseInt(move.substring(3));
                    String fromColumn = move.substring(0, 1), toColumn = move.substring(2, 3);
                    if(!possibleBeat(fromColumn, toColumn)) return invalid(move);
                    if(board[rows.get(row+1)][columns.get(fromColumn)].equals("p") && board[rows.get(row)][columns.get(toColumn)].equals("P")) {
                        board[rows.get(row+1)][columns.get(fromColumn)] = ".";
                        board[rows.get(row)][columns.get(toColumn)] = "p";
                    }
                    else return invalid(move);
                }
                else return invalid(move);
            }
        }

        return board;
    }

    private static String[][] invalid(String move) { return new String[][] {{ move + " is invalid" }}; }

    private static boolean possibleBeat(String fromColumn, String toColumn) {
        return (fromColumn.equals("a") &&  toColumn.equals("b")) ||
                (fromColumn.equals("b") && (toColumn.equals("a")  || toColumn.equals("c"))) ||
                (fromColumn.equals("c") && (toColumn.equals("b")  || toColumn.equals("d"))) ||
                (fromColumn.equals("d") && (toColumn.equals("c")  || toColumn.equals("e"))) ||
                (fromColumn.equals("e") && (toColumn.equals("d")  || toColumn.equals("f"))) ||
                (fromColumn.equals("f") && (toColumn.equals("e")  || toColumn.equals("g"))) ||
                (fromColumn.equals("g") && (toColumn.equals("f")  || toColumn.equals("h"))) ||
                (fromColumn.equals("h") &&  toColumn.equals("g"));
    }
}

/*
A chess board is normally played with 16 pawns and 16 other pieces, for this kata a variant will be played with only the pawns. All other pieces will not be on the board.
For information on how pawns move, refer here

Write a function that can turn a list of pawn moves into a visual representation of the resulting board.
A chess move will be represented by a string,

"c3"
This move represents a pawn moving to c3. If it was white to move, the move would represent a pawn from c2 moving to c3. If it was black to move, a pawn would move from c4 to c3, because black moves in the other direction.
The first move in the list and every other move will be for white's pieces.

The letter represents the column, while the number represents the row of the square where the piece is moving

Captures are represented differently from normal moves:

"bxc3"
represents a pawn on the column represented by 'b' (the second column) capturing a pawn on c3.

For the sake of this kata a chess board will be represented by a list like this one:

[[".",".",".",".",".",".",".","."],
 ["p","p","p","p","p","p","p","p"],
 [".",".",".",".",".",".",".","."],
 [".",".",".",".",".",".",".","."],
 [".",".",".",".",".",".",".","."],
 [".",".",".",".",".",".",".","."],
 ["P","P","P","P","P","P","P","P"],
 [".",".",".",".",".",".",".","."]]
Here is an example of the board with the squares labeled:

[["a8","b8","c8","d8","e8","f8","g8","h8"],
 ["a7","b7","c7","d7","e7","f7","g7","h7"],
 ["a6","b6","c6","d6","e6","f6","g6","h6"],
 ["a5","b5","c5","d5","e5","f5","g5","h5"],
 ["a4","b4","c4","d4","e4","f4","g4","h4"],
 ["a3","b3","c3","d3","e3","f3","g3","h3"],
 ["a2","b2","c2","d2","e2","f2","g2","h2"],
 ["a1","b1","c1","d1","e1","f1","g1","h1"]]
White pawns are represented by capital 'P' while black pawns are lowercase 'p'.

A few examples

If the list/array of moves is: ["c3"]
>>>
[[".",".",".",".",".",".",".","."],
 ["p","p","p","p","p","p","p","p"],
 [".",".",".",".",".",".",".","."],
 [".",".",".",".",".",".",".","."],
 [".",".",".",".",".",".",".","."],
 [".",".","P",".",".",".",".","."],
 ["P","P",".","P","P","P","P","P"],
 [".",".",".",".",".",".",".","."]]
add a few more moves,

If the list/array of moves is: ["e4", "e5", "f3", "c6", "f4"]
>>>
[[".",".",".",".",".",".",".","."],
 ["p","p",".",".","p","p","p","p"],
 [".",".","p",".",".",".",".","."],
 [".",".",".","p",".",".",".","."],
 [".",".",".","P",".","P",".","."],
 [".",".",".",".",".",".",".","."],
 ["P","P","P","","P",".","P","P"],
 [".",".",".",".",".",".",".","."]]
now to add a capture...

If the list/array of moves is: ["e4", "e5", "f3", "c6", "f4", "c4", "dxc4"]
>>>
[[".",".",".",".",".",".",".","."],
 ["p","p",".",".","p","p","p","p"],
 [".",".",".",".",".",".",".","."],
 [".",".","P","p",".",".",".","."],
 [".",".",".",".",".","P",".","."],
 [".",".",".",".",".",".",".","."],
 ["P","P","P",".","P",".","P","P"],
 [".",".",".",".",".",".",".","."]]
If an invalid move (a move is added that no pawn could perform, a capture where there is no piece, a move to a square where there is already a piece, etc.) is found in the list of moves, return '(move) is invalid'.

If the list/array of moves is: ["e6"]
>>>
[["e6 is invalid"]]
If the list/array of moves is: ["e4", "d5", "exf5"]
>>>
[["exf5 is invalid"]]
The list passed to pawn_move_tracker / PawnMoveTracker.movePawns will always be a list of strings in the form (regex pattern): [a-g][1-8] or [a-g]x[a-g][1-8].

Notes:

In the case of a capture, the first lowercase letter will always be adjacent to the second in the alphabet, a move like axc5 will never be passed.
A pawn can move two spaces on its first move
There are no cases with the 'en-passant' rule.
 */