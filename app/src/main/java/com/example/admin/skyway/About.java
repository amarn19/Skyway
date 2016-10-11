package com.example.admin.skyway;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        }
        //CardView aboutCard = (CardView) findViewById(R.id.aboutCard);
        //aboutCard.setAnimation(AnimationUtils.loadAnimation(this, R.anim.view_up));
        //CircleImageView tatvaLogoImageView = (CircleImageView) findViewById(R.id.tatvaLogoImageView);
        ImageButton facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        ImageButton instagramButton = (ImageButton) findViewById(R.id.instagramButton);
        ImageButton webButton = (ImageButton) findViewById(R.id.webButton);
        ImageButton playButton = (ImageButton) findViewById(R.id.playButton);
        ImageButton phoneButton = (ImageButton) findViewById(R.id.phoneButton);
        ImageButton mailButton = (ImageButton) findViewById(R.id.mailButton);
        //CircleImageView appDevImageView = (CircleImageView) findViewById(R.id.appDevImage);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/amar.narayan.p")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/amar.narayan.p")));
                }

            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/amar_narayan");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/amar_narayan")));
                }
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://about.me/amarnarayan")));
            }
        });

//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.biryanistudio.tatvaapp")));
//                } catch (Exception e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.biryanistudio.tatvaapp")));
//                }
//            }
//        });


        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9035697068")));
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:amarn19@gmail.com")), "Send with"));
            }
        });

    }
}
