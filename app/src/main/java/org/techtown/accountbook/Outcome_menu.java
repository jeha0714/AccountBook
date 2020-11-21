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

public class Outcome_menu extends AppCompatActivity {

    EditText et_outcome_day, et_outcome_price, et_outcome_memo;
    Button bt_outcome_menu;
    String date, price, memo;

    private TextView tv_outcome_day;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_menu);

        this.InitializeView();
        this.InitializeListner();
        //et_outcome_day = findViewById(R.id.et_outcome_day);
        et_outcome_price = findViewById(R.id.et_outcome_price);
        et_outcome_memo = findViewById(R.id.et_outcome_memo);

        bt_outcome_menu = findViewById(R.id.bt_outcome_menu);
        //입력 데이터 db로 이동
        bt_outcome_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //day = et_outcome_day.getText().toString();
                price = et_outcome_price.getText().toString();
                price = price.trim();
                memo = et_outcome_memo.getText().toString();

                date = tv_outcome_day.getText().toString();
                if(date.equals("지출 날짜 정보"))
                    Toast.makeText(Outcome_menu.this, "날짜를 선택하세요.",Toast.LENGTH_LONG).show();
                else if(price.getBytes().length <=0)
                    Toast.makeText(Outcome_menu.this, "금액을 입력하세요.",Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(Outcome_menu.this, InputFragment.class);
                    startActivity(intent);
                }
                //데이터베이스로 데이터 넘겨줌
            }
        });
    }
    public void InitializeView(){
        tv_outcome_day = (TextView)findViewById(R.id.tv_outcome_day);
    }
    public void InitializeListner(){
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_outcome_day.setText(year+"년"+(monthOfYear+1)+"월"+dayOfMonth+"일");
            }
        };
    }
    public void OnClickHandler(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 10, 21);
        dialog.show();
    }
}