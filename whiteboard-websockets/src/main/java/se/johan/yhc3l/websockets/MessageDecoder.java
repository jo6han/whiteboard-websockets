package se.johan.yhc3l.websockets;

import java.awt.List;
import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Todo> {

	@Override
	public Todo decode(String jsonMessage) throws DecodeException {
				
		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
		//System.out.println(jsonObject.toString());
		
		Todo todo = new Todo();
		todo.setId(jsonObject.getString("id"));
		todo.setDesc(jsonObject.getString("desc"));
		todo.setListinfo(getArrayFromJSONObject(jsonObject));
		todo.setColor(jsonObject.getString("color"));
		todo.setDone(jsonObject.getBoolean("done"));
		todo.setAction(jsonObject.getString("action"));
		System.out.println(todo.isDone());
		
		return todo;

	}

	@Override
	public boolean willDecode(String jsonMessage) {
		try {
			// Check if incoming message is valid JSON
			Json.createReader(new StringReader(jsonMessage)).readObject();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init(EndpointConfig ec) {
		System.out.println("MessageDecoder -init method called");
	}

	@Override
	public void destroy() {
		System.out.println("MessageDecoder - destroy method called");
	}
	
	public ArrayList<String> getArrayFromJSONObject (JsonObject json){
		ArrayList<String> list = new ArrayList<String>();
		JsonArray jsonArray = json.getJsonArray("list");
		for (int i = 0; i < jsonArray.size(); i++) {
			list.add(jsonArray.getString(i));
		}
//		System.out.println("LISTAN: "+list);
//		System.out.println("LISTAN DIREKT: "+jsonArray.toString());
		return list;
		
	}

}
