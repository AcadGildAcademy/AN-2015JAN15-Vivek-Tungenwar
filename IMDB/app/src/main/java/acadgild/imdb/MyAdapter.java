package acadgild.imdb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import acadgild.imdb.Utils.ImageLoader;

/**
 * Created by Tungenwar on 05/05/2015.
 */
public class MyAdapter extends BaseAdapter {
    private Activity activity;
    private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    private ArrayList<HashMap<String, String>> movieList=new ArrayList<HashMap<String,String>>();

    public MyAdapter(Activity a, String[] d, ArrayList<HashMap<String, String>> movieList) {
        activity = a;
        data=d;
        this.movieList=movieList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public TextView text;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public RatingBar ratingBar;
        public ImageView image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if(convertView==null){

            vi = inflater.inflate(R.layout.movielist, null);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textView);
            holder.text1=(TextView)vi.findViewById(R.id.textView2);
            holder.text2=(TextView)vi.findViewById(R.id.textView3);
            holder.text3=(TextView)vi.findViewById(R.id.textView4);
            holder.ratingBar=(RatingBar)vi.findViewById(R.id.ratingBar);
            holder.image=(ImageView)vi.findViewById(R.id.imageView);

            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        holder.text.setText(movieList.get(position).get("title"));
        holder.text1.setText("Release on : "+movieList.get(position).get("release_date"));
        holder.text2.setText("Popularity :");
        holder.ratingBar.setMax(5);
        holder.ratingBar.setRating(Float.parseFloat(movieList.get(position).get("popularity")));
        holder.text3.setText("("+movieList.get(position).get("vote_average")+"/10)voted by "+movieList.get(position).get("vote_count")+" users");
        ImageView image = holder.image;

        imageLoader.DisplayImage(data[position], image);
        return vi;
    }
}
