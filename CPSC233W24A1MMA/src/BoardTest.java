import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CPSC 233 W24 Assignment 1 Starter to use to make Board.java
 * TA Name: Tejas Shrestha
 * @author Muhammad Mustafa Amer
 * @email muhammadmustafa.amer@ucalgary.ca
 * @version 1.0
 */
public class BoardTest {

    // Tests for function createBoard() start here:
    @Test
    public void createBoardTestrowlength() {
        int rows = 4;
        int[][] board = Board.createBoard(rows,8);
        assertEquals(rows, board.length);
    }

    @Test
    public void createBoardTestcolumnlength() {
        int columns = 8;
        int[][] board = Board.createBoard(4,columns);
        assertEquals(8, board[0].length);
    }

    @Test
    public void createBoardTestinitialized() {
        int[][] board = Board.createBoard(4,8);
        int temp = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp = board[i][j];
                assertEquals(0,temp);
            }
        }
    }

    @Test
    public void createBoardTestincorrectrows() {
        int[][] board = Board.createBoard(0,8);
        assertNull(board);
    }

    @Test
    public void createBoardTestincorrectcolumns() {
        int[][] board = Board.createBoard(4,11);
        assertNull(board);
    }

    // Tests for createBoard() end here.

    // Tests for function rowCount() start here:

    @Test
    public void rowCountTestlengthusingcreateboard() {
        int[][] board = Board.createBoard(6,8);
        assertEquals(6,Board.rowCount(board));
    }

    @Test
    public void rowCountTestemptyboard() {
        int[][] board = {};
        assertEquals(0,Board.rowCount(board));

    }

    @Test
    public void rowCountTestlengthusingpredefinedarray() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
        };

        assertEquals(4, Board.rowCount(board));
    }

    @Test
    public void rowCountTestlargestvalueofboard() {
        int[][] board = Board.createBoard(8,8);

        assertEquals(8,Board.rowCount(board));
    }

    @Test
    public void rowCountTestminimumvalueofboard() {
        int[][] board = Board.createBoard(4,8);

        assertEquals(4,Board.rowCount(board));
    }

    // Tests for function rowCount() end here.

    // Tests for function columnCount() start here:

    @Test
    public void columnCountTestlengthusingcreateboard() {
        int[][] board = Board.createBoard(8,6);
        assertEquals(6,Board.columnCount(board));
    }

    @Test
    public void columnCountTestusingpredefinedarray() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
        };

        assertEquals(5, Board.columnCount(board));
    }

    @Test
    public void columnCountTestmaximumvalueofboard() {

    }

    // Tests for function columnCount() end here.

    // Tests for function valid() start here:

    @Test
    public void validTest1() {
        int[][] board = {};
        assertEquals(false, Board.valid(board,0,0));
    }

    @Test
    public void validTest2() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
        };
        assertEquals(false, Board.valid(board,5,6));
    }

    @Test
    public void validTest3() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
        };
        assertEquals(true, Board.valid(board,0,0));
    }

    @Test
    public void validTest4() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
        };
        assertEquals(true, Board.valid(board,3,5));
    }

    @Test
    public void validTest5() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
        };
        assertEquals(false, Board.valid(board,Board.rowCount(board),Board.columnCount(board)));
    }

    // Tests for function valid() end here.

    // Tests for function winInColumn start here:

    @Test
    public void wininColumnTestmirroredYAxisL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
        };
        assertEquals(true,Board.winInColumn(board,2,1,3));
    }

    @Test
    public void wininColumnTestnormalL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
        };
        assertEquals(true,Board.winInColumn(board,2,1,3));
    }

    @Test
    public void wininColumnTestmirroredXAxisL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
        };
        assertEquals(true,Board.winInColumn(board,2,1,3));
    }

    @Test
    public void wininColumnTestmirroredinYaxisandXaxis() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
        };
        assertEquals(true,Board.winInColumn(board,2,1,3));
    }

    @Test
    public void wininColumnTestincorrectlengthL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0},
        };
        assertEquals(false,Board.winInColumn(board,2,1,3));
    }

    // Tests for function winInColumn end here.

    // Test for function winInRow start here:

    @Test
    public void winInRowTesthorizontalupwardsL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
        };
        assertEquals(true, Board.winInRow(board,3,1,3));
    }
    @Test
    public void winInRowTesthorizontalbackwardsupwardsL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
        };
        assertEquals(true, Board.winInRow(board,3,1,3));
    }

    @Test
    public void winInRowTesthorizontalmirroredXaxisL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
        };
        assertEquals(true, Board.winInRow(board,1,1,3));
    }

    @Test
    public void winInRowTesthorizontalLmirroredinXandYaxisL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        assertEquals(true, Board.winInRow(board,1,1,3));
    }

    @Test
    public void winInRowTestmirroredinYaxisL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
        };
        assertEquals(true, Board.winInRow(board,3,1,3));
    }

    @Test
    public void winInRowTestincorrectlengthL() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
        };
        assertEquals(false, Board.winInRow(board,3,1,3));
    }

    // Tests for function winInRow end here.

    // Tests for function full start here:

    @Test
    public void fullTestfullboard() {
        int[][] board = new int[][]{
                {1, 2, 2, 1, 1},
                {1, 2, 1, 2, 1},
                {2, 1, 2, 2, 2},
                {1, 1, 1, 2, 1},
        };

        assertEquals(true, Board.full(board));

    }

    @Test
    public void fullTestnotfullboard() {
        int[][] board = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 2, 0, 2, 1},
                {2, 1, 0, 2, 2},
                {1, 1, 1, 2, 1},
        };

        assertEquals(false, Board.full(board));
    }

    @Test
    public void fullTestinitializedboard() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        assertEquals(false, Board.full(board));
    }

    @Test
    public void fullTestemptyboardusingcreateBoard() {
        int [][] board = Board.createBoard(4,4);

        assertEquals(false, Board.full(board));
    }

    @Test
    public void fullTestemptyboardusingcreateboard2() {
        for (int row = 4; row <= 8; row++) {
            for (int column = 4; column <= 8; column++) {
                int[][] board = Board.createBoard(row,column);
                assertEquals(false, Board.full(board));
            }
        }
    }

    // Tests for function full end here.

    // Tests for function removelastplay() start here:

    @Test
    public void removelastplayTestremovefromanycolumn() {
        int[][] board = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 2, 0, 2, 1},
                {2, 1, 0, 2, 2},
                {1, 1, 1, 2, 1},
        };
        assertEquals(3,Board.removeLastPlay(board,2));
    }

    @Test
    public void rmeovelastplayTestemptycolumn() {
        int[][] board = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 2, 0, 2, 1},
                {2, 1, 0, 2, 2},
                {1, 1, 0, 2, 1},
        };
        assertEquals(-1,Board.removeLastPlay(board,2));
    }

    @Test
    public void removelastplayTestmiddlerow() {
        int[][] board = new int[][]{
                {0, 1, 0, 1, 1},
                {0, 2, 0, 2, 1},
                {2, 1, 0, 2, 2},
                {1, 1, 0, 2, 1},
                {2, 1, 2, 1, 1},
        };
        assertEquals(2, Board.removeLastPlay(board,0));
    }

    @Test
    public void removelastplayTesttoprow() {
        int[][] board = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 2, 0, 2, 1},
                {2, 1, 0, 2, 2},
                {1, 1, 0, 2, 1},
        };

        assertEquals(0,Board.removeLastPlay(board,1));
    }

    @Test
    public void removelastplayTestallcolumnsempty() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        for (int i = 0; i < 5; i++) {
            assertEquals(-1,Board.removeLastPlay(board,i));
        }
    }




    // Test for removelastplay() end here.


    // Tests for winInDiagonalBackslash() start here:

    @Test
    public void winindiagonalBSTest1() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 1, 0},
                {2, 1, 1, 2, 0},
                {1, 2, 2, 1, 0},
        };

        assertEquals(true, Board.winInDiagonalBackslash(board,1,3));
    }

    @Test
    public void winindiagonalBSTest2() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 1, 0},
                {2, 1, 1, 0, 0},
                {2, 1, 2, 0, 0},
        };

        assertEquals(true, Board.winInDiagonalBackslash(board,1,3));
    }

    @Test
    public void winindiagonalBSTest3() {
        int[][] board = new int[][]{
                {1, 0, 2, 0, 0},
                {0, 2, 0, 0, 0},
                {0, 1, 2, 0, 0},
                {0, 1, 0, 2, 0},
        };

        assertEquals(true, Board.winInDiagonalBackslash(board,2,3));

    }

    @Test
    public void winindiagonalBSTest4() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {2, 1, 2, 0, 0},
                {0, 1, 1, 2, 0},
        };
        assertEquals(false, Board.winInDiagonalBackslash(board, 2, 3));
    }

    @Test
    public void winindiagonalBSTest5() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {1, 2, 0, 0, 0},
                {2, 1, 2, 0, 0},
                {2, 1, 1, 2, 1},
        };
        assertEquals(true, Board.winInDiagonalBackslash(board, 2, 3));
    }


    // Tests for winInDiagonalBackslash() end here.

    // Tests for winInDiagonalForwardslash() start here:

    @Test
    public void winindiagonalFSTest1() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {1, 2, 0, 0, 0},
                {2, 1, 2, 0, 0},
                {2, 1, 1, 2, 1},
        };

        assertEquals(false, Board.winInDiagonalForwardSlash(board,1,3));
    }

    @Test
    public void winindiagonalFSTest2() {
        int[][] board = new int[][]{
                {1, 0, 0, 1, 0},
                {1, 2, 1, 0, 1},
                {2, 1, 2, 0, 0},
                {1, 1, 1, 2, 1},
        };

        assertEquals(true, Board.winInDiagonalForwardSlash(board,1,4));
    }

    @Test
    public void winindiagonalFSTest3() {
        int[][] board = new int[][]{
                {1, 0, 0, 2, 0},
                {1, 2, 2, 0, 2},
                {2, 2, 2, 1, 0},
                {2, 1, 1, 2, 1},
        };

        assertEquals(true, Board.winInDiagonalForwardSlash(board,2,4));
    }

    @Test
    public void winindiagonalFSTest4() {
        int[][] board = new int[][]{
                {1, 0, 1, 0, 0},
                {1, 2, 0, 1, 0},
                {2, 1, 1, 0, 0},
                {1, 1, 1, 2, 1},
                {1, 1, 1, 1, 2},
        };

        assertEquals(true, Board.winInDiagonalForwardSlash(board,1,4));
    }

    @Test
    public void winindiagonalFSTest5() {
        int[][] board = new int[][]{
                {1, 0, 0, 1, 1},
                {1, 2, 1, 1, 0},
                {2, 1, 1, 0, 0},
                {1, 1, 1, 2, 1},
                {2, 2, 1, 2, 1}
        };

        assertEquals(true, Board.winInDiagonalForwardSlash(board,1,4));
    }


    // Tests for winInDiagonalForwardslash() start here:

    // Tests for function canPlay() start here:

    @Test
    public void canPlayTest1() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {2, 1, 1, 0, 0},
                {2, 1, 2, 0, 0},
        };
        assertEquals(true, Board.canPlay(board,1));
    }

    @Test
    public void canPlayTest2() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {2, 1, 1, 0, 0},
                {2, 1, 2, 0, 0},
        };
        assertEquals(false, Board.canPlay(board,0));
    }

    @Test
    public void canPlayTest3() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {2, 1, 1, 0, 0},
                {2, 1, 2, 0, 0},
        };
        assertEquals(true, Board.canPlay(board,4));
    }

    @Test
    public void canPlayTest4() {
        int[][] board = new int[][]{
                {1, 0, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {2, 1, 2, 0, 0},
        };
        assertEquals(true, Board.canPlay(board,2));
    }

    @Test
    public void canPlayTest5() {
        int[][] board = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 1, 1, 2, 2},
                {2, 1, 1, 1, 1},
                {2, 1, 2, 2, 2},
        };
        for (int i = 0; i < Board.columnCount(board); i++) {
            assertEquals(false, Board.canPlay(board,i));
        }


    }

    // Tests for canPlay() end here.

    //Tests for play() start here:

    @Test
    public void playTest1 () {
        int[][] board = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 1, 1, 2, 2},
                {2, 1, 1, 1, 1},
                {2, 1, 2, 2, 2},
        };

        assertEquals(-1, Board.play(board,0,1));
    }
    @Test
    public void playTest2 () {
        int[][] board = new int[][]{
                {1, 1, 0, 1, 1},
                {2, 1, 0, 2, 2},
                {2, 1, 0, 1, 1},
                {2, 1, 0, 2, 2},
        };

        assertEquals(3, Board.play(board,2,2));
    }
    @Test
    public void playTest3 () {
        int[][] board = new int[][]{
                {1, 1, 1, 1, 0},
                {2, 1, 1, 2, 0},
                {2, 1, 1, 1, 0},
                {2, 1, 2, 2, 2},
        };

        assertEquals(2, Board.play(board,4,1));
    }
    @Test
    public void playTest4 () {
        int[][] board = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 1, 1, 2, 2},
                {2, 1, 1, 1, 1},
                {2, 1, 2, 2, 2},
        };

        for (int i = 0; i < Board.columnCount(board); i++) {
            assertEquals(-1, Board.play(board,i,2));
        }
    }
    @Test
    public void playTest5 () {
        int[][] board = new int[][]{
                {0, 1, 1, 1, 1},
                {2, 1, 1, 2, 2},
                {2, 1, 1, 1, 1},
                {2, 1, 2, 2, 2},
        };

        assertEquals(0, Board.play(board,0,1));
    }

    //Tests for play() end here.

    /**
     * Used to make a copy of board before functions run, so that verify a function was non-destructive on board is easy
     * @param board The board to make deep copy of
     * @return A deep copy of given board
     */
    public int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }
}