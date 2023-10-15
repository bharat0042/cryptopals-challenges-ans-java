import java.util.ArrayList;

public class HexToBase64 {
    public static void main(String[] args) {
        String input = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        getBase64Encode(input).forEach(System.out::print);
    }

    public static  ArrayList<Character> getBase64Encode(String inputHexStr) {
        boolean isSinglePad = false;
        boolean isDoublePad = false;
        ArrayList<Integer> byteArr = getByteArrForHexString(inputHexStr);

        // represents array of 6-bit values as int
        ArrayList<Integer> asciiIntArr = new ArrayList<Integer>();

        // base64 encoded 6-bit char arrays
        ArrayList<Character> asciiArr = new ArrayList<Character>();

        // padding on right if size of byte array if not a multiple of 3 to make it
        if(byteArr.size() % 3 == 1) {
            byteArr.add(0);
            byteArr.add(0);
            isDoublePad = true;
        } else if(byteArr.size() % 3 == 2) {
            byteArr.add(0);
            isSinglePad = true;
        }
        for(int k = 0; k < byteArr.size(); k = k + 3) {
            int d0 = byteArr.get(k);
            int d1 = byteArr.get(k + 1);
            int d2 = byteArr.get(k + 2);

            // after masking correct bits move it to right so as these 6-bits can be stored as int
            asciiIntArr.add( (d0  & 0xfc) >> 2);
            asciiIntArr.add( ((d0 & 0x03) << 4)  + ((d1 & 0xf0) >> 4) );
            asciiIntArr.add( ( (d1 & 0x0f) << 2)  + ((d2 & 0xc0) >> 6));
            asciiIntArr.add( (d2 & 0x3f));

        }

        String indexTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        for(int m = 0; m < asciiIntArr.size(); m++) {
            asciiArr.add(indexTable.charAt(asciiIntArr.get(m)));
        }

        if(isSinglePad) {
            asciiArr.remove(asciiArr.size() - 1);
            asciiArr.add('=');
        } else if (isDoublePad) {
            asciiArr.remove(asciiArr.size() - 1);
            asciiArr.remove(asciiArr.size() - 1);
            asciiArr.add('=');
            asciiArr.add('=');
        }

        return asciiArr;
    }

    // using int instead of byte cause java does not support unsigned bytes
    public static ArrayList<Integer> getByteArrForHexString(String inputStr) {
        ArrayList<Integer> byteArr = new ArrayList<Integer>();
        for(int i = 0; i < inputStr.length(); i = i + 2) {
            String singleByteHex = inputStr.substring(i, i + 2);
            int d0 =  Character.getNumericValue(singleByteHex.charAt(0));
            int d1 =  Character.getNumericValue(singleByteHex.charAt(1));
            byteArr.add(((16 * d0) + d1));
        }
        return byteArr;
    }
}