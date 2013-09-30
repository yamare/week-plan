package no.hig.imt3662.weekplan;

import org.json.JSONException;
import org.json.JSONObject;

public class Items extends JSONObject {
	public Items() throws JSONException {
		
		JSONObject item1 = new JSONObject();
		item1.put("due_date", "2013-10-03 23:00");
		item1.put("content", "Project deadline");
			
		JSONObject item2 = new JSONObject();
		item2.put("due_date", "2013-10-09 09:00");
		item2.put("content", "Finish reading for the exam");
			
		JSONObject items = new JSONObject();
		items.put("item1", item1);
		items.put("item2", item2);
				
	}
	
}