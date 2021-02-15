package fr.faget.noir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button m_Jouer = findViewById(R.id.b_Jouer);

        m_Jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent JoueurActivity = new Intent(getApplicationContext(), JoueurActivity.class);
                startActivity(JoueurActivity);
            }
        });
    }
}