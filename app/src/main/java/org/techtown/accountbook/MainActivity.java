package org.techtown.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CalenderFragment calenderFragment;
    InputFragment inputFragment;

    Spinner sp_main;    //날짜 달력 스피너

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_main = findViewById(R.id.sp_main);
        //스피너 값에 따른 fragment 변환
        sp_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: //0 is calender
                        setFlags(0);    //fragment 바꿔주는 함수
                        break;
                    case 1: //1 is 나열
                        setFlags(1);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //나열 달력 fragment 변환
    public void setFlags(int position){
        switch (position){
            case 0:
                calenderFragment = new CalenderFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,calenderFragment).commit();
                break;
            case 1:
                inputFragment = new InputFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,inputFragment).commit();
                break;
        }
    }
}