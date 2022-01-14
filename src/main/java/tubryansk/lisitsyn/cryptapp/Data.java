package tubryansk.lisitsyn.cryptapp;

import java.nio.charset.StandardCharsets;

public class Data {
    private byte[] byteKey;
    private byte[] byteText;
    private byte[] byteCryptText;
    private byte[] byteDecryptText;
    private int r;

    public byte[] getByteKey() {
        return byteKey;
    }

    public void setByteKey(byte[] key) {
        this.byteKey = key;
    }

    public void setDefaultKey(){
        byteKey = "1234567890".getBytes(StandardCharsets.UTF_8);
    }

    public byte[] getByteText() {
        return byteText;
    }

    public void setByteText(byte[] byteText) {
        this.byteText = byteText;
    }

    public byte[] getByteCryptText() {
        return byteCryptText;
    }

    public void setByteCryptText(byte[] byteCryptText) {
        this.byteCryptText = byteCryptText;
    }

    public byte[] getByteDecryptText() {
        return byteDecryptText;
    }

    public void setByteDecryptText(byte[] byteDecryptText) {
        this.byteDecryptText = byteDecryptText;
    }

    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
