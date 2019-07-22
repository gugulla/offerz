package cn.fanrunqi.materiallogin;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fanrunqi.materiallogin.volley.API;
import cn.fanrunqi.materiallogin.volley.VolleySingleton;

import static android.content.Context.MODE_PRIVATE;

public class TwoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView selleremail,category,subcategory;
    Spinner f2s1,f2s2;
    EditText description,validupto,title1,percentage,shopname,location,address,mobile;
    Button register;
    String string,substrings;
    String[] Electronics22={"Mobiles","Computers","Telivision","Camera","Accessories","AC","Washing machine","Refrigerator","Others"} ;
    String[] Kids22={"Toys","Baby care","School Supplies","Footwear","Clothing","Others"};
    String[] Automobiles22={"Cars","Bikes","Scooters","Autos","Cycles","Accessories","Others"};
    String[] Clothing22={"Men","Women","Watches","Accessories","Footwear","Personal Care","Others"};
    String[] Groceries22={"Cooking Essentials","Snacks","Beverages","Packaged Foods","House Hold","Nutrition","Pet Suplies","Others"};
    String[] Food22={"Bakeries","Restaurants","Bars","Cafes","Chinese","Others"};
    String[] Furniture22={"Sofa","Arm Chairs","Mattresses","Out Door","Storage","Tables","Curtains","Mirrors","Others"};
    String[] sports22={"Cricket","Badminton","FootBall","VolleyBall","Tennis","Swimming","Table Tennis","Cycling","Others"};
    String[] Others22={"Books","Stationary","Fitness","Saloons","Gifts","Gaming"};
    SharedPreferences preferences;
    ThreeFragment threeFragment;
    public TwoFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selleremail=(TextView)getView().findViewById(R.id.selleremail);
        title1=(EditText) getView().findViewById(R.id.title1);
        description=(EditText) getView().findViewById(R.id.description);
        validupto=(EditText) getView().findViewById(R.id.validupto);
        percentage=(EditText) getView().findViewById(R.id.percentage);
        shopname=(EditText) getView().findViewById(R.id.shopname);
        location=(EditText) getView().findViewById(R.id.location);
        address=(EditText) getView().findViewById(R.id.address);
        mobile=(EditText) getView().findViewById(R.id.mobile);
        category=(TextView) getView().findViewById(R.id.category);
        subcategory=(TextView) getView().findViewById(R.id.subcategory);
        register=(Button) getView().findViewById(R.id.register);
        f2s1=(Spinner)getView().findViewById(R.id.f2s1);
        f2s2=(Spinner)getView().findViewById(R.id.f2s2);
        //f2s1.setOnItemSelectedListener(this);


        List<String> Categories=new ArrayList<String>();
        Categories.add("Electronics");
        Categories.add("Kids");
        Categories.add("Automobiles");
        Categories.add("LifeStyle");
        Categories.add("Groceries");
        Categories.add("Food");
        Categories.add("Furniture");
        Categories.add("Sports");
        Categories.add("Others");

        ArrayAdapter dataAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f2s1.setAdapter(dataAdapter);

        f2s1.setOnItemSelectedListener(this);


        preferences=getActivity().getSharedPreferences("eshare",MODE_PRIVATE);
        String emailget1=preferences.getString("emailfromlogin","");
        selleremail.setText(emailget1);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE};

        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String Title = title1.getText().toString();
                String Description = description.getText().toString();
                String Vaildupto = validupto.getText().toString();
                String Percentage = percentage.getText().toString();
                String Shopname = shopname.getText().toString();
                String Location = location.getText().toString();
                String Address = address.getText().toString();
                String Mobile = mobile.getText().toString();
                String Category = string;
                String SubCategory = subcategory.getText().toString();

                supload();
            }
        });
    }

    private void supload() {

        //calling url
        String serverurl = API.addofferurl;
        //sending request to url for response Or Request Constructer with 4 parameters


        StringRequest sr = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String res=jsonObject.getString("result");//result should be matched with url link response ie,{"result":"success"}
                    if(res.equals("success")) //array key
                    {
                        Toast.makeText(getActivity(),"your details are saved ",Toast.LENGTH_SHORT).show();

                        title1.setText(null);
                        description.setText(null);
                        validupto.setText(null);
                        percentage.setText(null);
                        shopname.setText(null);
                        location.setText(null);
                        address.setText(null);
                        mobile.setText(null);
                        category.setText(null);
                        subcategory.setText(null);
                    }else {
                        Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
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
                String stitle=title1.getText().toString();
                String sdescription=description.getText().toString();
                String svalidupto=validupto.getText().toString();
                String spercentage=percentage.getText().toString();
                String sshopname=shopname.getText().toString();
                String slocation=location.getText().toString();
                String saddress=address.getText().toString();
                String sMobile=mobile.getText().toString();
                String scategory=string;
                String ssubcategory=f2s2.getSelectedItem().toString();
                String sEmail=selleremail.getText().toString();
                Map<String,String> data= new HashMap<String, String>();//to bind group of data
                //to insert data from edit feilds into table feilds
                data.put("email",sEmail);
                data.put("title",stitle);
                data.put("description",sdescription);
                data.put("validupto",svalidupto);
                data.put("percentage",spercentage);
                data.put("shopname",sshopname);
                data.put("location",slocation);
                data.put("address",saddress);
                data.put("mobile",sMobile);
                data.put("category",scategory);
                data.put("subcategory",ssubcategory);
                return data;
            }
        };
        //TO add request to Volley
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(sr);
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        string=f2s1.getSelectedItem().toString();
        if(string=="Electronics")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Electronics22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);
            substrings=f2s2.getSelectedItem().toString();

            //f2s2.setOnItemSelectedListener(this);

        }
        if(string=="Kids")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Kids22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);
            substrings=f2s2.getSelectedItem().toString();

            //f2s2.setOnItemSelectedListener(this);
        }
        if(string=="Automobiles")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Automobiles22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);
            substrings=f2s2.getSelectedItem().toString();

            //f2s2.setOnItemSelectedListener(this);
        }
        if(string=="LifeStyle")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Clothing22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);
            substrings=f2s2.getSelectedItem().toString();
            //f2s2.setOnItemSelectedListener(this);
        }
        if(string=="Groceries")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Groceries22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);

            //f2s2.setOnItemSelectedListener(this);
            substrings=f2s2.getSelectedItem().toString();
        }
        if(string=="Food")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Food22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);

            //f2s2.setOnItemSelectedListener(this);
            substrings=f2s2.getSelectedItem().toString();
        }
        if(string=="Furniture")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Furniture22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);

            //f2s2.setOnItemSelectedListener(this);
            substrings=f2s2.getSelectedItem().toString();
        }
        if(string=="Sports")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,sports22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);

            //f2s2.setOnItemSelectedListener(this);
            substrings=f2s2.getSelectedItem().toString();
        }
        if(string=="Others")
        {
            ArrayAdapter dataAdapter1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Others22);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            f2s2.setAdapter(dataAdapter1);

            //f2s2.setOnItemSelectedListener(this);
            substrings=f2s2.getSelectedItem().toString();
        }
        substrings=f2s2.getSelectedItem().toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}