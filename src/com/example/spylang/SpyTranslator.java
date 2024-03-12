package com.example.spylang;

import java.util.Scanner;


public class SpyTranslator {

    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter your Spy Code:");
        String code = userInput.nextLine();

        // Printing entered string in Morse code
        System.out.println("Entered Spy Code in Morse code:");
        printInMorseCode(code);

        SpyParser parser = new SpyParser(code);
        parser.parse();

        System.out.println("Valid Spy Code!");
    }

    // Method to print a string in Morse code
    private static void printInMorseCode(String code) {
        // Morse code mapping for characters
        String[] morseCodeMapping = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", // A-I
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", // J-R
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", // S-Z
                "-----", ".----", "..---", "...--", "....-", ".....", // 0-5
                "-....", "--...", "---..", "----.", // 6-9
                ".-.-.-" // Period
        };

        StringBuilder morseCodeOutput = new StringBuilder();
        for (char c : code.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                morseCodeOutput.append(morseCodeMapping[c - 'A']).append(" ");
            } else if (Character.isDigit(c)) {
                morseCodeOutput.append(morseCodeMapping[c - '0' + 25]).append(" ");
            } else if (c == '.') {
                morseCodeOutput.append(morseCodeMapping[36]).append(" ");
            } else if (c == ' ') {
                morseCodeOutput.append("/ ");
            }
        }
        System.out.println(morseCodeOutput.toString().trim());
    }
}


