package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {
    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        int rows = pyramid.getRows();
        if (rows == 0) return 0;

        int cols = rows;
        long[][] dp = new long[rows][cols];

        int last = rows - 1;
        for (int c = 0; c < cols; c++) {
            dp[last][c] = pyramid.get(last, c);
        }

        for (int r = last - 1; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                long down = dp[r + 1][c];
                long downLeft = c > 0 ? dp[r + 1][c - 1] : Long.MIN_VALUE;
                dp[r][c] = pyramid.get(r, c) + Math.max(down, downLeft);
            }
        }
        long result = Long.MIN_VALUE;
        for (int c = 0; c < cols; c++) {
            result = Math.max(result, dp[0][c]);
        }
        return result;
    }
}
/*
In my solution, I apply a bottom-up dynamic programming approach.
I construct a 2D array called dp where each element dp[r][c] stores
the maximum total achievable from the cell at position (r, c) down to the base of the pyramid.
To start, I handle the base case by simply copying the values from the bottom row of the pyramid
into the last row of the dp array. Since there are no moves available from the bottom row,
each value there represents the maximum total from that point downward.
then, I move upward through the pyramid. For each cell, I consider the two allowed moves: either
going straight down or down-right. I take the maximum of the two corresponding values from the row below in dp,
add the value of the current cell, and store the result back in the current dp[r][c] position.
Once I reach the top of the pyramid, the maximum total from top to bottom is stored in dp[0][0], which I return.
*/
