package main;

import java.util.BitSet;

public class Message_Operation {

    BitSet plainMessage = new BitSet(64);
    BitSet[] keys;

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

    final int[] FUNCTION_PERMUTATION =
            {
                    16, 7, 20, 21,
                    29, 12, 28, 17,
                    1, 15, 23, 26,
                    5, 18, 31, 10,
                    2, 8, 24, 14,
                    32, 27, 3, 9,
                    19, 13, 30, 6,
                    22, 11, 4, 25
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
    public Message_Operation(BitSet[] k) {
        keys = k;
    }

    public void addMessage(String m, char mode) {
        for(int i = 0; i < m.length(); i++) {
            if(m.charAt(i) == '1') {
                plainMessage.set(i);
            }
        }
        initialPermutateMessage(plainMessage, mode);
    }

    private void initialPermutateMessage(BitSet plainMessage, char mode) {
        BitSet IP = new BitSet(64);
        for(int i = 0; i < 64; i++) {
            IP.set(i, plainMessage.get(INITIAL_PERMUTATION[i] - 1));
        }

        System.out.println();
        System.out.print("IM: ");
        for(int j = 0; j < 64; j++) {
            if(j == 4 || j == 8 || j == 12 || j == 16 || j == 20 || j == 24 || j == 28 || j == 32 || j == 36 || j == 40 || j == 44 || j == 48 || j == 52 || j == 56 || j == 60) {
                System.out.print(" ");
            }
            System.out.print(plainMessage.get(j) ? 1 : 0);
        }
        System.out.println();
        System.out.print("IP: ");
        for(int j = 0; j < 64; j++) {
            if(j == 4 || j == 8 || j == 12 || j == 16 || j == 20 || j == 24 || j == 28 || j == 32 || j == 36 || j == 40 || j == 44 || j == 48 || j == 52 || j == 56 || j == 60) {
                System.out.print(" ");
            }
            System.out.print(IP.get(j) ? 1 : 0);
        }
        System.out.println();
        System.out.println();

        if(mode == 'e') {
            encryptMessage(IP);
        } else if (mode == 'd') {
            decryptMessage(IP);
        }
    }

    private void encryptMessage(BitSet permutatedMessage) {
        BitSet left = new BitSet(32);
        BitSet originalRight;
        BitSet right = new BitSet(32);
        BitSet expandRight = new BitSet(48);
        BitSet xorRight;
        BitSet[] sBoxIn = new BitSet[8];
        BitSet sboxOutput = new BitSet(32);
        BitSet f = new BitSet(32);
        BitSet r0l0 = new BitSet(64);
        BitSet encrypted = new BitSet(64);

        System.out.println("-------------------------------");
        System.out.println("MESSAGE ENCRYPTION");

        for(int i = 0; i < 32; i++) {
            left.set(i, permutatedMessage.get(i));
        }

        for(int i = 32; i < 64; i++) {
            right.set(i - 32, permutatedMessage.get(i));
        }


        for(int round = 0; round < 16; round++) {
            System.out.println("ROUND " + (round + 1));
            System.out.print("Left Half: ");
            for(int i = 0; i < 32; i++) {
                if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }

            System.out.println();

            System.out.print("Right Half: ");
            for(int i = 0; i < 32; i++) {
                if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(right.get(i) ? 1 : 0);
            }

            originalRight = right;
            for (int i = 0; i < 48; i++) {
                expandRight.set(i, right.get(EXPANSION_BOX[i] - 1));
            }

            xorRight = expandRight;

            xorRight.xor(keys[round + 1]);

            System.out.println();
            System.out.print("XOR Right with Key: ");
            for (int i = 0; i < 48; i++) {
                if (i == 6 || i == 12 || i == 18 || i == 24 || i == 30 || i == 36 || i == 42) {
                    System.out.print(" ");
                }
                System.out.print(xorRight.get(i) ? 1 : 0);
            }
            System.out.println();

            BitSet temp = new BitSet(6);
            BitSet temp2 = new BitSet(6);
            BitSet temp3 = new BitSet(6);
            BitSet temp4 = new BitSet(6);
            BitSet temp5 = new BitSet(6);
            BitSet temp6 = new BitSet(6);
            BitSet temp7 = new BitSet(6);
            BitSet temp8 = new BitSet(6);
            int cnt = 0;

            for (int i = 0; i < 6; i++) {
                temp.set(i, xorRight.get(i));
            }
            sBoxIn[cnt] = temp;
            cnt++;

            for (int i = 6; i < 12; i++) {
                temp2.set(i - 6, xorRight.get(i));
            }
            sBoxIn[cnt] = temp2;
            cnt++;

            for (int i = 12; i < 18; i++) {
                temp3.set(i - 12, xorRight.get(i));
            }
            sBoxIn[cnt] = temp3;
            cnt++;

            for (int i = 18; i < 24; i++) {
                temp4.set(i - 18, xorRight.get(i));
            }
            sBoxIn[cnt] = temp4;
            cnt++;

            for (int i = 24; i < 30; i++) {
                temp5.set(i - 24, xorRight.get(i));
            }
            sBoxIn[cnt] = temp5;
            cnt++;

            for (int i = 30; i < 36; i++) {
                temp6.set(i - 30, xorRight.get(i));
            }
            sBoxIn[cnt] = temp6;
            cnt++;

            for (int i = 36; i < 42; i++) {
                temp7.set(i - 36, xorRight.get(i));
            }
            sBoxIn[cnt] = temp7;
            cnt++;

            for (int i = 42; i < 48; i++) {
                temp8.set(i - 42, xorRight.get(i));
            }
            sBoxIn[cnt] = temp8;

            int k = 0;
            int l = 0;
            for (int i = 0; i < 8; i++) {
                BitSet t;
                t = sBoxTransform(sBoxIn[i], i + 1);
                for (int j = k; j < k + 4; j++) {
                    sboxOutput.set(j, t.get(l));
                    l++;
                }
                l = 0;
                k = k + 4;
            }
            System.out.print("Output of S Blocks: ");
            for (int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(sboxOutput.get(i) ? 1 : 0);
            }
            System.out.println();
            System.out.print("Function: ");
            for (int i = 0; i < 32; i++) {
                f.set(i, sboxOutput.get(FUNCTION_PERMUTATION[i] - 1));
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(f.get(i) ? 1 : 0);
            }
            left.xor(f);

            System.out.println();
            System.out.print("XOR Left with Function: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }
            System.out.println();

            right = left;
            left = originalRight;

            System.out.println();
            System.out.print("Left After Operations: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }

            System.out.println();
            System.out.print("Right After Operations: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(right.get(i) ? 1 : 0);
            }
            System.out.println();
            System.out.println("------------------------------------------");
        }
        for(int i = 0; i < 32; i++) {
            r0l0.set(i, right.get(i));
        }
        for(int i = 32; i < 64; i++) {
            r0l0.set(i, left.get(i - 32));
        }

        System.out.println();
        System.out.print("R0L0 Output: ");
        for(int i = 0; i < 64; i++) {
            if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48 || i == 52 || i == 56 || i == 60) {
                System.out.print(" ");
            }
            System.out.print(r0l0.get(i) ? 1 : 0);
        }

        System.out.println();

        for(int i = 0; i < 64; i++) {
            encrypted.set(i, r0l0.get(FINAL_PERMUTATION[i] - 1));
        }
        System.out.print("Encrypted Message: ");
        for(int i = 0; i < 64; i++) {
            if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48 || i == 52 || i == 56 || i == 60) {
                System.out.print(" ");
            }
            System.out.print(encrypted.get(i) ? 1 : 0);
        }
        System.out.println();
        System.out.println();
    }

    private void decryptMessage(BitSet permutatedMessage) {
        BitSet left = new BitSet(32);
        BitSet originalRight;
        BitSet right = new BitSet(32);
        BitSet expandRight = new BitSet(48);
        BitSet xorRight;
        BitSet[] sBoxIn = new BitSet[8];
        BitSet sboxOutput = new BitSet(32);
        BitSet f = new BitSet(32);
        BitSet r0l0 = new BitSet(64);
        BitSet encrypted = new BitSet(64);

        System.out.println("-------------------------------");
        System.out.println("MESSAGE DECRYPTION");

        for(int i = 0; i < 32; i++) {
            left.set(i, permutatedMessage.get(i));
        }

        for(int i = 32; i < 64; i++) {
            right.set(i - 32, permutatedMessage.get(i));
        }

        int roundcnt = 1;
        for(int round = 16; round > 0; round--) {
            System.out.println("ROUND " + (roundcnt));
            roundcnt++;
            System.out.print("Left Half: ");
            for(int i = 0; i < 32; i++) {
                if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }

            System.out.println();

            System.out.print("Right Half: ");
            for(int i = 0; i < 32; i++) {
                if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(right.get(i) ? 1 : 0);
            }

            originalRight = right;
            for (int i = 0; i < 48; i++) {
                expandRight.set(i, right.get(EXPANSION_BOX[i] - 1));
            }

            xorRight = expandRight;

            xorRight.xor(keys[round]);

            System.out.println();
            System.out.print("XOR Right with Key: ");
            for (int i = 0; i < 48; i++) {
                if (i == 6 || i == 12 || i == 18 || i == 24 || i == 30 || i == 36 || i == 42) {
                    System.out.print(" ");
                }
                System.out.print(xorRight.get(i) ? 1 : 0);
            }
            System.out.println();

            BitSet dtemp = new BitSet(6);
            BitSet dtemp2 = new BitSet(6);
            BitSet dtemp3 = new BitSet(6);
            BitSet dtemp4 = new BitSet(6);
            BitSet dtemp5 = new BitSet(6);
            BitSet dtemp6 = new BitSet(6);
            BitSet dtemp7 = new BitSet(6);
            BitSet dtemp8 = new BitSet(6);
            int cnt = 0;

            for (int i = 0; i < 6; i++) {
                dtemp.set(i, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp;
            cnt++;

            for (int i = 6; i < 12; i++) {
                dtemp2.set(i - 6, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp2;
            cnt++;

            for (int i = 12; i < 18; i++) {
                dtemp3.set(i - 12, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp3;
            cnt++;

            for (int i = 18; i < 24; i++) {
                dtemp4.set(i - 18, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp4;
            cnt++;

            for (int i = 24; i < 30; i++) {
                dtemp5.set(i - 24, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp5;
            cnt++;

            for (int i = 30; i < 36; i++) {
                dtemp6.set(i - 30, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp6;
            cnt++;

            for (int i = 36; i < 42; i++) {
                dtemp7.set(i - 36, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp7;
            cnt++;

            for (int i = 42; i < 48; i++) {
                dtemp8.set(i - 42, xorRight.get(i));
            }
            sBoxIn[cnt] = dtemp8;

            int k = 0;
            int l = 0;
            for (int i = 0; i < 8; i++) {
                BitSet t;
                t = sBoxTransform(sBoxIn[i], i + 1);
                for (int j = k; j < k + 4; j++) {
                    sboxOutput.set(j, t.get(l));
                    l++;
                }
                l = 0;
                k = k + 4;
            }
            System.out.print("Output of S Blocks: ");
            for (int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(sboxOutput.get(i) ? 1 : 0);
            }
            System.out.println();
            System.out.print("Function: ");
            for (int i = 0; i < 32; i++) {
                f.set(i, sboxOutput.get(FUNCTION_PERMUTATION[i] - 1));
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(f.get(i) ? 1 : 0);
            }
            left.xor(f);

            System.out.println();
            System.out.print("XOR Left with Function: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }
            System.out.println();

            right = left;
            left = originalRight;

            System.out.println();
            System.out.print("Left After Operations: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(left.get(i) ? 1 : 0);
            }

            System.out.println();
            System.out.print("Right After Operations: ");
            for(int i = 0; i < 32; i++) {
                if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28) {
                    System.out.print(" ");
                }
                System.out.print(right.get(i) ? 1 : 0);
            }
            System.out.println();
            System.out.println("------------------------------------------");
        }
        for(int i = 0; i < 32; i++) {
            r0l0.set(i, right.get(i));
        }
        for(int i = 32; i < 64; i++) {
            r0l0.set(i, left.get(i - 32));
        }

        System.out.println();
        System.out.print("R0L0 Output: ");
        for(int i = 0; i < 64; i++) {
            if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48 || i == 52 || i == 56 || i == 60) {
                System.out.print(" ");
            }
            System.out.print(r0l0.get(i) ? 1 : 0);
        }

        System.out.println();

        for(int i = 0; i < 64; i++) {
            encrypted.set(i, r0l0.get(FINAL_PERMUTATION[i] - 1));
        }
        System.out.print("Decrypted Message: ");
        for(int i = 0; i < 64; i++) {
            if(i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48 || i == 52 || i == 56 || i == 60) {
                System.out.print(" ");
            }
            System.out.print(encrypted.get(i) ? 1 : 0);
        }
        System.out.println();
        System.out.println();
    }

    private BitSet sBoxTransform(BitSet input, int bN) {
        SBox_Operation transform = new SBox_Operation();
        BitSet sBoxOutput;

        sBoxOutput = transform.retrieveOutput(input, bN);
        return sBoxOutput;
    }
}
