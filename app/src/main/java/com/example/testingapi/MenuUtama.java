package com.example.testingapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuUtama extends AppCompatActivity {

    Button btnpl,btnLaliga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_utama);

        Button btnpl = findViewById(R.id.btnpl);
        Button btnlaliga = findViewById(R.id.btnlaliga);

        btnlaliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuUtama.this, LaligaLeague.class);
                startActivity(in);
            }
        });
        btnpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuUtama.this, PremierLeague.class);
                startActivity(in);
            }
        });

        };
    }
