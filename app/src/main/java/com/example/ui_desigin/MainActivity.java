package com.example.ui_desigin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public EditText uname,password;
    public String name,pass;
    public Button login,createnew;

    // just for check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userlogin);

        uname= findViewById(R.id.et_username);
        password= findViewById(R.id.et_password);
        login= findViewById(R.id.bt_login);
        createnew = findViewById(R.id.bt_register);

        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(link);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userlogin(); // call when login click
            }

            public void userlogin(){
                initialize(); // inside this method veriable are converted into string

                if(!validate()){
                    Toast.makeText(MainActivity.this, "login error", Toast.LENGTH_SHORT).show();

                }
                else {
                    onsignupsucess();
                }
            }
            public void onsignupsucess(){
                Intent link = new Intent(MainActivity.this,TODO.class);
                startActivity(link);
            }


            public boolean validate(){
                boolean valid= true;
                if(name.isEmpty()||name.length()>30)
                {
                  uname.setError("invalid name");
                  return false;
                }
                if(pass.isEmpty() || pass.length()>30){
                    password.setError("invalid password");
                    return false;
                }
                return true;
            }

            public void initialize(){
                name=uname.getText().toString().trim();
                pass=password.getText().toString().trim();
            }
        });
    }
}
