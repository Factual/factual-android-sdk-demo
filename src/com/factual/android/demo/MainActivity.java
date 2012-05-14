package com.factual.android.demo;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

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
		task.execute(new Query().limit(5));
    }
    
	protected class FactualRetrievalTask extends AsyncTask<Query, Integer, List<ReadResponse>> {
		@Override
		protected List<ReadResponse> doInBackground(Query... params) {
			List<ReadResponse> results = Lists.newArrayList();
			for (Query q : params) {
				results.add(factual.fetch("places", q));
			}
			return results;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
		}

		@Override
		protected void onPostExecute(List<ReadResponse> responses) {
			StringBuffer sb = new StringBuffer();
			for (ReadResponse r : responses) {
				sb.append(r.getJson());
				sb.append(System.getProperty("line.separator"));
			}
			resultText.setText(sb.toString());
		}

	}    
}