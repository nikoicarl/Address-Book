/**
 * Student Name: Carl Nikoi
 * Student Number: 100439006
 */
package org.example.lab10;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab10 {

    public static void main(String[] args) {
        Date start = new Date();

        double[][] matrix1 = generateRandomValuesMatrix(2000, 2000);
        double[][] matrix2 = generateRandomValuesMatrix(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        parallelMultiplyMatrix(matrix1, matrix2, result);

        Date end = new Date();
        System.out.println("\nParallel Multiplication - Time taken: " + (end.getTime() - start.getTime()) + " milliseconds");
    }

    public static void parallelMultiplyMatrix(double[][] matrix1, double[][] matrix2, double[][] result) {
        int rows1 = matrix1.length;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            for (int i = 0; i < rows1; i++) {
                // Create a separate Runnable task for each row multiplication
                Runnable task = new MatrixMultiplicationTask(matrix1, matrix2, result, i);
                executor.execute(task);
            }
        } finally {
            executor.shutdown();
        }
    }

    public static double[][] generateRandomValuesMatrix(int rows, int columns) {
        double[][] result = new double[rows][columns];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = random.nextDouble() * 10;
            }
        }
        return result;
    }
}

class MatrixMultiplicationTask implements Runnable {
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final double[][] result;
    private final int row;

    public MatrixMultiplicationTask(double[][] matrix1, double[][] matrix2, double[][] result, int row) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        for (int i = 0; i < matrix2[0].length; i++) {
            result[row][i] = 0;
            for (int j = 0; j < matrix1[row].length; j++) {
                result[row][i] += matrix1[row][j] * matrix2[j][i];
            }
        }
    }
}
