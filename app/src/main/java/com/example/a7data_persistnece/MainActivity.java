package com.example.a7data_persistnece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {




    final String FILE_NAME = "file.txt";
    EditText editText;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = findViewById(R.id.data_Et);
        tv = findViewById(R.id.textView2);
    }

    public void saveData(View view) {


        //writeFileToInternalStorage();
        writeFileToExtrnalSt();


        readFileFromAssets();


    }

    private void writeFileToExtrnalSt(){
        File path = getExternalFilesDir("MyFolder");
        File file = new File(path, FILE_NAME);
        String data = editText.getText().toString();


        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
            editText.setText("");
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void writeFileToInternalStorage() {
        String data = editText.getText().toString();


        try {


            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.write("\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("file error", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(View view) {



        readFileFromAssets();


        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);

            Scanner sc = new Scanner(fis);
            String line = "";

            while (sc.hasNextLine())
            {
                line = sc.nextLine();
                stringBuilder.append(line).append("\n");

            }
            sc.close();
            String data = stringBuilder.toString();
            tv.setText(data);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void readFileFromAssets(){

        try {
            InputStreamReader fis = new InputStreamReader(getAssets().open("file.txt"));


            Scanner sc = new Scanner(fis);
            String line = "";

            while (sc.hasNextLine())
            {
                line = sc.nextLine();

                Log.d("Name", line);
            }
            sc.close();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}