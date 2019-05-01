package com.example.parkyoungcheol.minigame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.parkyoungcheol.minigame.game3;


public class game3index extends AppCompatActivity {
    Button howtoBtn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3index);
//상단바 색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#FFF9BBCD"));
            }
        }

        howtoBtn = (Button) findViewById(R.id.howtoBtn);
        startBtn = (Button) findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game3index.this, game3.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(game3index.this);

        ad.setTitle("게임 방법");       // 제목 설정
        ad.setMessage("멍 떄리며 비눗방울을 누르세요!\n화면에 원하는 장소를 클릭하면 비눗방울이 만들어집니다.\n비눗방울을 다시 누르면 터집니다.");   // 내용 설정

        // 확인 버튼 설정
        ad.setPositiveButton("알겠습니다~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
                // Event
            }
        });
    }
}
