package org.techtown.accountbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import java.util.Calendar;

public class CalendarFragment extends Fragment{

    CalendarView calendarView;
    LinearLayout floating_view;
    RelativeLayout calendar_layout;
    LinearLayout floating_view_background;
    TextView tv_day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calender,container,false);

        calendarView = rootView.findViewById(R.id.calendarView);
        calendar_layout = rootView.findViewById(R.id.calendar_layout);
        floating_view = rootView.findViewById(R.id.floating_view);
        floating_view_background = rootView.findViewById(R.id.floating_view_background);
        tv_day = rootView.findViewById(R.id.tv_day);


        //날짜 클릭시 이벤트
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //floating_view 켜져있을때
                if(floating_view.getVisibility() == View.VISIBLE){
                    floating_view.setVisibility(View.INVISIBLE);
                    floating_view_background.setVisibility(View.INVISIBLE);
                }else { //flaoting_view 꺼져있을 때
                    floating_view_background.setVisibility(View.VISIBLE);
                    floating_view.setVisibility(View.VISIBLE);


                    //선택된 날짜 받아오기 + 설정
                    String day_text = year + "/" + month + "/" + dayOfMonth;
                    tv_day.setText(day_text);


                }
            }

        });

        //배경 클릭시 이벤트
        floating_view_background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // floating_view 끄기
                floating_view.setVisibility(View.INVISIBLE);
                floating_view_background.setVisibility(View.INVISIBLE);
                return true;
            }
        });


        return rootView;
    }

}
