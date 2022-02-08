package ishara.atigapaha.librarymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        EditText mEmail = findViewById(R.id.txtEmail);
       EditText mPassword = findViewById(R.id.txtPass);
       Button mLoginBtn = findViewById(R.id.btnLogin);

        mLoginBtn.setOnClickListener(view -> {


            //required text

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("password is Required");
                return;
            }
            if(password.length()<6){
                mPassword.setError("Password must be >=6 Characters");
                return;
            }


            if(mEmail.getText().toString().equals("admin@gmail.com") && mPassword.getText().toString().equals("Admin123")){
                Intent intent = new Intent(getApplicationContext(),MemberDashboardActivity.class);
                startActivity(intent);
            }


            else
            {
                Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
            }
});

    }}
