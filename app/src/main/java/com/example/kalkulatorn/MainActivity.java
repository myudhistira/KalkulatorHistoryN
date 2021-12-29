package com.example.kalkulatorn;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    TextView input1, input2, result, notif;
    Button btHitung, btJumlah, btKurang, btKali, btBagi;
    private ArrayAdapter<String> THistory;
    SharedPreferences sharedPreferences;
    private int operation = 0;
    private double HasilAkhir = 0.0;
    private String Cek1 = "";
    private String Cek2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        THistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(THistory);
        input1 = (TextView) findViewById(R.id.masukan1);
        input2 = (TextView) findViewById(R.id.masukan2);
        result = (TextView) findViewById(R.id.hasil);
        notif = (TextView) findViewById(R.id.notifikasi);
        btHitung = (Button) findViewById(R.id.hitung);
        btJumlah = (Button) findViewById(R.id.tambah);
        btKurang = (Button) findViewById(R.id.kurang);
        btKali = (Button) findViewById(R.id.kali);
        btBagi = (Button) findViewById(R.id.bagi);
    }
    public void klikTambah(View V){
        operation = 1;
    }
    public void klikKurang(View V){
        operation = 2;
    }
    public void klikKali(View V){
        operation = 3;
    }
    public void klikBagi(View V){
        operation = 4;
    }
    public void klikHasil(View V){
        Cek1 = input1.getText().toString();
        Cek2 = input2.getText().toString();
        if((Cek1.equalsIgnoreCase("")) || (Cek2.equalsIgnoreCase(""))){
            notif.setText("Kolom tidak boleh kosong");
        }
        else{
            double inputA = Double.parseDouble(input1.getText().toString());
            double inputB = Double.parseDouble(input2.getText().toString());
            switch(operation){
                case 1:
                    HasilAkhir = inputA + inputB;
                    THistory.add(inputA + " + " + inputB +" = "+ HasilAkhir);
                    break;
                case 2:
                    HasilAkhir = inputA - inputB;
                    THistory.add(inputA + " - " + inputB +" = "+ HasilAkhir);
                    break;
                case 3:
                    HasilAkhir = inputA * inputB;
                    THistory.add(inputA + " * " + inputB +" = "+ HasilAkhir);
                    break;
                case 4:
                    HasilAkhir = inputA / inputB;
                    THistory.add(inputA + " / " + inputB +" = "+ HasilAkhir);
                    break;
                case 0:
                    notif.setText("Pilih operasi perhitungan dahulu!");
                    break;
                default:
                    notif.setText("Undescribeable Error!");
                    break;
            }
            if(operation < 1){
                result.setText("0");
            }
            else{
                String hasilString = String.valueOf(HasilAkhir);
                result.setText(hasilString);
                notif.setText("Kalkulator App");
            }

                sharedPreferences = getSharedPreferences("input1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("input1", String.valueOf(input1));
            editor.putString("input2", String.valueOf(input2));
            editor.commit();
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

        }
    }
    public void clearInput1(View V){
        input1.setText("");
        operation = 0;
    }
    public void clearInput2(View V){
        input2.setText("");
        operation = 0;
    }

}