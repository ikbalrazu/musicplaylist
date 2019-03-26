package com.example.user.musicplaylist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parseJsonData();

     /*   TextView textView=findViewById(R.id.register);
        final String text="Register Now!";
        SpannableString ss=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(MainActivity.this,"register work",Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(clickableSpan,0,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance()); */
      login=(Button)findViewById(R.id.loginbutton);
      signup=(Button)findViewById(R.id.signupbutton);

        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });
    }
/*
    void getDataFromApi(){
        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = Volley.newRequestQueue(MainActivity.this);

// Start the queue
        String url ="http://www.musicforever.ml/api/users";

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response : ", response);
                    }
                                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response : ", error.toString());
                    }
                });


// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

        requestQueue.start();
    }
*/ //end

public void parseJsonData(){

    String myurl="http://www.musicforever.ml/api/playlists";

    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, myurl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            Log.d("ttt","onResponse:"+response);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Log.d("ttt","onErrorResponse:"+error.getMessage());
        }

    });

    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

}
}
