package com.example.parkyoungcheol.minigame.imageMatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parkyoungcheol.minigame.R;
import com.example.parkyoungcheol.minigame.mentalCal.mentalCal;
import com.example.parkyoungcheol.minigame.mentalCal.mentalCalsub;

import java.util.Random;

public class imageMatch extends AppCompatActivity {
    Button howtoBtn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_match);

        //상단바 색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#FF5FB1FD"));
            }
        }

        howtoBtn = (Button)findViewById(R.id.howtoBtn);
        startBtn = (Button)findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(imageMatch.this,imageMatchsub.class);
                startActivity(intent);
            }
        });
    }
    public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(imageMatch.this);

        ad.setTitle("게임 방법");       // 제목 설정
        ad.setMessage("이미지 매치 게임!\n가장 위에 있는 그림과 같은 그림을 찾으세요.\n같은 이미지를 클릭하면 됩니다.");   // 내용 설정

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
