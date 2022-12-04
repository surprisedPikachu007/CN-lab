// error checking using CRC

import java.io.*;
import java.util.Scanner;

public class CRC {

    static Scanner scanner = new Scanner(System.in);

    static int[] divide(int[] dividend, int[] divisor, int[] remainder) {
        int current = 0;
        while(true) {
            for (int i = 0; i < divisor.length; i++) {
                remainder[current + i] ^= divisor[i];
            }
            while (current < remainder.length && remainder[current] == 0) {
                current++;
            }
            if (remainder.length - current < divisor.length) {
                break;
            }
        }

        return remainder;
    }

    public static void main(String[] args) throws IOException {
        int[] data, dividend, divisor, remainder, crc, received;
        int n, m, total;

        System.out.print("Enter the number of data bits: ");
        n = scanner.nextInt();
        data = new int[n];
        System.out.println("Enter the data bits: ");
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of divisor bits: ");
        m = scanner.nextInt();
        divisor = new int[m];
        System.out.println("Enter the divisor bits: ");
        for (int i = 0; i < m; i++) {
            divisor[i] = scanner.nextInt();
        }

        total = n + m - 1;
        dividend = new int[total];
        remainder = new int[total];
        crc = new int[total];

        for (int i = 0; i < n; i++) {
            dividend[i] = data[i];
        }

        for(int i = 0; i < total; i++) {
            remainder[i] = dividend[i];
        }

        remainder = divide(dividend, divisor, remainder);

        for (int i = 0; i < total; i++) {
            crc[i] = remainder[i] ^ dividend[i];
        }

        System.out.print("The CRC code is: ");
        for (int i = 0; i < total; i++) {
            System.out.print(crc[i]);
        }
        System.out.println();

        received = new int[total];
        System.out.println("Enter the received code word: ");
        for (int i = 0; i < total; i++) {
            received[i] = scanner.nextInt();
            remainder[i] = received[i];
        }

        remainder = divide(received, divisor, remainder);

        boolean error = false;
        for (int i = 0; i < remainder.length; i++) {
            if (remainder[i] != 0) {
                error = true;
                break;
            }
        }

        if (error) {
            System.out.println("Error detected");
        } else {
            System.out.println("No error detected");
        }

    }
}
