package com.company;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.ArrayList;

public class Mario3 {

    static class MarioPoint {
        public int count = 0;
        public Point position = new Point();
        public Point velocity = new Point(0, 0);

        public boolean[][] visited;
    }

    static char[][] board;
    static Queue<Point> start = new Queue<>();
    static ArrayList<Point> finished = new ArrayList<>();
    static MarioPoint bestPath = new MarioPoint();

    // Queue for bfs
    static Queue<MarioPoint> q = new Queue<>();

    static boolean[][] marked;

    public static void main(String[] args) {
        int y = Integer.parseInt(StdIn.readLine());
        int x = Integer.parseInt(StdIn.readLine());

        board = new char[x][y];
        //marked = new boolean[x][y];

        // 'draws' the board
        for (int i = 0; i < y; i++) {
            String line = StdIn.readLine();
            for (int j = 0; j < x; j++) {
                char c = line.charAt(j);
                board[j][i] = c;
                //marked[j][i] = false;
                if (c == 'S')
                    start.enqueue(new Point(j, i));
                else if (c == 'F')
                    finished.add(new Point(j, i));
            }
        }

        // Do BFS from all starting points
        for (Point s : start) {
            MarioPoint mario = new MarioPoint();
            mario.position = s;
            mario.visited = new boolean[x][y];
            bfs(mario);
        }
        StdOut.println(bestPath.count);
    }


    // breadth-first search from a single source
    static void bfs(MarioPoint s) {

        q.enqueue(s);

        while (!q.isEmpty()) {
            MarioPoint mp = q.dequeue();

            // Moves mario
            mp.position.x += mp.velocity.x;
            mp.position.y += mp.velocity.y;

            getNewPositions(mp);


            //checks if it's finished
            for (Point f : finished)
                if (f.x == mp.position.x && f.y == mp.position.y) {
                    bestPath = mp;
                    StdOut.println(mp.count);
                    q = new Queue<>(); // empties queue
                }

        }
    }

    static void getNewPositions(MarioPoint mario) {

        // (-1,-1) upper left
        try {
            if (board[mario.position.x - 1][mario.position.y - 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x  - 1;
                mp.position.y = mario.position.y  - 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x - 1;
                mp.velocity.y = mario.velocity.y - 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }

        // (0,-1) middle left
        try {
            if (board[mario.position.x][mario.position.y - 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x;
                mp.position.y = mario.position.y - 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x;
                mp.velocity.y = mario.velocity.y - 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        // (1,-1) bottom left
        try {
            if (board[mario.position.x + 1][mario.position.y - 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x + 1;
                mp.position.y = mario.position.y - 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x + 1;
                mp.velocity.y = mario.velocity.y - 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }

        // (-1,0) upper middle
        try {
            if (board[mario.position.x - 1][mario.position.y] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x - 1;
                mp.position.y = mario.position.y;
                // sets new velocity
                mp.velocity.x = mario.velocity.x - 1;
                mp.velocity.y = mario.velocity.y;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }

        // (1,0) bottom middle
        try {
            if (board[mario.position.x + 1][mario.position.y] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x + 1;
                mp.position.y = mario.position.y;
                // sets new velocity
                mp.velocity.x = mario.velocity.x + 1;
                mp.velocity.y = mario.velocity.y;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }

        // (-1,1) upper right
        try {
            if (board[mario.position.x - 1][mario.position.y + 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x - 1;
                mp.position.y = mario.position.y + 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x - 1;
                mp.velocity.y = mario.velocity.y + 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }

        // (0,1) middle right
        try {
            if (board[mario.position.x][mario.position.y + 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x;
                mp.position.y = mario.position.y + 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x;
                mp.velocity.y = mario.velocity.y + 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }


        // (1,1) bottom right
        try {
            if (board[mario.position.x + 1][mario.position.y + 1] != 'O') {
                MarioPoint mp = new MarioPoint();
                mp.position.x = mario.position.x + 1;
                mp.position.y = mario.position.y + 1;
                // sets new velocity
                mp.velocity.x = mario.velocity.x + 1;
                mp.velocity.y = mario.velocity.y + 1;
                // count +1 steps
                mp.count = mario.count + 1;
                // sets visited fields for this path
                mp.visited = clone2dArray(mario.visited);

                if (!mp.visited[mp.position.x][mp.position.y]) {
                    mp.visited[mp.position.x][mp.position.y] = true;
                    q.enqueue(mp);
                }
            }
        } catch (Exception ex) {
        }
    }

    static boolean[][] clone2dArray(boolean[][] visited){
        boolean[][] tmp = new boolean[visited.length][];

        for (int i = 0; i < visited.length; i++){
            tmp[i] = visited[i].clone();
        }
        return tmp;
    }

}
