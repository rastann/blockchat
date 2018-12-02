package com.blockchat;

import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class BlockchatApplication {
	private static ArrayList<Message> conversation = new ArrayList<>();
	private static int difficulty = 5;

	public static void main(String[] args) {

		conversation.add(new Message("hi", "0"));
		System.out.println("Trying to mine message 1 ...");
		conversation.get(0).mineMessage(difficulty);

		conversation.add(new Message("hii", conversation.get(conversation.size()-1).getHash()));
		System.out.println("Trying to mine message 2 ...");
		conversation.get(1).mineMessage(difficulty);

		displayConversation();

		if(ChatUtils.isConversationValid(difficulty, conversation)) {
			System.out.println("Conversation chain is valid");
		} else {
			System.out.println("Conversation chain is not valid");
		}
	}

	public static void displayConversation() {
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(conversation);
		System.out.println(blockchainJson);

	}
}
