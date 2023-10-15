import java.util.ArrayList;

public class FixedXOR {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> hex1 = HexToBase64.getByteArrForHexString("1c0111001f010100061a024b53535009181c");
        ArrayList<Integer> hex2 = HexToBase64.getByteArrForHexString("686974207468652062756c6c277320657965");

//        fixedXOR(hex1, hex2).forEach(k -> {
//            System.out.print((char) k.intValue());
//        });

        System.out.println(getHexStrForByteArr(fixedXOR(hex1, hex2)));
    }

    // returns byte arrays of xor'ed values
    public static ArrayList<Integer> fixedXOR(ArrayList<Integer> hex1, ArrayList<Integer> hex2) throws Exception {
        if(hex1.size() != hex2.size()) throw new Exception("Unequal hex buffers");
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < hex1.size(); i++) {
            res.add((hex1.get(i) ^ hex2.get(i)) & 0xff);
        }
        return res;
    }

    // get hex string for a byte array
    public static String getHexStrForByteArr(ArrayList<Integer> arr) throws Exception {
        char[] hexMap = "0123456789abcdef".toCharArray();
        char[] c = new char[ arr.size() * 2];
        for(int i = 0; i < arr.size(); i++) {
            int byte1 = arr.get(i);
           c[2 * i] = hexMap[((byte1 & 0xf0) >> 4)];
           c[2 * i + 1] =  hexMap[(byte1 & 0x0f)];
        }
        return new String(c);
    }

}
