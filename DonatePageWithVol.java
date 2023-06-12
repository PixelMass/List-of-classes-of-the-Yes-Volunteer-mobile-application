package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DonatePageWithVol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_page_with_vol);

        ImageView myImage1 = findViewById(R.id.mono_btn);
        myImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Добавьте код, который будет выполняться при нажатии на картинку
                Uri uri = Uri.parse("https://send.monobank.ua/jar/4vfjrPPZXj");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageView myImage2 = findViewById(R.id.privat_btn);
        myImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Добавьте код, который будет выполняться при нажатии на картинку
                Uri uri = Uri.parse("https://www.privat24.ua/rd/transfer_to_card?hash=rd/transfer_to_card/%7B%22from%22:%22%22,%22to%22:%224149%204390%201340%206198%22,%22ccy%22:%22UAH%22,%22amt%22:%22100%22%7D");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void startHomePage(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent, options.toBundle());
    }

    public void startNewsPage(View v) {
        Intent intent = new Intent(this, NewsPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent, options.toBundle());
    }

    public void startDonatePage(View v) {
        Intent intent = new Intent(this, DonatePageWithVol.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void onClickMenu(View v) {
        Intent intent = new Intent(this, MenuPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }

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

}
