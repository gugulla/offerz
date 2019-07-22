package cn.fanrunqi.materiallogin.a;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.fanrunqi.materiallogin.MainActivity;
import cn.fanrunqi.materiallogin.R;
import cn.fanrunqi.materiallogin.SellerMain;
import cn.fanrunqi.materiallogin.volley.API;
import cn.fanrunqi.materiallogin.volley.VolleySingleton;

public class AActivityOne extends AppCompatActivity {
    EditText lemail1,lpassword1;
    Button lgo1;
    CheckBox checkBox;

    private CardView cv;
   private  TextView pass;
    private FloatingActionButton fab;
    public static String  PREFS_NAME="mypre";
    public static String PREF_USERNAME="email";
    public static String PREF_PASSWORD="password";
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_one);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        initView();
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            lemail1.setText(loginPreferences.getString("email", ""));
            lpassword1.setText(loginPreferences.getString("password", ""));
            checkBox.setChecked(true);
        }
        setListener();

    }


    private void initView() {
        checkBox=findViewById(R.id.checkbox);
        lemail1 = findViewById(R.id.lemail);
        lpassword1 = findViewById(R.id.lpassword);
        lgo1 = findViewById(R.id.lgo);
        cv = findViewById(R.id.cv);
        fab = findViewById(R.id.fab);
        pass=findViewById(R.id.pass);
    }

    private void setListener() {
        lgo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences=getSharedPreferences("eshare",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("emailfromlogin",lemail1.getText().toString());
                editor.apply();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(lemail1.getWindowToken(), 0);

                String username = lemail1.getText().toString();
                String password = lpassword1.getText().toString();

                if (checkBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("email", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }


                String REmail1=lemail1.getText().toString();
                String RPassword1=lpassword1.getText().toString();
                if(REmail1.equals("")) {
                   Drawable errorIcon=getResources().getDrawable(R.drawable.ic_error_outline_black_24dp);
                    errorIcon.setBounds(new Rect(0,0,errorIcon.getIntrinsicWidth(),errorIcon.getIntrinsicHeight()));
                    lemail1.setError("Enter Your Email",errorIcon);
                }
                else if(RPassword1.equals("")) {
                    Drawable errorIcon=getResources().getDrawable(R.drawable.ic_error_outline_black_24dp);
                    errorIcon.setBounds(new Rect(0,0,errorIcon.getIntrinsicWidth(),errorIcon.getIntrinsicHeight()));
                    lpassword1.setError("Enter password",errorIcon);

                }
                else
                    supload();

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AActivityOne.this, fab, fab.getTransitionName());
                startActivity(new Intent(AActivityOne.this, AActivityTwo.class), options.toBundle());
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AActivityOne.this,forgot_password.class);
                startActivity(i);
            }

        });

    }

    private void supload() {

        //calling url
        String serverurl = API.loginurl;
        //sending request to url for response Or Request Constructer with 4 parameters


        StringRequest sr = new StringRequest(Request.Method.POST, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String res=jsonObject.getString("result");//result should be matched with url link response ie,{"result":"success"}
                    if(res.equals("success")) //array key
                    {
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                        lemail1.setText(null);
                        lpassword1.setText(null);
                        Explode explode = new Explode();
                        explode.setDuration(500);

                        getWindow().setExitTransition(explode);
                        getWindow().setEnterTransition(explode);
                        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(AActivityOne.this);
                        Intent i2 = new Intent(AActivityOne.this,SellerMain.class);
                        startActivity(i2, oc2.toBundle());

                    }else {
                        Toast.makeText(getApplicationContext(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
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
                String semail=lemail1.getText().toString();
                String spassword=lpassword1.getText().toString();
                Map<String,String> data= new HashMap<String, String>();//to bind group of data
                //to insert data from edit feilds into table feilds
                data.put("email",semail);
                data.put("password",spassword);
                return data;
            }
        };
        //TO add request to Volley
        VolleySingleton.getInstance(this).addToRequestQueue(sr);

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
        Intent inte=new Intent(getApplicationContext(),MainActivity .class);
        startActivity(inte);
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }
}
