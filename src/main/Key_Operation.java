package main;

import java.util.BitSet;

public class Key_Operation {
    final int PREPARITY_SIZE = 56;
    final int PARITY_SIZE = 64;
    final int HALF_SIZE = 28;

    BitSet key = new BitSet(PARITY_SIZE);
    BitSet[] subkey = new BitSet[17];

    final int PERMUTATION_CHART1[] = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23,15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};

    final int PERMUTATION_CHART2[] = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32};

    public void addKey(String k) {
        for(int i = 0; i < k.length(); i++) {
            if(k.charAt(i) == '1') {
                key.set(i);
            }
        }
    }

    public void permutateKeys() {
         initialPermutation();
    }

    private void initialPermutation() {
        BitSet permKey = new BitSet(PREPARITY_SIZE);
        for(int i = 0; i < 56; i++) {
            permKey.set(i,key.get(PERMUTATION_CHART1[i]-1));
        }

        splitKey(permKey);
    }

    private void splitKey(BitSet permKey) {
        BitSet halfKey1 = new BitSet(HALF_SIZE);
        BitSet halfKey2 = new BitSet(HALF_SIZE);
        for(int i = 0; i < HALF_SIZE; i++) {
            halfKey1.set(i,permKey.get(i));
        }
            for (int i = HALF_SIZE; i < PARITY_SIZE; i++) {
                halfKey2.set(i - HALF_SIZE, permKey.get(i));
            }

        shiftKey(halfKey1, halfKey2);
    }

    private void shiftKey(BitSet hk1, BitSet hk2) {

        for(int i = 1; i <= 16; i++) {

            if(i == 1 || i == 2 || i == 9 || i == 16) {
                boolean temp;

                temp = hk1.get(0);
                for (int j = 0; j < 28; j++) {
                    hk1.set(j, hk1.get(j + 1));
                }
                hk1.set(27, temp);

                temp = hk2.get(0);
                for (int j = 0; j < 28; j++) {
                    hk2.set(j, hk2.get(j + 1));
                }
                hk2.set(27, temp);

            } else {
                boolean temp1, temp2;

                temp1 = hk1.get(0);
                temp2 = hk1.get(1);
                for (int j = 0; j < 27; j++) {
                    hk1.set(j, hk1.get(j + 2));
                }
                hk1.set(26, temp1);
                hk1.set(27, temp2);

                temp1 = hk2.get(0);
                temp2 = hk2.get(1);
                for (int j = 0; j < 27; j++) {
                    hk2.set(j, hk2.get(j + 2));
                }
                hk2.set(26, temp1);
                hk2.set(27, temp2);
            }
            endPermutation(hk1, hk2, i);
        }
    }

    private void endPermutation(BitSet htk1, BitSet htk2, int j) {
        BitSet tempKey = new BitSet(PARITY_SIZE);
        BitSet tempSubKey = new BitSet(48);

        for(int i = 0; i < HALF_SIZE; i++) {
            tempKey.set(i,htk1.get(i));
        }

        for(int i = HALF_SIZE; i < PARITY_SIZE; i++) {
            tempKey.set(i,htk2.get(i - HALF_SIZE));
        }

        for(int i = 0; i < 48; i++) {
            tempSubKey.set(i,tempKey.get(PERMUTATION_CHART2[i] - 1));
        }

        storeSubKey(tempSubKey, j);
    }

    private void storeSubKey(BitSet tsk, int j) {
        subkey[j] = tsk;
    }

    public void keyDebugPrint() {
        for(int i = 1; i < 17; i++) {
            System.out.print("K" + i + " ");
            if(i < 10) {
               System.out.print(" ");
            }
            for(int j = 0; j < 48; j++) {
                if(j == 6 || j == 12 || j == 18 || j == 24 || j == 30 || j == 36 || j == 42) {
                    System.out.print(" ");
                }
                System.out.print(subkey[i].get(j) ? 1 : 0);
            }
            System.out.println();
        }
    }
}
