package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.healthlink.R;
import com.example.healthlink.profileSetting.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchData().start();
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


//                        onBoardingItems.add(new OnBoardingItem("title","disc",img));

//                        for (int i = 0 ; i< users.length();i++){
//                            JSONObject names= users.getJSONObject(i);
//                            String name =names.getString("firstName");
//                            userList.add(name);
//
//                        }
                    }

//                    line = line.replace("quoteText", "text");
//                    line = line.replace("quoteAuthor", "author");


                    Gson gson = new Gson();
                    User quotesAndAuthor = gson.fromJson(line, User.class);


//                while (line!=null){
//                    System.out.println(line);
//                    line=in.readLine();
//                    in.close();
//                }


                }catch (Exception e){
                    Log.e("API Parsing", "error");

                }


            }
    }
}




