package cn.fanrunqi.materiallogin;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.fanrunqi.materiallogin.volley.API;

public class Electronics extends AppCompatActivity {
    RecyclerView recyclerView;
    Electronicsmanager electronicsmanager;
    ArrayList<bean1> data1 = new ArrayList<>();
    SharedPreferences sharedPreferences2;
    bean1 jobsBean1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        /*ArrayList<bean1> theList = get_fill();*/
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.INTERNET};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        recyclerView = findViewById(R.id.r2);
        getallofferz();

    }
    public void getallofferz() {
        //request for getting data
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String serverurl = API.alloffersviewurl;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("offers");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String eid=jsonObject1.getString("id");
                        String etitle = jsonObject1.getString("title");
                        String elocation=jsonObject1.getString("location");
                        String evalidupto = jsonObject1.getString("validupto");
                        String eshopname = jsonObject1.getString("shopname");
                        String esaveupto = jsonObject1.getString("percentage");
                        String edescription=jsonObject1.getString("description");
                        String eemail=jsonObject1.getString("email");
                        String ephone=jsonObject1.getString("mobile");
                        String eaddress=jsonObject1.getString("address");
                        /*sharedPreferences2=getSharedPreferences("offerz",MODE_PRIVATE);
                        SharedPreferences.Editor eeditor=sharedPreferences2.edit();
                        eeditor.putString("etitle22",etitle);
                        eeditor.putString("edescription22",edescription);
                        eeditor.putString("eemail22",eemail);
                        eeditor.putString("ephone22",ephone);
                        eeditor.putString("eaddress22",eaddress);
                        eeditor.apply();*/
                        jobsBean1 = new bean1(etitle, evalidupto, eshopname, esaveupto,edescription,ephone,eemail,eaddress, elocation,R.drawable.usb);
                        jobsBean1.setText1(etitle);
                        jobsBean1.setText2(evalidupto);
                        jobsBean1.setText3(eshopname);
                        jobsBean1.setText4(esaveupto);
                        jobsBean1.setText5(edescription);
                        jobsBean1.setText6(ephone);
                        jobsBean1.setText7(eemail);
                        jobsBean1.setText8(eaddress);
                        jobsBean1.setText9(elocation);
                        data1.add(jobsBean1);
                    }
                    electronicsmanager = new Electronicsmanager(data1, getApplicationContext());
                    GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 1);
                    // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(gridLayoutManager1);
                    recyclerView.setAdapter(electronicsmanager);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Main", "Error: " + error.getMessage());
                Log.d("Main", "" + error.getMessage() + "," + error.toString());


            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                return null;
            }

        };
        queue.add(stringRequest);
    }
    public static boolean hasPermissions(Context context, String... permissions)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onBackPressed()
    {
        Intent intent1=new Intent(getApplicationContext(),Electronicshome.class);
        startActivity(intent1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mmenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if (id==R.id.action_name)
        {
            Intent eintent=new Intent(getApplicationContext(),filter.class);
            Bundle bundle=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.enter,R.anim.exit).toBundle();
            startActivity(eintent,bundle);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*    private ArrayList<bean1> get_fill() {

        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));
        data1.add(new bean1("offername", "valid upto", "coupon code", "save upto", R.drawable.usb));

        return data1;
    }*/
    class Electronicsmanager extends RecyclerView.Adapter<Electronicsmanager.ViewHolder> {
        List<bean1> EList;
        Context context;


        public Electronicsmanager(ArrayList<bean1> EList, Context context) {
            this.EList = EList;
            this.context = context;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                        Intent i1 = new Intent(Electronics.this, ecard.class);
                        i1.putExtra("title",EList.get(position).getText1());
                    i1.putExtra("description",EList.get(position).getText5());
                    i1.putExtra("email",EList.get(position).getText7());
                    i1.putExtra("phone",EList.get(position).getText6());
                      i1.putExtra("address",EList.get(position).getText8());
                        startActivity(i1);

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
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/RalewayRegular.ttf");
                et1.setTypeface(typeface);
                et2.setTypeface(typeface);
                et3.setTypeface(typeface);
                et4.setTypeface(typeface);
            }
        }
    }
}