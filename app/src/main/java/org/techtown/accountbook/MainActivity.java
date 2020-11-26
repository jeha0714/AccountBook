package org.techtown.accountbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Spinner moneySpinner;
    private Spinner graphSpinner;

    private final String[] graphChoose = {"원그래프", "막대그래프", "나열"};
    private final String[] moneyShowChoose = {"전체", "잔액", "지출", "수입", "수입&지출"};

    //막대그래프 표현 필드
    private LinearLayout graph_bar_and_moneyAll;
    private LinearLayout graph_bar_and_moneyBalance;
    private LinearLayout graph_bar_and_moneySpending;
    private LinearLayout graph_bar_and_moneyIncome;
    private LinearLayout graph_bar_and_moneyIncomeandSpending;

    //나열 표현 필드
    private LinearLayout graph_list_and_moneyAll;
    private LinearLayout graph_list_and_moneyBalance;
    private LinearLayout graph_list_and_moneySpending;
    private LinearLayout graph_list_and_moneyIncome;
    private LinearLayout graph_list_and_moneyIncomeandSpending;

    //원그래프 표현 필드
    private LinearLayout graph_circle_and_moneyAll;
    private LinearLayout graph_circle_and_moneyBalance;
    private LinearLayout graph_circle_and_moneySpending;
    private LinearLayout graph_circle_and_moneyIncome;
    private LinearLayout graph_circle_and_moneyIncomeandSpending;

    private int graphNum;
    private int moneyNum;

    //세미씨 코드
    private ProgressBar pieChart1;
    private ProgressBar pieChart2;
    private ProgressBar pieChart3;

    private TextView status_number1;
    private TextView status_number2;
    private TextView status_number3;

    private TextView cash_number1;
    private TextView cash_number2;
    private TextView cash_number3;
    //세미씨 코드

    CalendarFragment calendarFragment;
    InputFragment inputFragment;
    LinearLayout floating_view;
    long backBthTime = 0;

    Spinner sp_main;    //날짜 달력 스피너

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart1 = (ProgressBar)findViewById(R.id.status_progressbar1);
        pieChart2 = (ProgressBar)findViewById(R.id.status_progressbar2);
        pieChart3 = (ProgressBar)findViewById(R.id.status_progressbar3);

        status_number1= (TextView)findViewById(R.id.status_number1);
        status_number2 = (TextView)findViewById(R.id.status_number2);
        status_number3 = (TextView)findViewById(R.id.status_number3);

        cash_number1 = (TextView)findViewById(R.id.cash_number1);
        cash_number2 = (TextView)findViewById(R.id.cash_number2);
        cash_number3 = (TextView) findViewById(R.id.cash_number3);

        pieChart1.setProgress(0);
        pieChart2.setProgress(0);
        pieChart3.setProgress(0);

        //버튼클릭함수(resultonactivity))반영{  }
//        cashUpdate(10000, 2000, 2000);  //금액 먼저 업데이트후 차트 업데이트
        updateChart();

        graph_bar_and_moneyAll = findViewById(R.id.graph_bar_and_moneyAll);
        graph_bar_and_moneyBalance = findViewById(R.id.graph_bar_and_moneyBalance);
        graph_bar_and_moneySpending = findViewById(R.id.graph_bar_and_moneySpending);
        graph_bar_and_moneyIncome = findViewById(R.id.graph_bar_and_moneyIncome);
        graph_bar_and_moneyIncomeandSpending = findViewById(R.id.graph_bar_and_moneyIncomeandSpending);

        graph_list_and_moneyAll = findViewById(R.id.graph_list_and_moneyAll);
        graph_list_and_moneyBalance = findViewById(R.id.graph_list_and_moneyBalance);
        graph_list_and_moneySpending = findViewById(R.id.graph_list_and_moneySpending);
        graph_list_and_moneyIncome = findViewById(R.id.graph_list_and_moneyIncome);
        graph_list_and_moneyIncomeandSpending = findViewById(R.id.graph_list_and_moneyIncomeandSpending);

        graph_circle_and_moneyAll = findViewById(R.id.graph_circle_and_moneyAll);
        graph_circle_and_moneyBalance = findViewById(R.id.graph_circle_and_moneyBalance);
        graph_circle_and_moneySpending = findViewById(R.id.graph_circle_and_moneySpending);
        graph_circle_and_moneyIncome = findViewById(R.id.graph_circle_and_moneyIncome);
        graph_circle_and_moneyIncomeandSpending = findViewById(R.id.graph_circle_and_moneyIncomeandSpending);


        moneySpinner = findViewById(R.id.moneySpinner);

        moneySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moneyNum = position;

                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                }


                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }

                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        // 위는 돈 목록 아래는 그래프 .


        graphSpinner = findViewById(R.id.graphSpinner);

        graphSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphNum = position;

                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("원그래프") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                }


                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("막대그래프") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }

                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("전체")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.VISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("잔액")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.VISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("수입")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.VISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
                if (graphChoose[graphNum].equals("나열") && moneyShowChoose[moneyNum].equals("수입&지출")) {
                    graph_bar_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_bar_and_moneyIncomeandSpending.setVisibility(View.VISIBLE);
                    graph_list_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_list_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_list_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyAll.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyBalance.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneySpending.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncome.setVisibility(View.INVISIBLE);
                    graph_circle_and_moneyIncomeandSpending.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        sp_main = findViewById(R.id.sp_main);
        //스피너 값에 따른 fragment 변환
        sp_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //0 is calender
                        setFlags(0);    //fragment 바꿔주는 함수
                        break;
                    case 1: //1 is 나열
                        setFlags(1);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //나열과 달력의 fragment 변환
    public void setFlags(int position) {
        switch (position) {
            //open calendarFragment
            case 0:
                calendarFragment = new CalendarFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, calendarFragment).commit();
                break;
            //open inputFragment
            case 1:
                inputFragment = new InputFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, inputFragment).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBthTime;


        if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        } else {
            if (floating_view.getVisibility() == View.VISIBLE) {
                floating_view.setVisibility(View.INVISIBLE);
            }
            backBthTime = curTime;
            Toast.makeText(this, "한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    public void cashUpdate(int a, int b, int c){

        int currentCash = a;
        int inputCash = b;
        int outputCash = c;

        int i_percent = 0;
        int o_percent = 0;

        i_percent = (inputCash / currentCash) * 100;
        o_percent = (outputCash / currentCash) * 100;

        cash_number1.setText(currentCash);   //잔액, 수입, 지출 새로 업데이트
        cash_number2.setText(inputCash);
        cash_number3.setText(outputCash);


        cash_number1.setText(currentCash + inputCash);//지출하고 수입에 변화가 있을시..
        cash_number1.setText(currentCash - outputCash);

        //넘버 1은 항상 100으로 설정
        status_number2.setText(i_percent);
        status_number3.setText(o_percent);


    }

    public void updateChart(){

        progress(100);  //퍼센트 계산해서 넘겨주기

    }

    //동시에..
    public void progress(final int value){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= value ; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int percent = i;
                    status_number1.post(new Runnable() {
                        @Override
                        public void run() {
                            pieChart1.setProgress(percent);
                            status_number1.setText(percent + "%");
                        }
                    });
                }

                for(int i = 0; i <= 20 ; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int percent = i;
                    status_number2.post(new Runnable() {
                        @Override
                        public void run() {
                            pieChart2.setProgress(percent);
                            status_number2.setText(percent + "%");
                        }
                    });
                }

                for(int i = 0; i <= 80 ; i++) {  //익명클래스,
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int percent = i;
                    status_number3.post(new Runnable() {
                        @Override
                        public void run() {
                            pieChart3.setProgress(percent);
                            status_number3.setText(percent + "%");
                        }
                    });
                }
            }
        }).start();
    }



}



