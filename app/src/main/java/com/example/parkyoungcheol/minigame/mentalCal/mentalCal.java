package com.example.parkyoungcheol.minigame.mentalCal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.parkyoungcheol.minigame.MainActivity;
import com.example.parkyoungcheol.minigame.R;

public class mentalCal extends AppCompatActivity {
    Button howtoBtn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentalcal);
//상단바 색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#FFF9BBCD"));
            }
        }

        howtoBtn = (Button)findViewById(R.id.howtoBtn);
        startBtn = (Button)findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mentalCal.this,mentalCalsub.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(mentalCal.this);

        ad.setTitle("게임 방법");       // 제목 설정
        ad.setMessage("암산왕이 되어보세요!\n화면에 표시되는 사칙연산을 확인하고 정답을 입력하세요.\n정답을 입력하고 [입력]버튼을 클릭해야합니다.");   // 내용 설정

        // 확인 버튼 설정
        ad.setPositiveButton("알겠습니다~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
                // Event
            }
        });

        /*// 중립 버튼 설정
        ad.setNeutralButton("What?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
                // Event
            }
        });

        // 취소 버튼 설정
        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
                // Event
            }
        });*/

        // 창 띄우기
        ad.show();
    }
}
