package main;

import javax.swing.*;
import java.awt.*;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BitSet message, key;
        int option = 0;
        int rounds = 0;

        message = new BitSet(8);

        Key_Operation test = new Key_Operation();
        SBox_Operation test2 = new SBox_Operation();
        Message_Operation test3 = new Message_Operation();



        test.addKey("0001001100110100010101110111100110011011101111001101111111110001");
        test.permutateKeys();
        test.keyDebugPrint();

        test3.addMessage( "0000000100100011010001010110011110001001101010111100110111101111");
//        do {
//
//            switch(getChoice()) {
//                case 1:
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//                case 4:
//                    break;
//                case 5:
//                    break;
//            }
//
//        } while(option !=5);
//
//        for(int i = 0; i<8; i++) {
//            int temp;
//            temp = message.get(i) ? 1: 0;
//            System.out.print(temp + " ");
//        }
    }

    public static int getChoice() {
        Scanner in = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1) Use Permutations");
        System.out.println("2) No Permutation");
        System.out.println("3) Choose Number of Rounds");
        System.out.println("4) Run Encryption");
        System.out.println("5) Run Decryption");
        System.out.println("6) Exit");
        return in.nextInt();
    }
}
