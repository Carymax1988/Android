package com.liujiaqi.loadnewsdata;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    ListView lv = null;
    ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        final AsyncTask<Void, Void, ArrayList<HashMap<String, String>>> execute = new AsyncTask<Void, Void, ArrayList<HashMap<String, String>>>() {
            @Override
            protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
                try {
                    URL url = new URL("http://192.168.1.124/NewsDemo/getNewsJSON.php");
                    InputStream in = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuffer content = new StringBuffer();
                    String sb = "";
                    while ((sb = br.readLine()) != null) {
                        content.append(sb);
                    }
                    JSONArray ja = new JSONArray(content.toString());
                    for(int i=0;i<ja.length();i++){
                        JSONObject jo = ja.getJSONObject(i);
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("title",jo.getString("title"));
                        map.put("desc",jo.getString("desc"));
                        map.put("time",jo.getString("time"));
                        map.put("content_url",jo.getString("content_url"));
                        map.put("pic_url",jo.getString("pic_url"));
                        listdata.add(map);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return listdata;
            }

            @Override
            protected void onPostExecute(ArrayList<HashMap<String, String>> datas) {
                super.onPostExecute(datas);
                if(datas!=null){
                    ListItemAdapter adapter = new ListItemAdapter(MainActivity.this,listdata);
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
