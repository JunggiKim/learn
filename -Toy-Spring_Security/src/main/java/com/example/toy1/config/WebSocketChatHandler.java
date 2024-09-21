package com.example.toy1.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebSocketChatHandler extends TextWebSocketHandler  {

	private final ObjectMapper objectMapper;

	// private final ChatService chatService;


	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		String payload = message.getPayload().toString();

	}




}
