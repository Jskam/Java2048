import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int[][] gameBoard;
    private Random random = new Random();

    public Game() {
        gameBoard = new int[4][4];
    }

    public void printArray(){
        System.out.println("=============================");
        for(int y =0; y<4; y++) {
            for(int x = 0; x<4; x++) {
                System.out.format("%6d", gameBoard[x][y]);
            }
            if (y != 3) {
                System.out.println();
            } else {
                System.out.println("\n=============================");
            }
        }
    }

    public void addNewNumbers() {
        ArrayList<Integer> emptySpacesX = new ArrayList<>();
        ArrayList<Integer> emptySpacesY = new ArrayList<>();
        for (int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                if(gameBoard[x][y] == 0) {
                    emptySpacesX.add(x);
                    emptySpacesY.add(y);
                }
            }
        }
        int choice = random.nextInt(emptySpacesX.size());
        int numberChooser = random.nextInt(10); // 0-9
        int newNumber = 2;
        int X = emptySpacesX.get(choice); // получаем рандомную свободную X
        int Y = emptySpacesY.get(choice); // получаем рандомную свободную Y
        gameBoard[X][Y] = newNumber; // спавним рандомное значение в свободной клетке
        System.out.println("Point was spawn at the ["+ X + "; " + Y + "] cell");
    }

    public void pushUp() {
        System.out.println("Pushing up...");
        for(int x = 0; x < 4; x++) { // перебираем всё игровое поле
            for(int y = 1; y < 4; y++) {
                if(gameBoard[x][y]!=0) {
                    int value = gameBoard[x][y]; // значение клетки не равной нулю
                    int Y = y;
                    Y = Y-1;
                    while ((Y>=0)&&(gameBoard[x][Y]==0)) {
                        Y--; // ведем клетку вверх, пока не встретим сопротивление или не уткнемся в стенку
                    }
                    if(Y==-1){ // если не встретили клеток, то перемещемае клетку вплотную к стенке
                        gameBoard[x][0] = value;
                        gameBoard[x][y] = 0;
                    } else if(gameBoard[x][Y] != value) {
                        gameBoard[x][Y+1] = value; // встретили сопротивление, но значение в клетки выше не совпадает,
                        gameBoard[x][y] = 0; // значит оставляем перед ней
                    } else { // значения совпадают, удаляем клетку, а клетку выше удваиваем
                        gameBoard[x][Y] *=2;
                        gameBoard[x][y] = 0;
                    }

                }
            }
        }
    }

    public void pushDown() {
        System.out.println("Pushing down...");
        for(int x = 0; x < 4; x++) { // перебираем всё игровое поле
            for(int y = 2; y >= 0; y--) {
                if(gameBoard[x][y]!=0) {
                    int value = gameBoard[x][y]; // значение клетки не равной нулю
                    int Y = y;
                    Y = Y+1;
                    while ((Y<=3)&&(gameBoard[x][Y]==0)) {
                        Y++; // ведем клетку вверх, пока не встретим сопротивление или не уткнемся в стенку
                    }
                    if(Y==4){ // если не встретили клеток, то перемещемае клетку вплотную к стенке
                        gameBoard[x][3] = value;
                        gameBoard[x][y] = 0;
                    } else if(gameBoard[x][Y] != value) {
                        gameBoard[x][Y-1] = value; // встретили сопротивление, но значение в клетки выше не совпадает,
                        gameBoard[x][y] = 0; // значит оставляем перед ней
                    } else { // значения совпадают, удаляем клетку, а клетку выше удваиваем
                        gameBoard[x][Y] *=2;
                        gameBoard[x][y] = 0;
                    }

                }
            }
        }
    }

    public void pushLeft() {
        System.out.println("Pushing left...");
        for(int y = 0; y < 4; y++) { // перебираем всё игровое поле
            for(int x = 1; x <4; x++) {
                if(gameBoard[x][y]!=0) {
                    int value = gameBoard[x][y]; // значение клетки не равной нулю
                    int X = x;
                    X = X-1;
                    while ((X>=0)&&(gameBoard[X][y]==0)) {
                        X--; // ведем клетку вверх, пока не встретим сопротивление или не уткнемся в стенку
                    }
                    if(X==-1){ // если не встретили клеток, то перемещемае клетку вплотную к стенке
                        gameBoard[0][y] = value;
                        gameBoard[x][y] = 0;
                    } else if(gameBoard[X][y] != value) {
                        gameBoard[X+1][y] = value; // встретили сопротивление, но значение в клетки выше не совпадает,
                        gameBoard[x][y] = 0; // значит оставляем перед ней
                    } else { // значения совпадают, удаляем клетку, а клетку выше удваиваем
                        gameBoard[X][y] *=2;
                        gameBoard[x][y] = 0;
                    }

                }
            }
        }
    }

    public void pushRight() {
        System.out.println("Pushing right...");
        for(int y = 0; y < 4; y++) { // перебираем всё игровое поле
            for(int x = 2; x >=0; x--) {
                if(gameBoard[x][y]!=0) {
                    int value = gameBoard[x][y]; // значение клетки не равной нулю
                    int X = x;
                    X = X+1;
                    while ((X<=3)&&(gameBoard[X][y]==0)) {
                        X++; // ведем клетку вверх, пока не встретим сопротивление или не уткнемся в стенку
                    }
                    if(X==4){ // если не встретили клеток, то перемещемае клетку вплотную к стенке
                        gameBoard[3][y] = value;
                        gameBoard[x][y] = 0;
                    } else if(gameBoard[X][y] != value) {
                        gameBoard[X-1][y] = value; // встретили сопротивление, но значение в клетки выше не совпадает,
                        gameBoard[x][y] = 0; // значит оставляем перед ней
                    } else { // значения совпадают, удаляем клетку, а клетку выше удваиваем
                        gameBoard[X][y] *=2;
                        gameBoard[x][y] = 0;
                    }

                }
            }
        }
    }
}
