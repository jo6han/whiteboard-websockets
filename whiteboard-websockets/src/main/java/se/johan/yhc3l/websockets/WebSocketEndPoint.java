package se.johan.yhc3l.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", encoders = { MessageEncoder.class }, decoders = { MessageDecoder.class })
public class WebSocketEndPoint {
	

		private static int count = 0;
		private static final Set<Todo> syncList = Collections.synchronizedSet(new HashSet<Todo>());
		private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());


		@OnMessage
		public void onMessage(Todo message, Session session) throws IOException, EncodeException {

			// Echo the received message back to the client
			Todo response = getItem(message);
			
			if (message.getId().equals("nyId") && message.getAction().equals("add")){
				response.setId(newItem());
			}
			
			
			for (Iterator<Todo> iter = syncList.iterator(); iter.hasNext(); ) {
			    Todo element = iter.next();
			    
			    if(response.getId().equals(element.getId()) && response.getAction().equals("remove") && response.isDone() == true){
			    	System.out.println("Desc:" + element.getDesc() + " will be removed.");
			    	iter.remove();
			    }
			    
			    if (message.getAction().equals("update") && response.isDone() == false){
					response.setId(message.getId());					    
					    if(response.getId().equals(element.getId())){
					    	System.out.println("Desc:" + element.getDesc() + " will be updated to" + response.getDesc());
					    	iter.remove();
					    }
				}
			}
			
			if(!response.isDone()){
				syncList.add(response);
			}
			
			for(Session s: session.getOpenSessions()){
				System.out.println("Session user: " + s);
					try { 
						s.getBasicRemote().sendObject(response); 
					} catch (IOException | EncodeException ex) { 
						sessions.remove(s); 
					} 
			}
			
		}

		@OnOpen
		public void onOpen(Session session) throws IOException, EncodeException {
			sessions.add(session); 
		      for (Todo todo : syncList) { 
		         session.getBasicRemote().sendObject(todo); 
		      } 
						
			System.out.println("Client connected");
		}

		@OnClose
		public void onClose(Session session) {
			sessions.remove(session); 
			System.out.println("Connection closed");
		}

		public String newItem (){
			count++;
			return Integer.toString(count);
			}
		
		public Todo getItem (Todo message){
			Todo response = new Todo();
			response.setId(message.getId());
			response.setDesc(message.getDesc());
			response.setColor(message.getColor());
			response.setListinfo(message.getListinfo());
			response.setDone(message.isDone());
			response.setAction(message.getAction());
			return response;
			
		}
	
}
