package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TourDetailActivity extends AppCompatActivity {
    ImageView imgTour;
    TextView nameTour, descTour, priceTour, txtCount;
    Button addCount, subCount, btnPay,btnLoc;
    String KeyImgTour , KeyTotalPrice , KeyNameTour,KeyLoc,KeyCountItems,keyPriceTour="priceTour",keyDescTour;
    int mcount = 1;
    SharedPreferences preferences;
    Intent checkLocIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        imgTour = findViewById(R.id.imgTour);
        nameTour = findViewById(R.id.nameTour);
        descTour = findViewById(R.id.descTour);
        priceTour = findViewById(R.id.priceTour);
        txtCount = findViewById(R.id.txtCount);
        addCount = findViewById(R.id.btnAddCount);
        subCount = findViewById(R.id.btnSubCount);
        btnPay = findViewById(R.id.btnPay);
        btnLoc = findViewById(R.id.btnLoc);

        int KeyImageTour=getIntent().getIntExtra("image",1);
        String KeyNameTour=getIntent().getStringExtra("cityName");
        String KeyPriceTour=getIntent().getStringExtra("cityPrice");
        String KeyDescTour=getIntent().getStringExtra("cityDesc");

        nameTour.setText(KeyNameTour);
        priceTour.setText(KeyPriceTour);
        imgTour.setImageResource(KeyImageTour);
        descTour.setText(KeyDescTour);


        txtCount.setText(Integer.toString(mcount));

        addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcount++;
                txtCount.setText(Integer.toString(mcount));
                if (getIntent().hasExtra("priceTour")) {
                    int price_tour = getIntent().getIntExtra("priceTour", 0);
                    int total_price = price_tour * mcount;
                    priceTour.setText(Integer.toString(total_price));
                }
            }

        });
        subCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mcount > 1) {
                    mcount--;
                    txtCount.setText(Integer.toString(mcount));
                    int price_tour = getIntent().getIntExtra("priceTour", 0);
                    int total_price = price_tour * mcount;
                    priceTour.setText(Integer.toString(total_price));
                }
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price_tour = getIntent().getIntExtra("priceTour", 0);
                int price_value = price_tour;
                String imageValue = getIntent().getStringExtra("imgTour");
                String nameTourValue = nameTour.getText().toString();
                String totalItmesValue = priceTour.getText().toString();
                String totalPriceValue = priceTour.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KeyImgTour, imageValue);
                editor.putString(KeyNameTour, nameTourValue);
                editor.putString(KeyCountItems, totalItmesValue);
                editor.putString(keyPriceTour, String.valueOf(price_value));
                editor.putString(KeyTotalPrice, totalPriceValue);
                editor.apply();
                Intent intent = new Intent(TourDetailActivity.this, ReceiptActivity.class);
                startActivity(intent);
            }

        });
        /*btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("locTour")) {
                    String txtLoc = getIntent().getStringExtra("locTour");
                    Uri uri = Uri.parse("geo:0,0?q="+txtLoc );
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }
            }

        });*/
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLocIntent=new Intent(TourDetailActivity.this,fragLocation.class);
                startActivity(checkLocIntent);
            }
        });



    }

    private void getDataAdapter() {
        if (getIntent().hasExtra("imgTour") && getIntent().hasExtra("nameTour") && getIntent().hasExtra("descTour") && getIntent().hasExtra("priceTour")){
            ;
            String image_tour = getIntent().getStringExtra("imgTour");
            String name_tour = getIntent().getStringExtra("nameTour");
            String desc_tour = getIntent().getStringExtra("descTour");
            int price_tour = getIntent().getIntExtra("priceTour", 0);

            setDataDetail(image_tour, name_tour, desc_tour, price_tour);
        }

    }

    private void setDataDetail(String image_tour, String name_tour, String desc_tour, int price_tour) {
        // Glide.with(this).asBitmap().load(image_tour).into(imgTour);

        nameTour.setText(name_tour);
        descTour.setText(desc_tour);
        priceTour.setText(Integer.toString(price_tour));
    }

}