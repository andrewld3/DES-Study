package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String key = null;
        int option = 0;
        do {

            switch(getChoice()) {
                case 1:
                    key = getKey();
                    break;
                case 2:
                    messageEncoder(key);
                    break;
                case 3:
                    messageDecoder(key);
                    break;
                case 4:
                    option = 4;
                    break;
            }

        } while(option !=4);
    }

    public static int getChoice() {
        Scanner in = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1) Enter Key");
        System.out.println("2) Encrypt a Message");
        System.out.println("3) Decrypt a Message");
        System.out.println("4) Exit");
        System.out.print("Option Choice: ");
        return in.nextInt();
    }

    public static String getKey() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Key (64 digits, binary only: ");
        return in.next();
    }

    public static String getMessage() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Message: ");
        return in.nextLine();
    }

    public static void messageEncoder(String key) {
        Key_Operation encode = new Key_Operation();
        encode.addKey(key);
        Message_Operation encodeMessage = new Message_Operation(encode.subkey);
        encodeMessage.addMessage(getMessage(), 'e');
    }

    public static void messageDecoder(String key) {
        Key_Operation decode = new Key_Operation();
        decode.addKey(key);
        Message_Operation decodeMessage = new Message_Operation(decode.subkey);
        decodeMessage.addMessage(getMessage(), 'd');
    }
}
