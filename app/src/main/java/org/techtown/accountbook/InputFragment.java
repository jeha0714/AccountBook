package org.techtown.accountbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.acl.Group;


public class InputFragment extends Fragment implements View.OnClickListener{

    //floating-action-bar
    FloatingActionButton fab_plus, fab_income,fab_outcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.input,container,false);

        fab_plus = rootView.findViewById(R.id.fab_plus);
        fab_income = rootView.findViewById(R.id.fab_income);
        fab_outcome = rootView.findViewById(R.id.fab_outcome);

        fab_plus.setOnClickListener(this);
        fab_income.setOnClickListener(this);
        fab_outcome.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_plus:
                toggleFab();    //fab 창 올라오는 애니메이션 실행
                break;
            case R.id.fab_income:
                openInputmenu(0);   // 0 는 income 입력 메뉴로
                break;
            case R.id.fab_outcome:
                openInputmenu(1);   // 1은 outcome 입력 메뉴로
                break;
        }
    }

    boolean isFabOpen = false;

    //애니매이션 함수
    public void toggleFab(){
        //fab가 열려있을때 닫는 기능
        if (isFabOpen) {
            fab_plus.setImageResource(R.drawable.open); //fab 이미지 설정
            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);  //fab_close 불러옴
            fab_income.startAnimation(anim);    // income fab 열림
            fab_outcome.startAnimation(anim);   // outcome fab 열림
            fab_income.setClickable(false);     // 클릭 불가
            fab_outcome.setClickable(false);
            isFabOpen = false;
        } else {    //fab 닫혀있을때 여는 기능
            fab_plus.setImageResource(R.drawable.close);
            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_open);   //fab_open 불러옴
            fab_income.startAnimation(anim);
            fab_outcome.startAnimation(anim);
            fab_income.setClickable(true);  // 클릭 가능
            fab_outcome.setClickable(true);
            isFabOpen = true;
        }
    }

    public void openInputmenu(int option){
        switch (option){
            //go to income_menu
            case 0:
                Intent intent1 = new Intent(getActivity(), Income_menu.class);
                startActivityForResult(intent1,101);
                break;
            //go to outcome_menu
            case 1:
                Intent intent2 = new Intent(getActivity(), Outcome_menu.class);
                startActivityForResult(intent2,102);
                break;
        }
    }
}
