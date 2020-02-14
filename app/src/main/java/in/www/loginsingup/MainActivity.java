package in.www.loginsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sign_up = findViewById(R.id.sign_up);
        Button login=findViewById(R.id.login);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        name1=preferences.getString("key_id",null);
        checkUser();
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignActivity.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });
        Button logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("Key_id");
                editor.clear();
                editor.commit();
                if(settings.getString("key_id",null)!=null) {
                    Toast.makeText(getApplicationContext(),"bfbdf",Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"hdfdhds12",Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

    private void checkUser() {
        if(name1!=null)
        {
            Toast.makeText(MainActivity.this,"dbsadhadg",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
            Toast.makeText(MainActivity.this,"dabhsf",Toast.LENGTH_SHORT).show();
        }
    }


}

