package in.www.loginsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
   static String aa;
   String email;
    EditText EditTextemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bt=findViewById(R.id.btn_login);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText EditTextemail=findViewById(R.id.input_email);
                EditText EditTextpassword=findViewById(R.id.input_password);
                 email=EditTextemail.getText().toString().trim();
                String password=EditTextpassword.getText().toString().trim();
                if(email.isEmpty())
                {
                    EditTextemail.setError("email is required");
                    EditTextemail.requestFocus();
                    return;
                }
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    EditTextemail.setError("enter Valid Eemail");
                    EditTextemail.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    EditTextpassword.setError("password is required");
                    EditTextpassword.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    EditTextpassword.setError("password  alteast six character is required");
                    EditTextpassword.requestFocus();
                    return;

                }
                ApiServices service = ApiClient.getClient().create(ApiServices.class);
                Call<MSG> userCall = service.userLogIn(email,password);
                userCall.enqueue(new Callback<MSG>() {
                    @Override
                    public void onResponse(Call<MSG> call, Response<MSG> response) {
                        if(response.body().getSuccess()!=0)
                        {
                           aa=email.trim();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("key_id",aa);
                            editor.apply();
                            Intent i =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);


                            Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"dkjdnjf", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<MSG> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
    }
}
