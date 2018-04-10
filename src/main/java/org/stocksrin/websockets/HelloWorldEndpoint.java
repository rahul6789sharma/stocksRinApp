package org.stocksrin.websockets;

import java.util.Date;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hello")
public class HelloWorldEndpoint {

	@OnMessage
	public String hello(String message) {
		System.out.println("Received : " + message);
		return message;
	}

	@OnOpen
	public void myOnOpen(Session session) {
		try{
		System.out.println("WebSocket opened: " + session.getId());

		Map<String, BNiftyTradeData> map = BNiftyTradeData.getData();
		

		String lastkey = null;
		for (Map.Entry<String, BNiftyTradeData> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			lastkey = entry.getKey();
			session.getBasicRemote().sendObject(entry.getValue().toString());
		}

		BNiftyTradeData bNiftyTradeData = new BNiftyTradeData();
		int i=1;
		while(true){
			Thread.sleep(5000);
			BNiftyTradeData s=bNiftyTradeData.getSampleData(i);
			map.put(new Date().toString(), s);
			session.getBasicRemote().sendObject(s.toString());
			i++;
		}
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	@OnClose
	public void myOnClose(CloseReason reason) {
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
	}

}