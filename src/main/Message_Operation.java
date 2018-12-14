package main;

import java.util.BitSet;

public class Message_Operation {

    BitSet plainMessage = new BitSet(64);

    final int[] INITIAL_PERMUTATION =
            {
                    58, 50, 42, 34, 26, 18, 10, 2,
                    60, 52, 44, 36, 28, 20, 12, 4,
                    62, 54, 46, 38, 30, 22, 14, 6,
                    64, 56, 48, 40, 32, 24, 16, 8,
                    57, 49, 41, 33, 25, 17, 9, 1,
                    59, 51, 43, 35, 27, 19, 11, 3,
                    61, 53, 45, 37, 29, 21, 13, 5,
                    63, 55, 47, 39, 31, 23, 15, 7
            };

    final int[] EXPANSION_BOX =
            {
                    32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1
            };

    final int[] FINAL_PERMUTATION =
            {
                    40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25
            };

    public void addMessage(String m) {
        for(int i = 0; i < m.length(); i++) {
            if(m.charAt(i) == '1') {
                plainMessage.set(i);
            }
        }
        initialPermutateMessage(plainMessage);
    }

    private void initialPermutateMessage(BitSet plainMessage) {
        BitSet IP = new BitSet(64);
        for(int i = 0; i < 64; i++) {
            IP.set(i, plainMessage.get(INITIAL_PERMUTATION[i] - 1));
        }

        for(int j = 0; j < 64; j++) {
            if(j == 4 || j == 8 || j == 12 || j == 16 || j == 20 || j == 24 || j == 28 || j == 32 || j == 36 || j == 40 || j == 44 || j == 48 || j == 52 || j == 56 || j == 60 || j == 64) {
                System.out.print(" ");
            }
            System.out.print(IP.get(j) ? 1 : 0);
        }
        System.out.println();
    }

    private void encryptMessage(BitSet permutatedMessage) {

    }
}