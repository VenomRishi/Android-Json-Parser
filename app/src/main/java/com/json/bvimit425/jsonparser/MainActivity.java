package com.json.bvimit425.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public String getFileContents() throws IOException {
        InputStream stream = getAssets().open("persons.json");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String jsonContent = getFileContents();
            getContents(jsonContent);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void getContents(String jsonContent) throws JSONException {
        JSONObject root = new JSONObject(jsonContent);
        JSONArray persons = root.getJSONArray("persons");
        if (persons != null && persons.length() > 0) {
            JSONObject firstPerson = persons.getJSONObject(0);
            String name = firstPerson.getString("name");
            int age = firstPerson.getInt("age");
            String address = firstPerson.getString("address");
            JSONArray companies = firstPerson.getJSONArray("companies");
            if (companies != null && companies.length() > 1) {
                JSONObject comapany2 = companies.getJSONObject(1);
                String company2Name = comapany2.getString("name");
                String company2Location = comapany2.getString("location");
                Toast.makeText(this, "Name  : " + name + "\nAge : " + age +
                        "\nAddress : " + address + "\nCompany Name : " + company2Name + "\nCompany Location : " + company2Location, Toast.LENGTH_LONG).show();
            }
        }
    }
}
