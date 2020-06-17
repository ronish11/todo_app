package com.example.TODO;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashbordActivity extends AppCompatActivity {

    private CardView cardview1;
    private CardView cardview2;
    private CardView cardview3;
    private CardView cardview4;
    private CardView cardview5;
    private CardView cardview6;
    private CardView cardview7;
    private CardView cardview8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);


        this.cardview1 = (CardView) findViewById(R.id.card1);
        this.cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashbordActivity.this, TODO.class);
                startActivity(intent1);
            }
        });

        this.cardview2 = (CardView) findViewById(R.id.card2);
        this.cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashbordActivity.this, TODO.class);
                startActivity(intent1);
            }
        });

        //card 4
        this.cardview3 = (CardView) findViewById(R.id.card3);
        this.cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashbordActivity.this, "Logged out Successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(DashbordActivity.this, "Opening Settings.", Toast.LENGTH_SHORT).show();
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intent);
            }
        });



        //settings apps button function
        this.cardview4 = (CardView) findViewById(R.id.card4);
        this.cardview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashbordActivity.this, "Logged out Successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(DashbordActivity.this, "Opening Settings.", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                finish();
            }
        });


        //news button function..
        this.cardview5 = (CardView) findViewById(R.id.card5);
        this.cardview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashbordActivity.this, "Logged out Successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(DashbordActivity.this, "Opening Facebook", Toast.LENGTH_SHORT).show();
                String url = "https://www.facebook.com/rn.sharma.79";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                finish();
            }
        });

        //logout button
        this.cardview6 = (CardView) findViewById(R.id.card6);
        this.cardview6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashbordActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("Yes Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DashbordActivity.this, "Logged out Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DashbordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}
