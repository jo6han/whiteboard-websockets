package se.johan.yhc3l.websockets;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Todo> {

	@Override
	public String encode(Todo message) throws EncodeException {
		
		System.out.println(message.getListinfo().toString());
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("id", message.getId())
				.add("desc", message.getDesc())
				.add("list",  convertFromJSON(message.getListinfo()))
				.add("color", message.getColor())
				.add("done", message.isDone())
				.add("action", message.getAction())
				.build();
		
		
		
		
//		JsonArray array = Json.createArrayBuilder()
//				.add(jsonObject)
//				.build();
		return jsonObject.toString();

	}

	@Override
	public void init(EndpointConfig ec) {
		System.out.println("MessageEncoder - init method called");
	}

	@Override
	public void destroy() {
		System.out.println("MessageEncoder - destroy method called");
	}
	
	public JsonArrayBuilder convertFromJSON(ArrayList<String> arrayList) {
		JsonArrayBuilder test = Json.createArrayBuilder();
//		test.add("test");
		for (int i = 0; i < arrayList.size(); i++) {
			test.add(arrayList.get(i).toString());
			System.out.println(arrayList.get(i));
		}
		return test;
		
		
		
	}

}
