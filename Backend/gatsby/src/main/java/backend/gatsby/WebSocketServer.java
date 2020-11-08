package backend.gatsby;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {/*
	private static Map<Session, Integer> sessionIdMap = new Hashtable<>();
	private static Map<Integer, Session> idSessionMap = new Hashtable<>();
	
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	@OnOpen
	public void onOpen(Session session, @PathParam("id") Integer id) throws IOException{
		logger.info("Entered into Open");
		
		sessionIdMap.put(session, id);
		idSessionMap.put(id, session);
		
		String message = "User: " + id + " has joined the chat";
		
	}
	
	@OnMessage
	public void onMessage(Session session, String message, int destUserId) throws IOException{
		logger.info("Entered new message: " + message);
		int id = sessionIdMap.get(session);
		
		sendMessageToParticularUser(destUserId, message);
		sendMessageToParticularUser(id, message);
		
		
	}

	private void sendMessageToParticularUser(int id, String message){
		try {
    		  idSessionMap.get(id).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
          e.printStackTrace();
        }
    }
	
	@OnClose
	public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");

    	int id = sessionIdMap.get(session);
    	sessionIdMap.remove(session);
    	idSessionMap.remove(id);

    	String message= id + " disconnected";
        broadcast(message);
    }
	
	private void broadcast(String message){
		  sessionIdMap.forEach((session, username)->{
			try {
						session.getBasicRemote().sendText(message);
			} catch (IOException e) {
						logger.info("Exception: " + e.getMessage().toString());
							e.printStackTrace();
			}
		});

}



*/
}
