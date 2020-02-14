package in.www.loginsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        final Button sign_up=findViewById(R.id.btn_signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText EditTextname=findViewById(R.id.input_name);
                EditText EditTextemail=findViewById(R.id.input_email);
                EditText EditTextpassword=findViewById(R.id.input_password);
                String email=EditTextemail.getText().toString().trim();
                String password=EditTextpassword.getText().toString().trim();
                String name=EditTextname.getText().toString().trim();
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
                if(name.isEmpty())
                {
                    EditTextname.setError("name is required");
                    EditTextname.requestFocus();
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(SignActivity.this);
                progressDialog.setMessage("creating account...");
                progressDialog.show();
                ApiServices service = ApiClient.getClient().create(ApiServices.class);
                Call<MSG> userCall = service.userSignUp(name,email,password);
                userCall.enqueue(new Callback<MSG>() {
                    @Override
                    public void onResponse(Call<MSG> call, Response<MSG> response) {
                        progressDialog.dismiss();
                        Toast.makeText(SignActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<MSG> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(SignActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

}



