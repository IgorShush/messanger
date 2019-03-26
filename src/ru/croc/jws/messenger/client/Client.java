package ru.croc.jws.messenger.client;

import ru.croc.jws.messenger.common.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private final String host;
	private final int port;

	public Client() {
		this("localhost", 7777);
	}

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void sendMessage(Message message) throws IOException {
		if (message == null)
			throw new IllegalArgumentException("message is null");

		// send message
		try (Socket socket = new Socket(host, port)) {
			OutputStream out = socket.getOutputStream();
			try (Writer w = new OutputStreamWriter(out)) {
				w.write("0");
				w.write("\n");
				w.write(message.getUser().getName());
				w.write("\n");
				String messageText = message.getText().replaceAll("\n", " ");
				w.write(messageText);
				w.write("\n");
			}
		}
	}
}
