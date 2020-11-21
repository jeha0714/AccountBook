package org.techtown.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Income_menu extends AppCompatActivity {

    EditText et_income_day, et_income_price, et_income_memo;
    Button bt_income_menu;
    String date, price, memo;

    private TextView tv_income_day;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    //DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_menu);

        this.InitializeView();
        this.InitializeListner();
        //et_income_day = findViewById(R.id.et_income_day);
        et_income_price = findViewById(R.id.et_income_price);
        et_income_memo = findViewById(R.id.et_income_memo);

        bt_income_menu = findViewById(R.id.bt_income_menu);
        //입력 데이터 db로 이동
        bt_income_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //day = et_income_day.getText().toString();
                price = et_income_price.getText().toString();
                memo = et_income_memo.getText().toString();

                date = tv_income_day.getText().toString();
                if(date.equals("수입 날짜 정보"))
                    Toast.makeText(Income_menu.this, "날짜를 선택하세요.",Toast.LENGTH_LONG).show();
                else if(price.getBytes().length <=0)
                    Toast.makeText(Income_menu.this, "금액을 입력하세요.",Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(Income_menu.this, MainActivity.class);
                    startActivity(intent);
                    //데이터베이스로 데이터 넘겨줌
                }
            }
        });
    }
    public void InitializeView(){
        tv_income_day = (TextView)findViewById(R.id.tv_income_day);
    }
    public void InitializeListner(){
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_income_day.setText(year+"년"+(monthOfYear+1)+"월"+dayOfMonth+"일");
            }
        };
    }
    public void OnClickHandler(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 10, 21);
        dialog.show();
    }

}