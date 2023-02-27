import java.util.Scanner;

public class Main {
    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        B = sc.nextLong();
        matrix = new int[N][N];

        // read the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // calculate the result using matrix exponentiation
        int[][] result = pow(matrix, B);

        // print the result
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] % 1000 + " ");
            }
            System.out.println();
        }
    }

    // matrix exponentiation function
    public static int[][] pow(int[][] A, long exp) {
        // base case: exp = 1
        if (exp == 1) {
            return A;
        }

        // recursive cases: exp is even or odd
        int[][] half = pow(A, exp / 2);
        if (exp % 2 == 0) {
            return multiply(half, half);
        } else {
            return multiply(multiply(half, half), A);
        }
    }

    // matrix multiplication function
    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= 1000;
            }
        }
        return C;
    }
}
