package cn.fanrunqi.materiallogin;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.fanrunqi.materiallogin.R;
import cn.fanrunqi.materiallogin.bean;

public class Electronicshome extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactmanagerAdapter contactmanager;
    ArrayList<bean> data = new ArrayList<>();
    private static final Integer[] IMAGES = {R.drawable.usb};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronicshome);

        ArrayList<bean> theList = get_fill();


        recyclerView = findViewById(R.id.reh1);
        contactmanager = new ContactmanagerAdapter(theList, getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(contactmanager);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent0=new Intent(getApplicationContext(),home_activity.class);
        startActivity(intent0);
    }

    private ArrayList<bean> get_fill() {
        data.add(new bean("Mobile", R.drawable.ic_smartphone));
        data.add(new bean("Computers", R.drawable.ic_responsive));
        data.add(new bean("Telivision", R.drawable.ic_television));
        data.add(new bean("Camera", R.drawable.ic_photo_camera));
        data.add(new bean("Accessories", R.drawable.ic_headphones));
        data.add(new bean("AC", R.drawable.ic_air_conditioner));
        data.add(new bean("Washing machine", R.drawable.ic_washing_machine));
        data.add(new bean("Refrigerators", R.drawable.ic_fridge));
        data.add(new bean("Others",R.drawable.ic_more));
        return data;
    }

    ;

    private class ContactmanagerAdapter extends RecyclerView.Adapter<Electronicshome.ContactmanagerAdapter.MyViewHolder>  {

        List<bean> hlist;
        Context context;

        public ContactmanagerAdapter(List<bean> hlist, Context context) {
            this.hlist = hlist;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate, parent, false);
            return new MyViewHolder(item);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textview1.setText(hlist.get(position).getTitle());
            holder.imageview1.setBackgroundResource((hlist.get(position).getImage()));
            holder.imageview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position==0)
                    {
                        Intent intent1=new Intent(Electronicshome.this,Electronics.class);
                        startActivity(intent1);
                    }
                    if(position==1)
                    {
                    }
                    if (position==2)
                    {

                    }
                    if (position==3)
                    {

                    }
                    if (position==4)
                    {

                    }
                    if (position==5)
                    {

                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return hlist.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textview1;
            ImageView imageview1;

            public MyViewHolder(View itemView) {
                super(itemView);
                textview1 = (TextView) itemView.findViewById(R.id.textview1);
                imageview1 = (ImageView) itemView.findViewById(R.id.imageview1);
            }
        }
    }
}


