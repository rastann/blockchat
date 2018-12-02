package com.blockchat;

import java.util.Date;

public class Message {
    private String info;
    private String hash;
    private String previousHash;
    private long timeStamp;
    private String merkleRoot;
    private int nonce;
    private int difficulty = 5;

    public Message(String info, String previousHash) {
        this.info = info;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public void mineMessage(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Message Mined!!! : " + hash);
    }

    public String calculateHash() {
        return ChatUtils.applySha256(info + previousHash + Long.toString(timeStamp) + nonce + merkleRoot);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
