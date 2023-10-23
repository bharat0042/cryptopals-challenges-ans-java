import java.util.*;

public class XorCipher {
    public static void main(String[] args) {
        String input = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        decodedXorCipherSolutions(input);
    }

    public static void decodedXorCipherSolutions(String input) {
        // In English, the space character occurs almost twice as frequently as the top letter (⟨e⟩)
        String letterSortByFrequency = " etaonrishdlfcmugypwbvkjxzqETAONRISHDLFCMUGYPWBVKJXZQ";

        ArrayList<Integer> byteArrofInput = HexToBase64.getByteArrForHexString(input);

        int byteWithHighestFrequency = Collections.max(frequencyMapForByteArr(byteArrofInput).entrySet(), Map.Entry.comparingByValue()).getKey();

        byte[] letterFreqByteArr = letterSortByFrequency.getBytes();
        int[] possibleKeySortedByScoreDes = new int[letterFreqByteArr.length];
        for(int i = 0; i < letterFreqByteArr.length; i++) {
            possibleKeySortedByScoreDes[i] = letterFreqByteArr[i] ^ byteWithHighestFrequency;
        }

        // trying first five keys
        for(int k = 0; k < 4; k++) {
            int key = possibleKeySortedByScoreDes[k];
            byteArrofInput.forEach(data -> {
                System.out.print((char) (key ^ data));
            });
            System.out.println();
        }
    }

    public static Map<Integer, Integer> frequencyMapForByteArr(ArrayList<Integer> byteArr) {
        Map<Integer, Integer> map = new HashMap<>();
        byteArr.forEach(byteData -> {
            map.put(byteData, Collections.frequency(byteArr, byteData));
        });
        return map;
    }
}
