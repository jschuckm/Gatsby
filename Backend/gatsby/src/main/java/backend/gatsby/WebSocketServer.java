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

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();
	
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException{
		logger.info("Entered into Open");
		
		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);
		
		String message = "User: " + username + " has joined the chat";
		broadcast(message);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException{
		logger.info("Entered new message: " + message);
		String username = sessionUsernameMap.get(session);
		
		//sendMessageToParticularUser(destUser, message);
		//sendMessageToParticularUser(username, message);
		if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
    	{
    		String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
    		sendMessageToParticularUser(destUsername, "[DM] " + username + ": " + message);
    		sendMessageToParticularUser(username, "[DM] " + username + ": " + message);
    	}
    	else // Message to whole chat
    	{
	    	broadcast(username + ": " + message);
    	}
	}

	private void sendMessageToParticularUser(String username, String message){
		try {
    		  usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
          e.printStackTrace();
        }
    }
	
	@OnClose
	public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");

    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);

    	String message= username + " disconnected";
        broadcast(message);
    }
	
	private void broadcast(String message){
		  sessionUsernameMap.forEach((session, username)->{
			try {
						session.getBasicRemote().sendText(message);
			} catch (IOException e) {
						logger.info("Exception: " + e.getMessage().toString());
							e.printStackTrace();
			}
		});
	}
	
	@OnError
	public void onError(Session session, Throwable throwable){
	    // Do error handling here
	    logger.info("Entered into Error");
	}

}
