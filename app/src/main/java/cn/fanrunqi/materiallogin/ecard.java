package cn.fanrunqi.materiallogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class ecard extends AppCompatActivity {
    ImageView ecfi1;
    TextView tx1,tx2,tx3,tx4,tx5;
    SharedPreferences sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecard);
        Bundle extras=getIntent().getExtras();
        ecfi1=findViewById(R.id.ecfi1);
        tx1=findViewById(R.id.tx1);
        tx2=findViewById(R.id.tx2);
        tx3=findViewById(R.id.tx3);
        tx4=findViewById(R.id.tx4);
        tx5=findViewById(R.id.tx5);
        tx1.setText(extras.getString("title"));
        tx2.setText(extras.getString("description"));
        tx3.setText(extras.getString("phone"));
        tx4.setText(extras.getString("email"));
        tx5.setText(extras.getString("address"));
        ecfi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation linkanimation=new AlphaAnimation(1,0);
                linkanimation.setDuration(300);
                linkanimation.setInterpolator(new LinearInterpolator());
                linkanimation.setRepeatCount(2);
                linkanimation.setRepeatMode(Animation.REVERSE);
                Intent intent=new Intent(getApplicationContext(),Electronics.class);
                startActivity(intent);
            }
        });

    }
}
