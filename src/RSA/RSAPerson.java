package RSA;

import math.modular.calcul.ModularProcess;
import math.modular.calcul.NotInvertibleNumber;
import math.modular.number.ModularNum;
import java.math.BigInteger;

public class RSAPerson {

    private String name;
    private RSAKey keys;
    private int signature;

    public RSAPerson(String name){
        this.name = name;
        this.keys = new RSAKey();
        this.signature = 0;
    }

    public String getName() {
        return name;
    }
    public int getSignature() {
        return signature;
    }
    public RSAKey getKeys() {
        return keys;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSignature(int signature) {
        this.signature = signature;
    }

    public BigInteger encryptTextTo(String message, RSAPerson personToContact){
        BigInteger messageToEncrypt = new BigInteger(transformCharTextIntoHex(message), 16);
        ModularNum valueToCrypt = new ModularNum(messageToEncrypt, personToContact.getKeys().getPublicKey().getModulo());
        ModularNum cryptedValue = ModularProcess.modPow(valueToCrypt, personToContact.getKeys().getPublicKey().getVal());
        return cryptedValue.getVal();
    }


    public String decrypteText(BigInteger cryptedMessage){
        ModularNum valueToDecrypt = new ModularNum(cryptedMessage, this.getKeys().getPublicKey().getModulo());
        ModularNum decryptedValue = ModularProcess.modPow(valueToDecrypt, this.getKeys().getPrivateKey().getVal());
        BigInteger decryptedTextValue = decryptedValue.getVal();
        return RSAPerson.transformHexToChar(decryptedTextValue);
    }

    private static String transformCharTextIntoHex(String text){
        char[] messageToTransform = text.toCharArray();
        StringBuilder convertedText = new StringBuilder();
        for(char letter : messageToTransform){
            convertedText.append(Integer.toHexString(letter));
        }
        return convertedText.toString();
    }

    private static String transformHexToChar(BigInteger decNum){

        String hexNum = decNum.toString(16);
        StringBuilder hexStringBuilder = new StringBuilder();

        for (int i = 0; i < hexNum.length(); i += 2) {
            String str = hexNum.substring(i, i + 2);
            hexStringBuilder.append((char) Integer.parseInt(str, 16));
        }
        return hexStringBuilder.toString();
    }

    public void refreshKeys() throws NotInvertibleNumber {
        this.keys = new RSAKey();
    }
}
