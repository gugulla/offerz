package cn.fanrunqi.materiallogin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import cn.fanrunqi.materiallogin.a.AActivityOne;
import cn.fanrunqi.materiallogin.volley.API;
import cn.fanrunqi.materiallogin.volley.VolleySingleton;

import static android.content.Context.MODE_PRIVATE;
import static cn.fanrunqi.materiallogin.a.AActivityOne.hasPermissions;

public class FourFragment extends Fragment {
    TextView email;
    EditText name,phone,password;
    Button f4b1;
    SharedPreferences preferences;


    public FourFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview1= inflater.inflate(R.layout.fragment_four, container, false);
        return rootview1; }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=getView().findViewById(R.id.f4t3a);
        name=getView().findViewById(R.id.f4t2a);
        phone=getView().findViewById(R.id.f4t4a);
        password=getView().findViewById(R.id.f4t5a);
        f4b1=getView().findViewById(R.id.f4b1);
        preferences=getActivity().getSharedPreferences("eshare",MODE_PRIVATE);
        String emailget=preferences.getString("emailfromlogin","");
        email.setText(emailget);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.INTERNET};

        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }
        getdata();
        f4b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supload();
            }
        });



    }

    private void supload() {

        //calling url
        String serverurl = API.updateurl;
        //sending request to url for response Or Request Constructer with 4 parameters


        StringRequest sr = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String res=jsonObject.getString("result");//result should be matched with url link response ie,{"result":"success"}
                    if(res.equals("success")) //array key
                    {
                        Toast.makeText(getActivity(),"Profle Updated Successfully",Toast.LENGTH_SHORT).show();
                        email.setText(email.getText().toString());
                        password.setText(password.getText().toString());
                        phone.setText(phone.getText().toString());
                        name.setText(name.getText().toString());

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
                String semail=email.getText().toString();
                String spassword=password.getText().toString();
                String sphone=phone.getText().toString();
                String sname=name.getText().toString();
                Map<String,String> data= new HashMap<String, String>();//to bind group of data
                //to insert data from edit feilds into table feilds
                data.put("email",semail);
                data.put("password",spassword);
                data.put("mobile",sphone);
                data.put("fullname",sname);
                return data;
            }
        };
        //TO add request to Volley
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(sr);

    }

    public void getdata() {
        //request for getting data
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String serverurl = API.profileurl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject1 = null;
                try {
                    jsonObject1 = new JSONObject(response);

                    JSONArray jsonArray = jsonObject1.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String eemail = jsonObject.getString("email");
                        String ename = jsonObject.getString("fullname");
                        String ephone = jsonObject.getString("mobile");
                        String epassword = jsonObject.getString("password");
                        name.setText(ename);
                        phone.setText(ephone);
                        password.setText(epassword);
                        /*JobsBean jobsBean1 = new JobsBean();
                        jobsBean1.setId(Integer.parseInt(eid));
                        jobsBean1.setName(ename);
                        jobsBean1.setRole(erole);
                        jobsBean1.setTechnology(equal);
                        data.add(jobsBean1);*/
                        //}
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }}

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Main", "Error: " + error.getMessage());
                Log.d("Main", "" + error.getMessage() + "," + error.toString());


            }
        }) {
            @Override
            public Map<String, String> getParams() {
                //Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
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



}
