package com.liujiaqi.loadnewsdata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by miracles on 2015/3/12.
 */
public class ListItemAdapter extends BaseAdapter{
    Context context;
    ArrayList<HashMap<String, String>> listdata;
    ListItemAdapter(Context context,ArrayList<HashMap<String, String>> listdata){
        this.context = context;
        this.listdata = listdata;

    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder  = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        final HashMap<String,String>itemdata = listdata.get(position);

        new AsyncTask<String, Void, Drawable>()
        {
            protected Drawable doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        protected void onPostExecute(Drawable result) {
            holder.image.setImageDrawable(result);
        }
        }.execute(itemdata.get("pic_url"));

        holder.title.setText(itemdata.get("title"));
        holder.desc.setText(itemdata.get("desc"));
        holder.time.setText(itemdata.get("time"));
        return convertView;
    }

    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }

    class ViewHolder{
        ImageView image;
        TextView title;
        TextView desc;
        TextView time;
    }
}
