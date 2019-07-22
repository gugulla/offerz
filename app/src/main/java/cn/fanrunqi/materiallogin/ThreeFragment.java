package cn.fanrunqi.materiallogin;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fanrunqi.materiallogin.a.AActivityOne;
import cn.fanrunqi.materiallogin.volley.API;
import cn.fanrunqi.materiallogin.volley.VolleySingleton;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
public class ThreeFragment extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences preferences;
    Fragmentmanager1 fragmentmanager1;
    ArrayList<bean2> data2 = new ArrayList<>();
    ArrayList<bean2> theList = get_fill();
    TextView email1;
    EditText title1,description1,validupto1,percentage1,shopname1,location1,address1,mobile1,category1,subcategory1;
    public ThreeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_three, container, false);



        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.INTERNET};

        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }


        recyclerView =rootview.findViewById(R.id.f3);
      /*  fragmentmanager1 = new Fragmentmanager1(theList, getContext());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1);
        // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager1);
        recyclerView.setAdapter(fragmentmanager1);*/
        getofferdata();
        return rootview; }

    public void getofferdata() {
        //request for getting data
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String serverurl = API.viewofferurl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                preferences=getActivity().getSharedPreferences("eshare",MODE_PRIVATE);
                String emailget=preferences.getString("emailfromlogin","");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("offers");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String eemail=jsonObject1.getString("email");
                        String eid=jsonObject1.getString("id");
                        String etitle = jsonObject1.getString("title");
                        String evalidupto = jsonObject1.getString("validupto");
                        String edescription = jsonObject1.getString("description");
                        bean2 bb = new bean2(R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp,etitle,evalidupto,edescription);
                        bb.setImage1(R.drawable.usb);
                        bb.setImage2(R.drawable.ic_create_black_24dp);
                        bb.setImage3(R.drawable.ic_delete_black_24dp);
                        bb.setOffername1(etitle);
                        bb.setDescription1(edescription);
                        bb.setValidupto1(evalidupto);
                        data2.add(bb);
                        SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("prefid",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences1.edit();
                        editor.putString("eid",eid);
                        editor.apply();
                    }
                    fragmentmanager1 = new Fragmentmanager1(data2, getContext());
                    GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1);
                    // LinearLayoutManager mlinearlayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(gridLayoutManager1);
                    recyclerView.setAdapter(fragmentmanager1);

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
                String emailget=preferences.getString("emailfromlogin","");
                //String semail=lemail1.getText().toString();
                Map<String,String> data= new HashMap<String, String>();//to bind group of data
                //to insert data from edit feilds into table feilds
                data.put("email",emailget);
                return data;
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

    private ArrayList<bean2> get_fill() {
       /* data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));
        data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));
        data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));
        data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));
        data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));
        data2.add(new bean2("hi","hello","bye",R.drawable.usb,R.drawable.ic_create_black_24dp,R.drawable.ic_delete_black_24dp));*/
        return data2;
    }
    class Fragmentmanager1 extends RecyclerView.Adapter<Fragmentmanager1.ViewHolder> {
        List<bean2> EList;
        Context context;
        public Fragmentmanager1(ArrayList<bean2> EList, Context context) {
            this.EList = EList;
            this.context = context;

        }
        @NonNull
        @Override
        public Fragmentmanager1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment3inflator, parent, false);
            return new Fragmentmanager1.ViewHolder(item);
        }
        @Override
        public void onBindViewHolder(@NonNull final Fragmentmanager1.ViewHolder holder, final int position) {
            holder.ft1.setText(EList.get(position).getOffername1());
            holder.ft2.setText(EList.get(position).getValidupto1());
            holder.ft3.setText(EList.get(position).getDescription1());
            holder.fi1.setBackgroundResource((EList.get(position).getImage1()));
            holder.fi2.setBackgroundResource((EList.get(position).getImage2()));
            holder.fi3.setBackgroundResource((EList.get(position).getImage3()));
            holder.fi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog=new Dialog(getActivity());

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.editxml);
                    dialog.setTitle("EDIT");
                    final EditText ea=(EditText)dialog.findViewById(R.id.f3iet1);
                    final EditText eb=(EditText)dialog.findViewById(R.id.f3iet2);
                    final EditText ec=(EditText)dialog.findViewById(R.id.f3oet3);

                    ea.setText(EList.get(position).getOffername1());
                    eb.setText(EList.get(position).getValidupto1());
                    ec.setText(EList.get(position).getDescription1());

                    Button dialogedit=(Button)dialog.findViewById(R.id.f3obtedit);
                    Button dialogcancel=(Button)dialog.findViewById(R.id.f3obtcancel);
                    dialogedit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                        }
                    });
                    dialogcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentTransaction transaction=getFragmentManager().beginTransaction();
                            transaction.replace(R.id.frag3id,new Fragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    Window window=dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                }
            });
            holder.fi3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("do you want to delete?");
                    alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            supload();
                          // data2.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, data2.size());
                            Toast.makeText(getActivity(),"item has been deleted",Toast.LENGTH_SHORT).show();
                            getActivity().recreate();

                        }

                    });
                    alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentTransaction transaction=getFragmentManager().beginTransaction();
                            transaction.replace(R.id.frag3id,new Fragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                    AlertDialog al = alertDialog.create();
                    al.show();
                    al.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                    al.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));


                }
            });

        }

        @Override
        public int getItemCount() {
            return EList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView ft1,ft2,ft3;
            ImageView fi1,fi2,fi3;

            public ViewHolder(View itemView) {
                super(itemView);
                ft1 = (TextView) itemView.findViewById(R.id.ft1);
                ft2 = (TextView) itemView.findViewById(R.id.ft2);
                ft3= (TextView) itemView.findViewById(R.id.ft3);
                fi1 = (ImageView) itemView.findViewById(R.id.fi1);
                fi2=(ImageView) itemView.findViewById(R.id.fi2);
                fi3=(ImageView) itemView.findViewById(R.id.fi3);

            }
        }
    }
    private void supload() {

        //calling url
        String serverurl = API.deleteofferurl;
        //sending request to url for response Or Request Constructer with 4 parameters
        SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("prefid",MODE_PRIVATE);
        final String x=sharedPreferences1.getString("eid","");


        StringRequest sr = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String res=jsonObject.getString("result");//result should be matched with url link response ie,{"result":"success"}
                    if(res.equals("success")) //array key
                    {
                        Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getActivity(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Log.e("ERROR","Exception");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Main", "Error: " + error.getMessage());
                Log.d("Main", "" + error.getMessage() + "," + error.toString());

            }
        }){
            @Override
            public Map<String,String> getParams() throws AuthFailureError {
                //To Read data from Edit fields and convert to string
                Map<String,String> data= new HashMap<String,String>();//to bind group of data
                //to insert data from edit feilds into table feilds
                data.put("id",x);
                return data;
            }
        };
        //TO add request to Volley
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(sr);

    }


}