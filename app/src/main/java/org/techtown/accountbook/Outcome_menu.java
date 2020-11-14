package org.techtown.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Outcome_menu extends AppCompatActivity {

    EditText et_outcome_day, et_outcome_price, et_outcome_memo;
    Button bt_outcome_menu;
    String day, price, memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_menu);

        et_outcome_day = findViewById(R.id.et_outcome_day);
        et_outcome_price = findViewById(R.id.et_outcome_price);
        et_outcome_memo = findViewById(R.id.et_outcome_memo);

        bt_outcome_menu = findViewById(R.id.bt_outcome_menu);
        //입력 데이터 db로 이동
        bt_outcome_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = et_outcome_day.getText().toString();
                price = et_outcome_price.getText().toString();
                memo = et_outcome_memo.getText().toString();

                //데이터베이스로 데이터 넘겨줌
            }
        });
    }
}