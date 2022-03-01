package com.example.dyla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLog, btnReg;
    EditText etLogin, etPass;
    SQLiteDatabase database;
    ContentValues contentValues;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLog = (Button) findViewById(R.id.btnLog);
        btnLog.setOnClickListener(this);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPass = (EditText) findViewById(R.id.etPass);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnLog:
            {
                Cursor cursor = database.query(DBHelper.TABLE_USER, null, null, null, null, null, null);
                boolean Log = false;
                if (TextUtils.isEmpty(etLogin.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_LONG).show();
                } else {
                    if (cursor.moveToFirst()) {
                        int NameID = cursor.getColumnIndex(DBHelper.NAME_USER);
                        int PassID = cursor.getColumnIndex(DBHelper.PASS_USER);
                        do {
                            if (etLogin.getText().toString().equals(cursor.getString(NameID))&&etPass.getText().toString().equals(cursor.getString(PassID)))
                            {
                                Log=true;
                                Toast.makeText(getApplicationContext(), "Добро пожаловать!", Toast.LENGTH_LONG).show();
                            }
                        }
                        while (cursor.moveToFirst());
                    }

                }
                cursor.close();
                if(!Log) Toast.makeText(getApplicationContext(), "Такого кликеруна не нашлось! Регайся живо!", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.btnReg:
            {
                Cursor cursor1 = database.query(DBHelper.TABLE_USER, null, null, null, null, null, null);
                boolean Find = false;
                if (TextUtils.isEmpty(etLogin.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_LONG).show();
                } else {
                    if (cursor1.moveToFirst()) {
                        int NameID = cursor1.getColumnIndex(DBHelper.NAME_USER);
                        do {
                            if (etLogin.getText().toString().equals(cursor1.getString(NameID)))
                            {
                                Find=true;
                                Toast.makeText(getApplicationContext(), "Такой ник уже есть!", Toast.LENGTH_LONG).show();
                            }
                        }
                        while (cursor1.moveToFirst());
                    }
                    if (!Find)
                    {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBHelper.NAME_USER,etLogin.getText().toString());
                        contentValues.put(DBHelper.PASS_USER,etPass.getText().toString());
                        database.insert(DBHelper.TABLE_USER,null,contentValues);
                        Toast.makeText(getApplicationContext(), "Зарегистрирован!", Toast.LENGTH_LONG).show();

                    }
                }
                cursor1.close();
                break;
            }
        }
    }
}