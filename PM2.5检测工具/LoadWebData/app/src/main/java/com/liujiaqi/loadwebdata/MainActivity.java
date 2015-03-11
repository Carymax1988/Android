package com.liujiaqi.loadwebdata;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends ActionBarActivity {
    private TextView tv;
    private Button reload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        reload = (Button) findViewById(R.id.reload);
        reload.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });
    }

    private void reloadData() {
        tv.setText("加载中.........");
        final AsyncTask<Void, Void, String> execute = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuffer content = new StringBuffer();
                try {
                    InputStream in = new URL("http://aqicn.org/publishingdata/json/").openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return content.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    String tvString = "";
                    try {
                        JSONArray jsondata = new JSONArray(s);
                        for (int i = 0; i < jsondata.length(); i++) {
                            JSONObject Jo = jsondata.getJSONObject(i);
                            String cityName = Jo.getString("cityName");
                            String stationName = Jo.getString("stationName");
                            String localName = Jo.getString("localName");
                            String latitude = Jo.getString("latitude");
                            String longitude = Jo.getString("longitude");
                            tvString += "**********************\n";
                            tvString += cityName+"\n";
                            tvString += stationName+"\n";
                            tvString += localName+"\n";
                            tvString += latitude+"\n";
                            tvString += longitude+"\n";
                            System.out.println(cityName);
                            System.out.println(stationName);
                            System.out.println(localName);
                            System.out.println(latitude);
                            System.out.println(longitude);
                            JSONArray pollutants = Jo.getJSONArray("pollutants");
                            for (int j = 0; j < pollutants.length(); j++) {
                                JSONObject JoIn = pollutants.getJSONObject(j);
                                String pol = JoIn.getString("pol");
                                String unit = JoIn.getString("unit");
                                String time = JoIn.getString("time");
                                String value = JoIn.getString("value");
                                String averaging = JoIn.getString("averaging");
                                tvString += "--------------------\n";
                                tvString += pol+"\n";
                                tvString += unit+"\n";
                                tvString += time+"\n";
                                tvString += value+"\n";
                                tvString += averaging+"\n";
                                tvString += "--------------------\n";
                                System.out.println(pol);
                                System.out.println(unit);
                                System.out.println(time);
                                System.out.println(value);
                                System.out.println(averaging);
                            }
                            tvString += "**********************\n";
                        }
                        tv.setText(tvString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    tv.setText(s);
                }
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
