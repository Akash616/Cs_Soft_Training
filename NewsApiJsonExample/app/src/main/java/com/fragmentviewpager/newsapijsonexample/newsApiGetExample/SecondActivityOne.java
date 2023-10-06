package com.fragmentviewpager.newsapijsonexample.newsApiGetExample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fragmentviewpager.newsapijsonexample.R;

public class SecondActivityOne extends AppCompatActivity {

    ImageView iv_news_ssecond;
    TextView tv_title_ssecond, tv_desc_sscreen, tv_content_sscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);

        iv_news_ssecond = findViewById(R.id.iv_news_sscreen);
        tv_title_ssecond = findViewById(R.id.tv_title_sscreen);
        tv_desc_sscreen = findViewById(R.id.tv_desc_sscreen);
        tv_content_sscreen = findViewById(R.id.tv_content_sscreen);

    }
}