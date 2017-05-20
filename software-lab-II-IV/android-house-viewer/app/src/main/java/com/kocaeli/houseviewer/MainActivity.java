package com.kocaeli.houseviewer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kocaeli.houseviewer.entity.House;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String HOUSES_URL = "http://ferdielik.me:8081";
    List<House> houseList =new ArrayList<>();

    ListView listView;
    private static CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateHouseList();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        listView = (ListView) findViewById(R.id.list);

        adapter = new CustomAdapter(houseList, getApplicationContext());

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                House house = houseList.get(position);


                Snackbar.make(view, house.getDescription() + "\n" + house.getType() + " Price: " + house.getPrice(), Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();

                try {
                    updateHouseList();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateHouseList() // todo: surekli calismali
    {
        StringRequest myReq = new StringRequest(Request.Method.GET, HOUSES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<House>>() {
                }.getType();

                houseList = gson.fromJson(response, listType);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {

        };
        myReq.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(myReq);
    }




}