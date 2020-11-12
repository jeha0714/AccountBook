package org.techtown.accountbook;

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
                toggleFab();
                break;
            case R.id.fab_income:
                toggleFab();    //애니메이션 실행 함수
                break;
            case R.id.fab_outcome:
                toggleFab();
                break;
        }
    }

    boolean isFabOpen = false;

    //애니매이션 함수
    public void toggleFab(){
        //fab가 열러있을때
        if (isFabOpen) {
            fab_plus.setImageResource(R.drawable.open);
            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);  //fab_close 불러옴
            fab_income.startAnimation(anim);
            fab_outcome.startAnimation(anim);
            fab_income.setClickable(false);
            fab_outcome.setClickable(false);
            isFabOpen = false;
        } else {    //fab 닫혀있을때
            fab_plus.setImageResource(R.drawable.close);
            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_open);   //fab_open 불러옴
            fab_income.startAnimation(anim);
            fab_outcome.startAnimation(anim);
            fab_income.setClickable(true);
            fab_outcome.setClickable(true);
            isFabOpen = true;
        }
    }
}
