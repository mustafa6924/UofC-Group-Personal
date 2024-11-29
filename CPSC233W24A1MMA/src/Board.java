/**
 * CPSC 233 W24 Assignment 1 Starter to use to make Board.java
 * TA Name: Tejas Shrestha
 * @author Muhammad Mustafa Amer
 * @email muhammadmustafa.amer@ucalgary.ca
 * @version 1.0
 */

public class Board {

    /**
     * No piece in board (empty)
     */
    public static int EMP = Game.EMP;
    /**
     * Connect-L Red Piece
     */
    public static final int RED = Game.RED;
    /**
     * Connect-L Blue Piece
     */
    public static final int BLU = Game.BLU;

    //Students should enter their functions below here
    public static int[][] createBoard(int rows, int columns) {
        if ((rows > 3) && (rows < 9) && (columns > 3) && (columns < 9)) {
            int[][] board = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = EMP;
                }
            }
            return board;

        }else {
            return null;
        }
    }
    // Takes 2 integers, row and count, as parameters and creates/initializes a 2d array with that number of rows and columns.


    public static int rowCount(int[][] board) {
        return board.length;
    }
    // Takes an integer 2d array as a parameter and uses the length function to return the number of rows in the array.
    public static int columnCount(int [][] board) {
        return board[0].length;
    }
    // Takes an integer 2d array as a parameter and uses the length function to return the number of columns in the array.

    public static boolean valid(int [][] board, int row, int column) {
        if ((row >= 0) && (column >= 0) && (row < rowCount(board)) && (column < columnCount(board))) {
            return true;
        }else {
            return false;
        }

    }

    public static boolean canPlay(int[][] board, int column) {
        if (board[0][column] == 0) {
            return true;
        }else {
            return false;
        }

    }

    public static int play(int[][] board, int column, int piece) {
        int row = 0;
        int index = 0;
        boolean flag = true;
        for (row = rowCount(board)-1; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = piece;
                index = row;
                flag = true;
                return index;


            }else {
                flag = false;
            }
        }

        if (flag == false) {
            return -1;
        }else {
            return index;
        }




    }

    public static int removeLastPlay(int[][] board, int column) {
        int row = 0;
        int index = 0;
        boolean flag = true;
        for (row = 0; row < rowCount(board); row++) {
            if (board[row][column] == RED || board[row][column] == BLU) {
                board[row][column] = EMP;
                index = row;
                return row;
            }else {
                flag = false;
            }
        }
        if (flag == false) {
            return -1;
        }else {
            return index;
        }

    }

    public static boolean full(int[][] board) {
        boolean flag = true;
        for (int row = 0; row < rowCount(board); row++) {
            for (int column = 0; column < columnCount(board); column++) {
                if (flag == false) {
                    return false;
                }
                if (board[row][column] == 1 || board[row][column] == 2) {
                    flag = true;
                }else {
                    flag = false;
                }
            }
        }
        if (flag) {
            return true;
        }else {
            return false;
        }
    }


    public static boolean winInRow(int[][] board, int row, int piece, int length) {
        boolean didwininarow = true;
        int inarowcount = 0;
        int index = 0;
        for (int column = 0; column < columnCount(board) -1 ; column++) {
            if ((row > 0) && (inarowcount >= length) && ((board[row - 1][index - length + 1] == piece))) {
                return true;
            }else if ((row > 0) && (inarowcount == length) && (board[row - 1][index] == piece)) {
                return true;
            }else if ((row <  rowCount(board) - 1) && (inarowcount == length) && (board[row + 1][index] == piece)) {
                return true;
            }else if ((row < rowCount(board) - 1) && (inarowcount == length) && (board[row + 1][index - length + 1] == piece)) {
                return true;
            }else if ((row > 0) && (inarowcount == length) && (board[row - 1][index] == piece)) {
                return true;
            }else if (board[row][column] == piece) {
                inarowcount++;
                index = column;
            }else {
                inarowcount = 0;
                didwininarow = false;
            }
        }
        if (didwininarow == false) {
            return false;
        }else {
            return true;
        }
    }




    public static boolean winInColumn(int[][] board, int column, int piece, int length) {
        boolean didwinincolumn = true;
        int inacolumncount = 0;
        int index = 0;
        for (int row = rowCount(board) - 1; row > - 1 ; row--) {
            if ((column < columnCount(board) - 1) && (column > 0) && (inacolumncount == length) && (board[index][column + 1] == piece)) {
                return true;
            }else if ((column > 0) && (column < columnCount(board) - 1) && (inacolumncount == length) && (board[index][column - 1] == piece)) {
                return true;
            }else if ((column == 0) && (inacolumncount == length) && (board[index][column + 1] == piece)) {
                return true;
            }else if ((column == columnCount(board) - 1) && (inacolumncount == length) && (board[index][column - 1] == piece)) {
                return true;
            }else if ((column == columnCount(board) - 1) && (column > 0) && (inacolumncount == length) && (board[index + length - 1][column - 1] == piece)) {
                return true;
            }else if ((column == 0) && (inacolumncount == length) && (board[index + length - 1][column + 1] == piece)) {
                return true;
            }else if ((column < columnCount(board) - 1) && (column > 0) && (inacolumncount == length) && (board[index + length - 1][column + 1] == piece)) {
                return true;
            }else if ((column < columnCount(board) - 1) && (column > 0) && (inacolumncount == length) && (board[index + length - 1][column - 1] == piece)) {
                return true;
            }else if (board[row][column] == piece) {
                inacolumncount++;
                index = row;
            }else if (board[row][column] != piece) {
                inacolumncount = 0;
                didwinincolumn= false;
            }
        }
        if (didwinincolumn) {
            return true;
        }else {
            return false;
        }
    }


    public static boolean winInDiagonalBackslash(int[][] board, int piece, int length) {
        int row = 0;
        int column = 0;
        int BackslashCount = 1;
        for (row = 0; row < rowCount(board); row++) { //loop for all rows
            for (column = 0; column < columnCount(board); column++) { //loop for all columns
                if (board[row][column] == piece) { //only continues if board piece is equal to piece
                    for (int i = 1; i <= length; i++) { //loop to check the left of a consecutive piece if it exists
                        if ((column + i < columnCount(board)) && (row + i < rowCount(board)) && (board[row + i][column + i] == piece)) {
                            BackslashCount++;
                            if (BackslashCount >= length) {
                                int topofrowL = row + i - 1;
                                int topofcolumnL = column + i + 1;
                                int bottomrowL = row + i + 1;
                                int bottomcolumnL = column + i - 1;

                                if (topofrowL >= 0 && topofrowL < rowCount(board) && topofcolumnL >= 0 && topofcolumnL < columnCount(board) && board[topofrowL][topofcolumnL] == piece) {
                                    return true;
                                }else if (bottomrowL >= 0 && bottomrowL < rowCount(board) && bottomcolumnL >= 0 && bottomcolumnL < columnCount(board) && (board[bottomrowL][bottomcolumnL] == piece)) {
                                    return true;

                                }
                            }


                        } else {
                            BackslashCount = 1;
                        }
                    }
                } else {
                    BackslashCount = 1;
                }
            }
        }


        BackslashCount = 1;

        for (row = rowCount(board) - 1; row >= 0; row--) {
            for (column = columnCount(board) - 1; column >= 0; column--) {
                if (board[row][column] == piece) {
                    for (int i = 1; i <= length; i++) {
                        if ((column - i >= 0) && (row - i >= 0) && board[row - i][column - i] == piece) {
                            BackslashCount++;
                            if (BackslashCount >= length) {
                                int topofrowL = row - i - 1;
                                int topofcolumnL = column - i + 1;
                                int bottomrowL = row - i + 1;
                                int bottomcolumnL = column - i -1;

                                if (topofrowL >= 0 && topofrowL < rowCount(board) && topofcolumnL >= 0 && topofcolumnL < columnCount(board) && (board[topofrowL][topofcolumnL] == piece)) {
                                    return true;
                                }else if (bottomrowL >= 0 && bottomrowL < rowCount(board) && bottomcolumnL >= 0 && bottomcolumnL < columnCount(board) && (board[bottomrowL][bottomcolumnL] == piece)) {
                                    return true;
                                }
                            }

                        }else {
                             BackslashCount = 1;
                        }
                    }

                }else {
                    BackslashCount = 1;
                }

                }
            }

        return false;
    }




    public static boolean winInDiagonalForwardSlash(int[][] board, int piece, int length) {
        int row = 0;
        int column = 0;
        int ForwardSlashcount = 1;
        int toprowL = 0;
        int topcolumnL = 0;
        int bottomrowL = 0;
        int bottomcolumnL = 0;
        for (row = rowCount(board) - 1; row >= 0; row--) {
            for (column = 0; column < columnCount(board); column ++) {
                if (board[row][column] == piece) {
                    for (int i = 1; i <= length; i++) {
                        if (row - i < rowCount(board) && row - i >= 0 && column + i < columnCount(board) && column >= 0 && board[row - i][column + i] == piece) {
                            ForwardSlashcount++;
                            if (ForwardSlashcount >= length) {
                                toprowL = row - i - 1;
                                topcolumnL = column + i - 1;
                                bottomrowL = row - i + 1;
                                bottomcolumnL = column + i +1;

                                if (toprowL >= 0 && toprowL < rowCount(board) && topcolumnL >= 0 && topcolumnL < columnCount(board)) {
                                    if (board[toprowL][topcolumnL] == piece) {
                                        return true;
                                    }
                                }else if (bottomrowL >= 0 && bottomrowL < rowCount(board) && bottomcolumnL >= 0 && bottomcolumnL < columnCount(board) && board[bottomrowL][bottomcolumnL] == piece) {
                                    return true;
                                }
                            }
                        }else {
                            ForwardSlashcount = 1;
                        }
                    }
                }else {
                    ForwardSlashcount = 1;
                }
            }
        }

        ForwardSlashcount = 1;

        for (row = 0; row < rowCount(board); row++) {
            for (column = columnCount(board) - 1; column >= 0; column--) {
                if (board[row][column] == piece) {
                    for (int i = 1; i <= length; i++) {
                        if (row + i >= 0 && row + i < rowCount(board) && column - i >= 0 && column - i < columnCount(board) && board[row + 1][column - 1] == piece) {
                            ForwardSlashcount++;
                            if (ForwardSlashcount >= length) {
                                toprowL = row + i - 1;
                                topcolumnL= column - i - 1;
                                bottomrowL = row + i + 1;
                                bottomcolumnL = column - i + 1;

                                if (toprowL >= 0 && toprowL < rowCount(board) && topcolumnL >= 0 && topcolumnL < columnCount(board) && board[toprowL][topcolumnL] == piece) {
                                    return true;
                                }else if (bottomrowL >= 0 && bottomrowL < rowCount(board) && bottomcolumnL >= 0 && bottomcolumnL < columnCount(board) && board[bottomrowL][bottomcolumnL] == piece) {
                                    return true;
                                }
                            }
                        }else {
                            ForwardSlashcount = 1;
                        }
                    }
                }else {
                    ForwardSlashcount = 1;
                }
            }
        }

        return false;
    }

    public static int[] hint(int[][] board, int piece, int length) {
        for (int column = 0; column < columnCount(board); column ++) {
            if (canPlay(board,column)) {
                play(board, column, piece);
                if (won(board,piece,length)) {
                    int row = removeLastPlay(board,column);
                    return new int[]{row,column};
                }else {
                    removeLastPlay(board,column);
                }
            }
        }
        return new int[]{-1,-1};
    }



    //Students should enter their functions above here


    /**
     * Is there a win in given board in any row of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any row
     * @return True if there is length in any row, False otherwise
     */
    private static boolean winInAnyRow(int[][] board, int piece, int length) {
        for (int row = 0; row < board.length; row++) {
            if (winInRow(board, row, piece, length)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is there a win in given board in any column of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any column
     * @return True if there is length in any column, False otherwise
     */
    private static boolean winInAnyColumn(int[][] board, int piece, int length) {
        for (int col = 0; col < board[0].length; col++) {
            if (winInColumn(board, col, piece, length)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is there a win in given board in any diagonal of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any diagonal
     * @return True if there is length in any diagonal /\, False otherwise
     */
    private static boolean winInAnyDiagonal(int[][] board, int piece, int length) {
        return winInDiagonalBackslash(board, piece, length) || winInDiagonalForwardSlash(board, piece, length);
    }

    /**
     * Has the given piece won the board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to check for a win
     * @return True if piece has won
     */
    public static boolean won(int[][] board, int piece, int length) {
        return winInAnyRow(board, piece, length) || winInAnyColumn(board, piece, length) || winInAnyDiagonal(board, piece, length);
    }

    /**
     * This function determines if the game is complete due to a win or tie by either player
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @return True if game is complete, False otherwise
     */
    public static boolean isGameOver(int[][] board, int length) {
        return full(board) || won(board, RED, length) || won(board, BLU, length);
    }









}

