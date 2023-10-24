package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.healthlink.Doctors.Doctor;
import com.example.healthlink.Doctors.DoctorAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DoctorAdapter doctorAdapter;
    RecyclerView doctorRecyclerView;
    List<Doctor> doctors=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
        new FetchData().start();

    }

    private void initRecycleView() {

        doctorRecyclerView=findViewById(R.id.rv_doctors);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        doctorAdapter=new DoctorAdapter(doctors);
        doctorRecyclerView.setAdapter(doctorAdapter);

    }


    class FetchData extends Thread{

        String data= "";
        public void run() {
                try {
                    URL url =new URL("https://dummyjson.com/users");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");


                    InputStream inputStream= connection.getInputStream();
                    InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
                    BufferedReader in = new BufferedReader(inputStreamReader);


                    String line ;
                    while ((line = in.readLine()) != null ){

                        data = data +line;
                    }
//                  userList.clear()
                    if (!data.isEmpty()){
                        JSONObject jsonObject= new JSONObject(data);
                        JSONArray users = jsonObject.getJSONArray("users");
                        Log.i("API Parsing", String.valueOf(users.length())); //30


                        for (int i = 0 ; i< users.length();i++){

                            JSONObject doctor= users.getJSONObject(i);

                            String fullName =doctor.getString("firstName")+" "+doctor.getString("lastName");

                            JSONObject address = doctor.getJSONObject("address");
                            String city = address.getString("city") + " , " + address.getString("address");
                            String phone = doctor.getString("phone");
                            String imgSrc = doctor.getString("image");


                            final Doctor newDoctor = new Doctor(fullName, city, phone, imgSrc);

                            // Add the doctor to the list on the main thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    doctors.add(newDoctor);
                                    doctorAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
                                }
                            });
                        }
                        }


                }catch (JSONException | IOException e){
                    Log.e("API Parsing", String.valueOf(e));

                }


        }
    }
}




