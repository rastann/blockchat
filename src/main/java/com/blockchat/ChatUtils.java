package com.blockchat;

import java.security.MessageDigest;
import java.util.ArrayList;

public class ChatUtils {

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isConversationValid(int difficulty, ArrayList<Message> conversation) {
        Message currentMessage;
        Message previousMessage;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i=1; i < conversation.size(); i++) {
            currentMessage = conversation.get(i);
            previousMessage = conversation.get(i-1);
            if(!currentMessage.getHash().equals(currentMessage.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousMessage.getHash().equals(currentMessage.getPreviousHash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentMessage.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
