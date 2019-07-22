package cn.fanrunqi.materiallogin;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class filter extends AppCompatActivity {
    LinearLayout ll1,ll0,l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ll1=findViewById(R.id.ll1);
        ll0=findViewById(R.id.ll0);
        l1=findViewById(R.id.l1);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Electronics.class);
                Bundle bundle=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.onea,R.anim.oneb).toBundle();
         startActivity(intent,bundle);
               /* ll0.setVisibility(View.GONE);
                onBackPressed();*/

            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Electronics.class);
                Bundle bundle=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.onea,R.anim.oneb).toBundle();
                startActivity(intent,bundle);
            }
        });

    }


 /*   @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(getApplicationContext(),Electronics.class);
        startActivity(intent);
    }*/
}
