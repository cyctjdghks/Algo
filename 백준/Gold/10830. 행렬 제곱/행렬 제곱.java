import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];

        // read the matrix
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // calculate the result using matrix exponentiation
        int[][] result = pow(matrix, B);

        // print the result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] % 1000).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
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
                    C[i][j] %= 1000;
                }
            }
        }
        return C;
    }
}
