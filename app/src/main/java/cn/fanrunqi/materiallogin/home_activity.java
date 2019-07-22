package cn.fanrunqi.materiallogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class home_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactmanagerAdapter contactmanager;
    ArrayList<bean> data = new ArrayList<>();
    private static final Integer[] IMAGES = {R.drawable.usb};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] Images = {R.drawable.ic_monitor, R.drawable.ic_toy, R.drawable.ic_racing, R.drawable.ic_shirt, R.drawable.ic_groceries,R.drawable.ic_diet,R.drawable.ic_sofa,R.drawable.ic_target,R.drawable.ic_more};     // array of images
    String Names[] = {"Electronics", "Kids", "Automobiles", "Clothing", "Groceries","Food","Furniture","Sports","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.flipper1);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), Names, Images);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(1500);
        simpleAdapterViewFlipper.setAutoStart(true);

        ArrayList<bean> theList = get_fill();


        recyclerView = findViewById(R.id.r1);
        contactmanager = new ContactmanagerAdapter(theList, getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
       // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(contactmanager);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);
    }
    private ArrayList<bean> get_fill() {
        data.add(new bean("Electronics", R.drawable.ic_monitor));
        data.add(new bean("Kids", R.drawable.ic_toy));
        data.add(new bean("Automobiles", R.drawable.ic_racing));
        data.add(new bean("Clothing", R.drawable.ic_shirt));
        data.add(new bean("Groceries", R.drawable.ic_groceries));
        data.add(new bean("Food", R.drawable.ic_diet));
        data.add(new bean("Furniture", R.drawable.ic_sofa));
        data.add(new bean("Sports", R.drawable.ic_target));
        data.add(new bean("Others",R.drawable.ic_more));
        return data;
    }

    ;

    private class ContactmanagerAdapter extends RecyclerView.Adapter<home_activity.ContactmanagerAdapter.MyViewHolder>  {

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
                        Intent intent1=new Intent(home_activity.this,Electronicshome.class);
                        startActivity(intent1);
                    }
                    if(position==1)
                    {
                        Intent intent2=new Intent(home_activity.this,kids.class);
                        startActivity(intent2);
                    }
                    if (position==2)
                    {
                        Intent intent3=new Intent(home_activity.this,Automobiles.class);
                        startActivity(intent3);
                    }
                    if (position==3)
                    {
                        Intent intent4=new Intent(home_activity.this,Clothing.class);
                        startActivity(intent4);
                    }
                    if (position==4)
                    {
                        Intent intent5=new Intent(home_activity.this,Groceries.class);
                        startActivity(intent5);
                    }
                    if (position==5)
                    {
                        Intent intent6=new Intent(home_activity.this,Food.class);
                        startActivity(intent6);
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


