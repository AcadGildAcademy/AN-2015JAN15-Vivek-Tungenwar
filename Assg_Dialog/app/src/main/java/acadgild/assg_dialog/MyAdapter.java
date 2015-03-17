package acadgild.assg_dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tungenwar on 17/03/2015.
 */
public class MyAdapter extends ArrayAdapter<Contacts> {
    private final Context context;
    private final ArrayList<Contacts> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Contacts> itemsArrayList) {

        super(context, R.layout.list, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.list, parent, false);

        // 3. Get the two text view from the rowView
        TextView Name=(TextView) rowView.findViewById(R.id.txtList1);
        TextView Number = (TextView) rowView.findViewById(R.id.txtList2);

        // 4. Set the text for textView
        if(itemsArrayList.get(position)!=null) {
            Name.setText(itemsArrayList.get(position).getName());
            Number.setText(itemsArrayList.get(position).getNumber());
        }

        // 5. return rowView
        return rowView;

    }
}
