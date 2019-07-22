package cn.fanrunqi.materiallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] Images;
    String[] Names;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] Names, int[] Images) {
        this.context = applicationContext;
        this.Images = Images;
        this.Names = Names;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return Names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.list_item, null);
        TextView Name = (TextView) view.findViewById(R.id.Name);
        ImageView Image = (ImageView) view.findViewById(R.id.Image);
        Name.setText(Names[position]);
        Image.setImageResource(Images[position]);
        return view;
    }
}