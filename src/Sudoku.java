import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    int[][] currentGame = new int[9][9];
    int[][] currentSolution = new int[9][9];
    public void Clear(TextField[][] entries) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                entries[i][j].setEditable(true);
                entries[i][j].setText("");
                entries[i][j].getStyleClass().removeAll("tf","tf1","tf2","tf3");
                entries[i][j].getStyleClass().add("tf");
                entries[i][j].setAlignment(Pos.CENTER);
                entries[i][j].setFont(Font.font("Cambria",29));
                entries[i][j].setStyle("-fx-text-inner-color: #213d59;");

                if (j%3==2)
                    entries[i][j].getStyleClass().add("tf1");
                if (i%3==2)
                    entries[i][j].getStyleClass().add("tf2");
                if (j%3==2 && i%3==2)
                    entries[i][j].getStyleClass().add("tf3");


                entries[i][j].setPrefWidth(80);
                entries[i][j].setPrefHeight(65);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (currentGame[i][j]!=0) {
                    entries[i][j].setText(currentGame[i][j]+"");
                    entries[i][j].setStyle("-fx-background-color: LIGHTBLUE;");
                    entries[i][j].setEditable(false);


                }

                }
            }
        }





public void NewGame(int Level, TextField[][] entries) throws FileNotFoundException {
        currentGame = new int[9][9];
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            entries[i][j].setEditable(true);
            entries[i][j].setText("");
            entries[i][j].getStyleClass().removeAll("tf","tf1","tf2","tf3");
            entries[i][j].getStyleClass().add("tf");
            entries[i][j].setAlignment(Pos.CENTER);
            entries[i][j].setFont(Font.font("Cambria",29));
            entries[i][j].setStyle("-fx-text-inner-color: #213d59;");

            if (j%3==2)
                entries[i][j].getStyleClass().add("tf1");
            if (i%3==2)
                entries[i][j].getStyleClass().add("tf2");
            if (j%3==2 && i%3==2)
                entries[i][j].getStyleClass().add("tf3");


            entries[i][j].setPrefWidth(80);
            entries[i][j].setPrefHeight(65);
        }
    }
        int r = (int) (Math.random()*10);
    r = r%4;
        File file = new File("C:\\Users\\joman\\IdeaProjects\\Project4Sudoku\\SudokuGames\\Sudoku"+Level+"p"+r+".txt");
    System.out.println(file.exists());
    Scanner Reader = new Scanner(file);
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
        currentGame[i][j] = Reader.nextInt();

        if (currentGame[i][j]!=0) {
            entries[i][j].setText(currentGame[i][j]+"");
            entries[i][j].setStyle("-fx-background-color: LIGHTBLUE;");
            entries[i][j].setEditable(false);


        }
        else {


        }
    }

}
    }

public void FillSolution(){
        currentSolution = new int[9][9];
        for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
        currentSolution[i][j] = currentGame[i][j];
        }
        }
        SudokuSolver(currentSolution,0,0);

        }

public static boolean SudokuSolver (int[][] grid, int row, int column) {
        if (row==grid.length && column== grid.length-1) {
            return true;
        }
        else if (row==grid.length) {
            row = 0;
            column++;
        }

        if (grid[row][column]==0) {
            for (int i = 1; i < 10; i++) {
                if (CheckSolution(grid,row,column,i)) {
                    grid[row][column] = i;


                    if (SudokuSolver(grid, row+1, column))
                        return true;

                }
                grid[row][column] =0;
            }
        }
        else {
            return SudokuSolver(grid, row+1, column);
        }
        return false;
    }
/*
    public static boolean ؤ(int grid[][], int row, int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i]==number)
                return false;
        }
        for (int i = 0; i < 9; i++) {
            if(grid[i][column]==number)
                return false;
        }
        for (int i = (row/3)*3; i < ((row/3)*3)+2; i++) {
            for (int j = (column/3)*3; j < ((column/3)*3)+2; j++) {
                if(grid[i][j]==number)
                    return false;
            }
        }
        return true;
    }
*/
static boolean CheckSolution(int[][] grid, int row, int col,
                      int num)
{


    for (int x = 0; x <= 8; x++)
        if (grid[row][x] == num)
            return false;


    for (int x = 0; x <= 8; x++)
        if (grid[x][col] == num)
            return false;


    int startRow = row - row % 3, startCol
            = col - col % 3;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (grid[i + startRow][j + startCol] == num)
                return false;

    return true;
}

    public void GiveUp(TextField[][] entries) {
        FillSolution();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                entries[i][j].setText(""+currentSolution[i][j]);
                if (entries[i][j].isEditable())
                    entries[i][j].setStyle("-fx-text-fill: #213d59;");

            }
        }
    }

    public void Check(TextField[][] entries) throws FileNotFoundException {
        if (CheckAllSolution(entries)) {
            new WinStage();
            return;

        }


        int[][]nums = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!entries[i][j].getText().equals(""))
                nums[i][j] = Integer.parseInt(entries[i][j].getText());
            }
        }


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nums[i][j]==0)
                    continue;
                if (entries[i][j].isEditable())
                    entries[i][j].setStyle("-fx-text-fill: #213d59;");
                if (!Check(nums,i,j,nums[i][j])&& entries[i][j].isEditable()) {
                    entries[i][j].setStyle("-fx-text-fill: #213d59;");
                    int n = nums[i][j];
                    nums[i][j] = 0;
                    if (!CheckSolution(nums,i,j,n))
                    entries[i][j].setStyle("-fx-text-fill: RED;");
                    nums[i][j] = n;
                }
            }
        }




    }

    private boolean CheckAllSolution(TextField[][] entries) {
        int grid[][]= new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (entries[i][j].getText().equals(""))
                    return false;
                grid[i][j] = Integer.parseInt(entries[i][j].getText());
            }
        }
            /// check rows

            for (int i = 0; i < 9; i++) {
                boolean[] checks = new boolean[9];
                for (int j = 0; j < 9; j++) {
                    if (checks[grid[i][j]-1])
                        return false;
                    else
                        checks[grid[i][j]-1] = true;
                }
            }
            // check columns
            for (int i = 0; i < 9; i++) {
                boolean[] checks = new boolean[9];
                for (int j = 0; j < 9; j++) {
                    if (checks[grid[j][i]-1])
                        return false;
                    else
                        checks[grid[j][i]-1] = true;
                }
            }

            //check grids
            for (int i = 0; i < 9; i++) {
                boolean[] checks = new boolean[9];

                for (int j = (i%3)*3; j < ((i%3)*3)+3; j++) {

                    for (int k = (i/3)*3; k < ((i/3)*3)+3; k++) {

                        if ( checks[grid[j][k]-1]==true)
                            return false;
                        else
                            checks[grid[j][k]-1]=true;

                    }
                }

            }
            return true;


    }

    public static boolean Check(int[][] grid, int row, int column, int number) {

        for (int i = 0; i < 9; i++) {
            if (grid[row][i]==number && i!=column)
                return false;
        }
        for (int i = 0; i < 9; i++) {
            if(grid[i][column]==number && i!=row)
                return false;
        }
        for (int i = (row/3)*3; i < ((row/3)*3)+2; i++) {
            for (int j = (column/3)*3; j < ((column/3)*3)+2; j++) {
                if(grid[i][j]==number &&( i!=row || j!=column))
                    return false;
            }
        }
        return true;
    }
}
