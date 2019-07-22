package cn.fanrunqi.materiallogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class kids extends AppCompatActivity {
    RecyclerView recyclerView;
    Mobilesmanager mobilesmanager;
    ArrayList<bean1> data1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);

    //    ArrayList<bean1> theList = get_fill();

        recyclerView = findViewById(R.id.r3);
      //  mobilesmanager = new Mobilesmanager(theList, getApplicationContext());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 1);
        // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager1);
        recyclerView.setAdapter(mobilesmanager);
    }

  /*  private ArrayList<bean1> get_fill() {

        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));

        return data1;
    }
*/
    ;


    class Mobilesmanager extends RecyclerView.Adapter<Mobilesmanager.ViewHolder> {
        List<bean1> EList;
        Context context;


        public Mobilesmanager(ArrayList<bean1> EList, Context context) {
            this.EList = EList;
            this.context = context;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
                viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.electronicsinflator, parent, false);
            return new ViewHolder(item);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.et1.setText(EList.get(position).getText1());
            holder.et2.setText(EList.get(position).getText2());
            holder.et3.setText(EList.get(position).getText3());
            holder.et4.setText(EList.get(position).getText4());
            holder.ei1.setBackgroundResource((EList.get(position).getImage1()));
            holder.cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Intent i1 = new Intent(kids.this, mcard.class);
                        startActivity(i1);
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return EList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView et1, et2, et3, et4;
            ImageView ei1;
            CardView cardView1;

            public ViewHolder(View itemView) {
                super(itemView);
                et1 = (TextView) itemView.findViewById(R.id.et1);
                et2 = (TextView) itemView.findViewById(R.id.et2);
                et3 = (TextView) itemView.findViewById(R.id.et3);
                et4 = (TextView) itemView.findViewById(R.id.et4);
                ei1 = (ImageView) itemView.findViewById(R.id.ei1);
                cardView1 = (CardView) itemView.findViewById(R.id.cardview1);



            }
        }
    }
}