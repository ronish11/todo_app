package com.example.TODO.LoginAndSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.TODO.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_username,et_password,et_email,et_address,et_phon;
    private String name,password,email,address,phon;
    Button submit,back_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        et_username=(EditText) findViewById(R.id.et_username);
        et_password=(EditText) findViewById(R.id.et_password);
        et_email=(EditText) findViewById(R.id.et_email);
        et_address=(EditText) findViewById(R.id.et_address);
        et_phon=(EditText) findViewById(R.id.et_phon);
        submit=(Button) findViewById(R.id.register);
        back_to_login=(Button) findViewById(R.id.back_to_login);

      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              register(); // call when button is clicked

          }
          public void register(){
              intialized(); // inside method intialized the string valriable
              if(!validate()){
                  Toast.makeText(RegisterActivity.this, "registration error", Toast.LENGTH_SHORT).show();
              }
              else{
                  onSignUpSucess();
              }
          }
          public void onSignUpSucess(){
              // TODO go back to login page
              Intent link = new Intent(RegisterActivity.this, LoginActivity.class);
              startActivity(link);
          }
          public boolean validate(){
              boolean valid=true;
              if(name.isEmpty()||name.length()>32){
                  et_username.setError("invalid username");
                  return false;
              }
              if(password.isEmpty()||password.length()>32){
                  et_password.setError("invalid Password");
                  return false;
              }
              if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  et_email.setError("invalid Email Address");
                  return false;
              }
              if(phon.isEmpty()||!Patterns.PHONE.matcher(phon).matches()||phon.length()>10){
                  et_phon.setError("invalid Phon number");
                  return false;
              }
              if(address.isEmpty()||address.length()>20){
                  et_address.setError("invalid Address");
                  return false;
              }
              return valid;
          }
          public void intialized(){
              name=et_username.getText().toString().trim();
              password=et_password.getText().toString().trim();
              email=et_email.getText().toString().trim();
              address=et_address.getText().toString().trim();
              phon=et_phon.getText().toString().trim();
          }
      });

        back_to_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent link=new Intent(RegisterActivity.this, LoginActivity.class);
              startActivity(link);
          }
      });

    }
}
