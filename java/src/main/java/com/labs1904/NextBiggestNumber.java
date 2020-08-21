package com.labs1904;

import java.util.PriorityQueue;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        Integer nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
    }

    public static int getNextBiggestNumber(Integer i) {
        // Store the original value to compare with next biggest #
        int originalNum = i;
        int nextBiggestNum = originalNum;
        // The digit that will replace a more significant digit `digitToDemote`, if a larger # exists
        int digitToSwap = -1;
        // The digit that will be replaced by `digitToSwap`
        int digitToDemote = originalNum % 10;
        // Min heap to sort the digits less significant than `digitToDemote`
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        // Find digitToDemote
        int numberOfDigits = (int) Math.log10(originalNum) + 1;
        // The digits that are more significant than digitToDemote
        int prefix = originalNum;
        do {
            int currentDigit = prefix % 10;
            minHeap.add(currentDigit);
            prefix /= 10;

            boolean foundDigitToDemote = currentDigit < digitToDemote;
            digitToDemote = currentDigit;
            if (foundDigitToDemote) break;
        }
        while (prefix != 0);

        // Find digitToSwap
        for (int index = 0; index < numberOfDigits; index++) {
            int currentDigit = nextBiggestNum % 10;
            boolean foundDigitToSwap = currentDigit > digitToDemote && currentDigit > digitToSwap;
            digitToSwap = currentDigit;
            if (foundDigitToSwap) break;
            nextBiggestNum /= 10;
        }

        // Build next largest number
        nextBiggestNum = prefix * 10 + digitToSwap;
        while (!minHeap.isEmpty()) {
            int nextDigit = minHeap.remove();
            if (nextDigit == digitToSwap) {
                digitToSwap = -1;
                continue;
            }
            nextBiggestNum = nextBiggestNum * 10 + nextDigit;
        }

        return nextBiggestNum <= originalNum ? -1 : nextBiggestNum;
    }


}
