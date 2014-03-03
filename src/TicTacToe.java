// File: TicTacToe.java
// Author: Dr. Watts
// Contents: This file contains the TicTacToe game class

//import java.io.*;
//import java.util.*;

import com.sun.tools.javac.util.Position;

import java.util.Random;

class TicTacToe {
    private char[][] positions;

    TicTacToe() {
        positions = new char[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                positions[i][j] = ' ';
    }

    public String toString() {
        String S = new String();
        S += "   ";
        for (int j = 0; j < 3; j++)
            S += "+-------";
        S += "+\n";
        for (int i = 1; i <= 3; i++) {
            S += "   ";
            for (int j = 0; j < 3; j++)
                S += "|       ";
            S += "|\n";
            S += " " + i + " ";
            for (int j = 1; j <= 3; j++)
                S += "|" + "   " + positions[i][j] + "   ";
            S += "|\n";
            S += "   ";
            for (int j = 0; j < 3; j++)
                S += "|       ";
            S += "|\n";
            S += "   ";
            for (int j = 0; j < 3; j++)
                S += "+-------";
            S += "+\n";
        }
        S += "   ";
        for (int j = 0; j < 3; j++)
            S += "    " + (j + 1) + "   ";
        S += "\n";
        return S;
    }

    public boolean Play(int row, int col, int who) {
        if (positions[row][col] == ' ') {
            positions[row][col] = (char) ('0' + who);
            return true;
        }
        return false;
    }

    public int makeBestMove(int player) {
        int r = 0, c = 0;
        boolean foundBlank = false;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j < 3; j++) {
                if (positions[i][j] == ' ') {
                    foundBlank = true;
                    positions[i][j] = (char) ('0' + (player == 1 ? 2 : 1));
                    if (Test() != 0) {
                        positions[i][j] = (char) ('0' + player);
                        r = i;
                        c = j;
                    } else {
                        positions[i][j] = ' ';
                    }
                }
            }
        }

        while (foundBlank && r != 0 && c != 0) {
            Random random = new Random();
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;

            if (positions[x][y] == ' ') {
                positions[x][y] = (char) ('0' + player);
                r = x;
                c = y;
            }
        }

        Play(r, c, player);
        return 3 * r + c;
    }

    public int Test() {
        // look for winning row
        for (int r = 1; r <= 3; r++)
            if (positions[r][1] != ' ') {
                int count = 1;
                for (int c = 2; c <= 3; c++)
                    if (positions[r][c] == positions[r][1])
                        count++;
                if (count == 3)
                    return positions[r][1] - '0';
            }
        // look for winning column
        for (int c = 1; c <= 3; c++)
            if (positions[1][c] != ' ') {
                int count = 1;
                for (int r = 2; r <= 3; r++)
                    if (positions[r][c] == positions[1][c])
                        count++;
                if (count == 3)
                    return positions[1][c] - '0';
            }
        // look for winning diagonal
        if (positions[1][1] != ' ')
            if (positions[2][2] == positions[1][1] && positions[3][3] == positions[1][1])
                return positions[1][1] - '0';
        if (positions[1][3] != ' ')
            if (positions[2][2] == positions[1][3] && positions[3][1] == positions[1][3])
                return positions[1][3] - '0';
        for (int r = 1; r <= 3; r++)
            for (int c = 1; c <= 3; c++)
                if (positions[r][c] == ' ')
                    return 0;
        return 3;
    }

    public char GetPosition(int row, int col) {
        return positions[row][col];
    }
}
