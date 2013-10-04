package no.hig.imt3662.weekplan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ExpandableListActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class DateListActivity extends ExpandableListActivity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor aSensor;
	private Sensor mSensor;
	float[] accelerometerValues = new float[3];   // values for accelerometer
    float[] magneticFieldValues = new float[3];    // values for magnetic
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
     
        List<Map<String, String>> topList = new ArrayList<Map<String, String>>();  
  
        Map<String, String> topMap1 = new HashMap<String, String>();  
        Map<String, String> topMap2 = new HashMap<String, String>();  
        Map<String, String> topMap3 = new HashMap<String, String>();  
        Map<String, String> topMap4 = new HashMap<String, String>();  
        Map<String, String> topMap5 = new HashMap<String, String>();  
        Map<String, String> topMap6 = new HashMap<String, String>();  
        Map<String, String> topMap7 = new HashMap<String, String>();  
        
        
        topMap1.put("weekday", getString(R.string.monday));  
		topMap2.put("weekday", getString(R.string.thursday));  
		topMap3.put("weekday", getString(R.string.wednesday)); 
		topMap4.put("weekday", getString(R.string.thursday)); 
		topMap5.put("weekday", getString(R.string.friday)); 
		topMap6.put("weekday", getString(R.string.saturday));
		topMap7.put("weekday", getString(R.string.sunday)); 
        
        
        topList.add(topMap1);  
        topList.add(topMap2);  
        topList.add(topMap3); 
        topList.add(topMap4); 
        topList.add(topMap5); 
        topList.add(topMap6); 
        topList.add(topMap7);
        
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        JSONObject item4 = new JSONObject();
        JSONObject item5 = new JSONObject();
        JSONObject item6 = new JSONObject();
        JSONObject item7 = new JSONObject();
        
        try{
		item1.put("due_date", "Mon Apr 2013-10-03 23:00");
		item1.put("content", "Project deadline");
		
        }
        catch(Exception ex)
        {
        	
        }
		JSONArray items = new JSONArray();
		items.put(item1);
		items.put(item2);
		items.put(item3);
		items.put(item4);
		items.put(item5);
		items.put(item6);
		items.put(item7);
		
		
		List<Map<String, String>> arrDetail1=null;
		try {
			arrDetail1 = ConvertToList(items);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<List<Map<String, String>>> nestList = new ArrayList<List<Map<String, String>>>();  
   
        nestList=getListTodoist(arrDetail1);
        // å‡†å¤‡æ•°æ�®åŒ¹é…�å™¨  
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(  
                this, // 1.ä¸Šä¸‹æ–‡  
                topList, // 2.é¡¶å±‚æ•°æ�®åˆ—è¡¨  
                android.R.layout.simple_expandable_list_item_1, // 3.ä¸€å±‚æ˜¾ç¤ºæ ·å¼�  
                new String[] { "weekday" }, // 4.é¡¶å±‚mapçš„é”®  
                new int[] { android.R.id.text1 }, // 5.é¡¶å±‚æ•°æ�®æ˜¾ç¤ºçš„View ID  
                nestList, // 6.äºŒå±‚æ•°æ�®åˆ—è¡¨  
                android.R.layout.simple_list_item_2, // 7.äºŒå±‚æ˜¾ç¤ºæ ·å¼�  
                new String[] {"time","description"}, // 8.äºŒå±‚mapçš„é”®  
                new int[] {android.R.id.text1,android.R.id.text2 } // 9.äºŒå±‚æ•°æ�®æ˜¾ç¤ºçš„View ID  
        );  
  
        // è®¾ç½®æ•°æ�®åŒ¹é…�å™¨  
        this.setListAdapter(adapter);      
        
        
        ////////////////////////////add the sensor////////////////////////
        
        mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
        mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
        
        new SensorEventListener() { 
        	
        	public void onSensorChanged(SensorEvent sensorEvent) 
            {  
        		if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)  
        		{ 
        			magneticFieldValues = sensorEvent.values.clone(); 
        		}
        		if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)  
	            { 
                	accelerometerValues = sensorEvent.values.clone();  
                	calculateOrientation();
	            }
			
            }
            
            
        	// Provided as a necessity to implement the sensors. The function itself has no real purpose in our software
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// Nothing
			}
          };
      
    }  
	
	// Starts the sensor when the program is activated
	public void onResume() {
		mSensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);  
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);  
    }
	
	// Detatches the sensor when the program is paused to conserve battery
	public void onPause() {
        mSensorManager.unregisterListener(this);  
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> ConvertToList(JSONArray jarray) throws JSONException
	{
		ArrayList<Map<String, String>> ljs=new ArrayList<Map<String, String>>();
		for(int iCount=0;iCount<jarray.length();iCount++)
		{
			JSONObject jsobj=jarray.getJSONObject(iCount);
			ljs.add(toMap(jsobj));
		}
		return ljs;
		
	}
	
	    @SuppressWarnings("unchecked") 
	    public static Map<String, String> toMap(JSONObject jsonObject) throws JSONException 
	    { 
	        Map<String, String> result = new HashMap<String, String>(); 
	        Iterator<String> iterator = jsonObject.keys(); 
	        String key = null; 
	        String value = null; 
	        while (iterator.hasNext()) 
	        { 
	            key = iterator.next(); 
	            value = jsonObject.getString(key); 
	            result.put(key, value); 
	        } 
	        return result; 
	    } 
	
	
  
	public List<List<Map<String, String>>> getListTodoist(List<Map<String, String>> LMDetail)
	{
		
		List<List<Map<String, String>>> nestList = new ArrayList<List<Map<String, String>>>(); //for return data
		
		List<Map<String, String>> nestList1 = new ArrayList<Map<String, String>>();  //Mon
		List<Map<String, String>> nestList2 = new ArrayList<Map<String, String>>();  //Tue
		List<Map<String, String>> nestList3 = new ArrayList<Map<String, String>>();  
		List<Map<String, String>> nestList4 = new ArrayList<Map<String, String>>();  
		List<Map<String, String>> nestList5 = new ArrayList<Map<String, String>>();  
		List<Map<String, String>> nestList6 = new ArrayList<Map<String, String>>();  
		List<Map<String, String>> nestList7 = new ArrayList<Map<String, String>>();  
		for(int iCount=0;iCount<LMDetail.size();iCount++)
		{
			String strDueDate=LMDetail.get(iCount).get("due_date").toString();
			String strDescription=LMDetail.get(iCount).get("content").toString();
			if(strDueDate.indexOf("Mon")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList1.add(nestMap);
			}
			else if(strDueDate.indexOf("Tue")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList2.add(nestMap);
			}
			else if(strDueDate.indexOf("Wed")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList3.add(nestMap);
			}
			else if(strDueDate.indexOf("Tur")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList4.add(nestMap);
			}
			else if(strDueDate.indexOf("Fri")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList5.add(nestMap);
			}
			else if(strDueDate.indexOf("Sat")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList6.add(nestMap);
			}
			else if(strDueDate.indexOf("Sun")!=-1)
			{
				Map<String, String> nestMap = new HashMap<String, String>();  
				nestMap.put("time", strDueDate);
				nestMap.put("description", strDescription);
				nestList7.add(nestMap);
			}
			else
			{}
		}
		
		nestList.add(nestList1);
		nestList.add(nestList2);
		nestList.add(nestList3);
		nestList.add(nestList4);
		nestList.add(nestList5);
		nestList.add(nestList6);
		nestList.add(nestList7);
        return nestList;
	}
	
	
	private  void calculateOrientation() {  
        float[] values = new float[3];  
        float[] R = new float[9];  
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);           
        SensorManager.getOrientation(R, values);  

        // è¦�ç»�è¿‡ä¸€æ¬¡æ•°æ�®æ ¼å¼�çš„è½¬æ�¢ï¼Œè½¬æ�¢ä¸ºåº¦  
        float varAngle = (float) Math.toDegrees(values[1]);  
        ExpandableListView list = getExpandableListView();
        if(varAngle>20)
        {
        	list.scrollBy(0, 12);
        }
        else if (varAngle>10)
        {
        	list.scrollBy(0, 5);
        }
        
        else if(varAngle<-20)
        {
        	if(list.getScrollY()>-1)
        	list.scrollBy(0, -12);
        	
        }
        else if(varAngle<-10)
        {
        	if(list.getScrollY()>-1)
        	list.scrollBy(0, -5);
        } 
        
      }  
	
	
	
	
    @Override  
    public boolean onChildClick(ExpandableListView parent, View v,  
            int groupPosition, int childPosition, long id) {  
        Toast.makeText(this,  
                "Location" + groupPosition + "second item" + childPosition,  
                Toast.LENGTH_SHORT).show();  
        return super.onChildClick(parent, v, groupPosition, childPosition, id);  
    }  
  
    @Override  
    public void onGroupCollapse(int groupPosition) {  
        Toast.makeText(this, "Location:Top List" + groupPosition, Toast.LENGTH_SHORT)  
                .show();  
        super.onGroupCollapse(groupPosition);  
    }  
  
    @Override  
    public void onGroupExpand(int groupPosition) {  
        Toast.makeText(this, "Location:Expand" + groupPosition, Toast.LENGTH_SHORT)  
                .show();  
        super.onGroupExpand(groupPosition);  
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}  
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)  
		{ 
			magneticFieldValues = event.values.clone(); 
		}
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)  
        { 
        	accelerometerValues = event.values.clone();  
        	calculateOrientation();
        }
	}
  
}  
