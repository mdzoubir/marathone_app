package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class datshAdmin extends AppCompatActivity {

    DBHelper db = new DBHelper(this);

    EditText name, email, phone, id;

    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datsh_admin);

        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        lst = findViewById(R.id.listView_data);
        id = findViewById(R.id.editID);
        showData();
    }


    public void add(View view) {

        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();

        if(Name.equals("") || Email.equals("") || Phone.equals("")){
            Toast.makeText(datshAdmin.this, "Form not complite", Toast.LENGTH_SHORT).show();
        }else{
            Boolean result = db.insertData(Name, Email, Phone);
            if (result == true) {
                Toast.makeText(datshAdmin.this, "ADD", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                phone.setText("");
                showData();
            } else {
                Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void showData(){
        ArrayList<String> listData = db.getAllUsers();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        lst.setAdapter(arrayAdapter);
    }

    public void edit(View view) {
        String Id = id.getText().toString();
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();

        if(Name.equals("") || Email.equals("") || Phone.equals("") || Id.equals("")){
            Toast.makeText(datshAdmin.this, "Form not complite", Toast.LENGTH_SHORT).show();
        }else{
            Boolean result = db.updateData(Id, Name, Email, Phone);
            if (result==true){
                Toast.makeText(datshAdmin.this, "UPDATE", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                phone.setText("");
                id.setText("");
                showData();
            }else {
                Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void delete(View view) {
        String Id = id.getText().toString();
        Integer result = db.deleteDate(Id);

        if(Id.equals("")){
            Toast.makeText(datshAdmin.this, "choose ID", Toast.LENGTH_SHORT).show();
        }else{
            if (result > 0){
                Toast.makeText(datshAdmin.this, "DELETE", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                phone.setText("");
                id.setText("");
                showData();
            }else{
                Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();
            }
        }

    }
}