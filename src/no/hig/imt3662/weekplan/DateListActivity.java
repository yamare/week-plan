package no.hig.imt3662.weekplan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DateListActivity extends ExpandableListActivity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor aSensor;
	private Sensor mSensor;
	private float varX=0;
	private float varRecord=0;
	private List<List<String>> ChildrenData;
	float[] accelerometerValues = new float[3];   // values for accelerometer
    float[] magneticFieldValues = new float[3];    // values for magnetic
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        //ExpandableListView myExpandableListView = (ExpandableListView)findViewById(R.id.expandlist);
       // myExpandableListView.setAdapter(new ExpandableAdapter());
        
	     //Locale.setDefault(locale);
	     //Configuration config = new Configuration();
	     //config.locale = locale;
	     //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // 准备顶层列表数据  
        List<Map<String, String>> topList = new ArrayList<Map<String, String>>();  
  
        Map<String, String> topMap1 = new HashMap<String, String>();  
        Map<String, String> topMap2 = new HashMap<String, String>();  
        Map<String, String> topMap3 = new HashMap<String, String>();  
        Map<String, String> topMap4 = new HashMap<String, String>();  
        Map<String, String> topMap5 = new HashMap<String, String>();  
        Map<String, String> topMap6 = new HashMap<String, String>();  
        Map<String, String> topMap7 = new HashMap<String, String>();  
        
        Locale locale = getResources().getConfiguration().locale; 
		 if(locale.getLanguage().compareTo("zh")==0)
		 {
			 //locale = new Locale("cn");
			 topMap1.put("weekday", "周一");  
		        topMap2.put("weekday", "周二");  
		        topMap3.put("weekday", "周三"); 
		        topMap4.put("weekday","周四")	;
		        topMap5.put("weekday", "周五"); 
		        topMap6.put("weekday", "周六");
		        topMap7.put("weekday", "周日"); 
		 }
		 else
		 {
			 topMap1.put("weekday", "Monday");  
		        topMap2.put("weekday", "Tuesday");  
		        topMap3.put("weekday", "Wednsday"); 
		        topMap4.put("weekday", "Tursday"); 
		        topMap5.put("weekday", "Friday"); 
		        topMap6.put("weekday", "Saturday");
		        topMap7.put("weekday", "Sunday"); 
		 }
        
        
        topList.add(topMap1);  
        topList.add(topMap2);  
        topList.add(topMap3); 
        topList.add(topMap4); 
        topList.add(topMap5); 
        topList.add(topMap6); 
        topList.add(topMap7); 
        
  //a test data - detailMap 1
        /*
        Map<String, String> detailMap = new HashMap<String, String>();
        detailMap.put("due_date", "Sun Apr 29 2007 23:59:59");
        detailMap.put("content", "Let's go Sunday!");
        
        Map<String, String> detailMap2 = new HashMap<String, String>();
        detailMap2.put("due_date", "Tue Apr 27 2007 23:59:59");
        detailMap2.put("content", "Let's go Sunday!");
        
        Map<String, String> detailMap1 = new HashMap<String, String>();
        detailMap1.put("due_date", "Sat Apr 28 2007 23:59:59");
        detailMap1.put("content", "Let's go Saturday!");
        
        Map<String, String> detailMap3 = new HashMap<String, String>();
        detailMap3.put("due_date", "Mon Apr 28 2007 23:59:59");
        detailMap3.put("content", "Let's go Saturday!");
        */
        ItemsArray iarray=null;
        try {
			iarray=new ItemsArray();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        JSONObject item4 = new JSONObject();
        JSONObject item5 = new JSONObject();
        JSONObject item6 = new JSONObject();
        JSONObject item7 = new JSONObject();
        JSONObject item8 = new JSONObject();
        JSONObject item9 = new JSONObject();
        try{
		item1.put("due_date", "Mon Apr 2013-10-03 23:00");
		item1.put("content", "Project deadline");
		
		/*
		item2.put("due_date", "Sun Apr 2013-10-09 09:00");
		item2.put("content", "Finish reading for the exam");
		item3.put("due_date", "Sun Apr 2013-10-09 09:00");
		item3.put("content", "Finish reading for the exam");
		item4.put("due_date", "Sun Apr 2013-10-09 09:00");
		item4.put("content", "Finish reading for the exam");
		item5.put("due_date", "Sun Apr 2013-10-09 09:00");
		item5.put("content", "Finish reading for the exam");
		item6.put("due_date", "Sun Apr 2013-10-09 09:00");
		item6.put("content", "Finish reading for the exam");
		item7.put("due_date", "Sun Apr 2013-10-09 09:00");
		item7.put("content", "Finish reading for the exam");
		item8.put("due_date", "Sun Apr 2013-10-09 09:00");
		item8.put("content", "Finish reading for the exam");
		item9.put("due_date", "Sun Apr 2013-10-09 09:00");
		item9.put("content", "Finish reading for the exam");
		
		*/
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
        //ArrayList<Map<String, String>> arrDetail=new ArrayList<Map<String, String>>();
        //arrDetail.add(detailMap);
       // arrDetail.add(detailMap1);
        //arrDetail.add(detailMap2);
        //arrDetail.add(detailMap3);
        
        List<List<Map<String, String>>> nestList = new ArrayList<List<Map<String, String>>>();  
   
        nestList=getListTodoist(arrDetail1);
        // 准备数据匹配器  
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(  
                this, // 1.上下文  
                topList, // 2.顶层数据列表  
                android.R.layout.simple_expandable_list_item_1, // 3.一层显示样式  
                new String[] { "weekday" }, // 4.顶层map的键  
                new int[] { android.R.id.text1 }, // 5.顶层数据显示的View ID  
                nestList, // 6.二层数据列表  
                android.R.layout.simple_list_item_2, // 7.二层显示样式  
                new String[] {"time","description"}, // 8.二层map的键  
                new int[] {android.R.id.text1,android.R.id.text2 } // 9.二层数据显示的View ID  
        );  
  
        // 设置数据匹配器  
        this.setListAdapter(adapter);  
        
        //this.setSelectedGroup(0);
		//this.onGroupCollapse(0);
		//ExpandableListView myExpandableListView = (ExpandableListView)findViewById(R.id.expandlist);
		//myExpandableListView.expandGroup(1);
        
        
        
        ////////////////////////////add the sensor////////////////////////
        
        float[] Rvalues = new float[3];  
        float[] RR = new float[9]; 
        mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);  
        //mSensorManager.registerListener(this, mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        
        //SensorManager.getOrientation(RR,Rvalues); 
        mSensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);  
        mSensorManager.registerListener(this, mSensor,SensorManager.SENSOR_DELAY_NORMAL);  
        
        SensorEventListener myListener = new SensorEventListener() { 
        	
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
            
            

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
				
			}
          };
        ////////////////////////////////////////////////////////////////////
      
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
	  
	    
	public boolean onTouchEvent(MotionEvent event)
	{
		int action=event.getAction()&MotionEvent.ACTION_MASK;
		//mSensorManager.registerListener(this, mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
		switch(action)
		{
			case MotionEvent.ACTION_POINTER_DOWN:
			{
			    //mSensorManager.registerListener(this, mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
			    
			
			break;
			}
			case MotionEvent.ACTION_POINTER_UP:
			{
				//varX=varRecord;
				//mSensorManager.unregisterListener(this);
			}
		}
		
		return true;
	}
	
	/*
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		TextView tv = (TextView) findViewById(R.id.tv_num);
		ExpandableListView list = getExpandableListView();
        
        
		varRecord=event.values[0];
		tv.setText(Float.toString(varRecord));
		if(event.values[0]>1||event.values[1]>1)
		{
			list.scrollBy(0, 10);
			list.expandGroup(0);
			list.collapseGroup(6);
			
		}
		else if(event.values[0]<-1||event.values[1]<-1)
		{
			list.scrollBy(0, -10);
			list.collapseGroup(0);
			list.expandGroup(6);
		}
		
		
	}*/
	
	
  
	//{"due_date": new Date("Sun Apr 29 2007 23:59:59"),"content": "By these things",}
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

        // 要经过一次数据格式的转换，转换为度  
        float varAngle = (float) Math.toDegrees(values[1]);  
        ExpandableListView list = getExpandableListView();
        if(varAngle>20)
        {
        	//if(list.getScrollY()>-1)
        	list.scrollBy(0, 12);
        }
        else if (varAngle>10)
        {
        	//if(list.getScrollY()>-1)
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
        //Log.i(TAG, values[0]+"");  
        //values[1] = (float) Math.toDegrees(values[1]);  
        //values[2] = (float) Math.toDegrees(values[2]);  
        
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
    
	private class ExpandableAdapter extends BaseExpandableListAdapter {    
        @Override  
        public Object getChild(int groupPosition, int childPosition) {  
            return ChildrenData.get(groupPosition).get(childPosition);  
        }

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
				ViewGroup arg4) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
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
