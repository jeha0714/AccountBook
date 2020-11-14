package org.techtown.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Income_menu extends AppCompatActivity {

    EditText et_income_day, et_income_price, et_income_memo;
    Button bt_income_menu;
    String day, price, memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_menu);

        et_income_day = findViewById(R.id.et_income_day);
        et_income_price = findViewById(R.id.et_income_price);
        et_income_memo = findViewById(R.id.et_income_memo);

        bt_income_menu = findViewById(R.id.bt_income_menu);
        //입력 데이터 db로 이동
        bt_income_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = et_income_day.getText().toString();
                price = et_income_price.getText().toString();
                memo = et_income_memo.getText().toString();

                //데이터베이스로 데이터 넘겨줌
            }
        });
    }

}