package com.factual.android.demo;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.factual.driver.Circle;
import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;
import com.google.common.collect.Lists;

public class MainActivity extends Activity {	
	protected Factual factual = new Factual("your_key", "your_secret");
	private TextView resultText = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        resultText = (TextView) findViewById(R.id.resultText);
        FactualRetrievalTask task = new FactualRetrievalTask();
        
        double latitude = 34.06018; 
        double longitude = -118.41835;
        int meters = 500;
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		String locationProvider = LocationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(locationProvider);
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
		
		Query query = new Query()
		.within(new Circle(latitude, longitude, meters))
		.field("cuisine").equal("Italian")
		.sortAsc("$distance")
		.only("name", "address", "tel");
		
		task.execute(query);
    }
    
	protected class FactualRetrievalTask extends AsyncTask<Query, Integer, List<ReadResponse>> {
		@Override
		protected List<ReadResponse> doInBackground(Query... params) {
			List<ReadResponse> results = Lists.newArrayList();
			for (Query q : params) {
				results.add(factual.fetch("restaurants-us", q));
			}
			return results;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
		}

		@Override
		protected void onPostExecute(List<ReadResponse> responses) {
			StringBuffer sb = new StringBuffer();
			for (ReadResponse response : responses) {
				for (Map<String, Object> restaurant : response.getData()) {
				String name = (String) restaurant.get("name");
				String address = (String) restaurant.get("address");
				String phone = (String) restaurant.get("tel");
				Number distance = (Number) restaurant.get("$distance");
				sb.append(distance + " meters away: "+name+" @ " +address + ", call "+phone);
				sb.append(System.getProperty("line.separator"));
				}  
			}
			resultText.setText(sb.toString());
		}

	}    
}