package cn.milai.jvmdemo.util;

import java.util.ArrayList;
import java.util.List;

public class MessageHolder {

	private static List<String> messages = new ArrayList<>();

	public static void addMessage(String message) {
		messages.add(message);
	}

	public static void clear() {
		messages.clear();
	}
	
	public static List<String> getMessages() {
		return messages;
	}

}
