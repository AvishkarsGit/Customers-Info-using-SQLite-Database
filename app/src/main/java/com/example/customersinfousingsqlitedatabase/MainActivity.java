package com.example.customersinfousingsqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText id,name,number,address,pin_code,id_res;
    Button save,read;
    TextView resultTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ID = Integer.parseInt(id.getText().toString());
                String NAME = name.getText().toString();
                String MOBILE = number.getText().toString();
                String ADDRESS = address.getText().toString();
                int PIN_CODE = Integer.parseInt(pin_code.getText().toString());
                boolean result = db.insertData(ID,NAME,MOBILE,ADDRESS,PIN_CODE);
                if (!result){
                    Toast.makeText(MainActivity.this, "Failed to insert", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Inserted successfully..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_result = Integer.parseInt(id_res.getText().toString());
                ArrayList<CustomerModel> arrayList =  db.getCustomers(id_result);
                for (int i=0;i<arrayList.size();i++){
                    resultTv.setText("Name:"+arrayList.get(i).name+"\nMobileNo:"+arrayList.get(i).mobile_no+"\nAddress:"+arrayList.get(i).address+"\nPin Code:"+arrayList.get(i).pin_code);
                }
            }
        });


    }


    private void initialize(){
        id = findViewById(R.id.edt_id);
        name =findViewById(R.id.edt_name);
        number = findViewById(R.id.edt_mobile);
        address = findViewById(R.id.edt_address);
        pin_code =findViewById(R.id.edt_pin);
        read = findViewById(R.id.read);
        save = findViewById(R.id.save);
        id_res =findViewById(R.id.edt_result_id);
//        listView =findViewById(R.id.lstView);
        resultTv =findViewById(R.id.tvResult);
    }

}