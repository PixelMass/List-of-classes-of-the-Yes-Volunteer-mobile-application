package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AccountPage extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private EditText edEmail, edPassword;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            finishAffinity();
        else
            Toast.makeText(getBaseContext(), "Натисніть ще раз для виходу",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        init();

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);

        if (savedEmail != null && savedPassword != null) {
            edEmail.setText(savedEmail);
            edPassword.setText(savedPassword);
            signIn(savedEmail, savedPassword);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void init() {
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Реєстрація пройшла успішно", Toast.LENGTH_SHORT).show();
                            saveCredentials(edEmail.getText().toString(), edPassword.getText().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Перевірте ваші дані", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Не всі поля заповнені", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();

            signIn(email, password);
        }
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                if (email.equals("zombilike308@gmail.com")) {
                    Toast.makeText(getApplicationContext(), "Вхід виконано з адмін правами", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ChoicesMePage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Вхід виконано", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ChoicesPage.class);
                    startActivity(intent);
                }
                saveCredentials(email, password);
            } else {
                Toast.makeText(getApplicationContext(), "Перевірте ваші дані", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }
}
