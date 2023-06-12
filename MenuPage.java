package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class MenuPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);

        TextView logoutTextView = findViewById(R.id.logout);
        logoutTextView.setOnClickListener(v -> {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();


            firebaseAuth.signOut();


            Intent intent = new Intent(MenuPage.this, AccountPage.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


            finish();
        });
    }

    public void onClickChangeRole(View v){
        Intent intent = new Intent(this, ChoicesPage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void onClickMenuBack(View v) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
