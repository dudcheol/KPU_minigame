package com.example.parkyoungcheol.minigame.mentalCal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkyoungcheol.minigame.R;
import com.example.parkyoungcheol.minigame.Thread.mentalCalThread;
import com.example.parkyoungcheol.minigame.Exit;

import java.util.Random;

public class mentalCalsub extends AppCompatActivity {
    private int oprtType;
    private int num1, num2;
    private int answer;
    private int corrected;

    TextView txtOpnd1, txtOpnd2, txtOprt;
    EditText edtAnswer;
    Button btnAnswer;

    LinearLayout liftingCat;

    Random mRand;

    mentalCalThread calculateThread;
    Drawable cat1, cat2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentalcal_sub);

        //상단바 색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#FF774322"));
            }
        }


        mRand = new Random();

        txtOpnd1 = (TextView)findViewById(R.id.txtOpnd1);
        txtOpnd2 = (TextView)findViewById(R.id.txtOpnd2);
        txtOprt = (TextView)findViewById(R.id.txtOprt);
        edtAnswer = (EditText)findViewById(R.id.editAnswer);
        btnAnswer = (Button)findViewById(R.id.btnAnswer);

        liftingCat = (LinearLayout)findViewById(R.id.liftingCat);
    /*    cat1 = getDrawable(R.drawable.cat_lifting);
        cat2 = getDrawable(R.drawable.cat_lifting1);*/

        // 정답 횟수 선언(5회 정답시 알람 종료), 최초 문제 set.
        corrected = 0;
        setQuiz();

        // 커스텀 토스트
       CustomToast("총 5문제를 맞추지 못하면 나갈 수 없습니다!");

        // 버튼을 누르면 정답과 입력값을 비교, 정답을 판단한다.
        btnAnswer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(edtAnswer.getText().toString().isEmpty())
                    CustomToast("정답을 입력하세요.");

                else {
                    switch (oprtType) {
                        case 1:
                            if (Integer.parseInt(edtAnswer.getText().toString()) == answer) {
                                CustomToast_O("O");
                                // 정답 시 정답횟수 변수 correct++
                                corrected++;
                            } else
                                CustomToast_X("X");

                            break;
                        case 2:
                            if (Integer.parseInt(edtAnswer.getText().toString()) == answer) {
                                CustomToast_O("O");
                                corrected++;
                            } else
                                CustomToast_X("X");

                            break;
                        case 3:
                            if (Integer.parseInt(edtAnswer.getText().toString()) == answer) {
                                CustomToast_O("O");
                                corrected++;
                            } else
                                CustomToast_X("X");

                            break;
                    }
                }

                // 정해진 정답 횟수(5)에 도달하면 게임 액티비티를 종료.
                if(corrected == 5){
                    Intent i=new Intent(mentalCalsub.this,Exit.class);
                    startActivity(i);
                    finish();
                }
                else
                    // 새로운 문제 set
                    setQuiz();

                edtAnswer.getText().clear();
            }
        });

        // 배경 애니메이션을 위한 쓰레드 선언
        calculateThread = new mentalCalThread(liftingCat, cat1, cat2);

        // UI에 접근하기 위해 핸들러를 사용
        mentalCalsub.CalculateHandler matchHandler = new mentalCalsub.CalculateHandler();
        //  matchHandler.setDaemon(true);
        matchHandler.start();
    }
    public void setQuiz(){
        oprtType = mRand.nextInt(3)+1;
        switch(oprtType){
            case 1:
                txtOprt.setText("+");
                num1 = mRand.nextInt(89)+10;
                num2 = mRand.nextInt(89)+10;
                txtOpnd1.setText(Integer.toString(num1));
                txtOpnd2.setText(Integer.toString(num2));

                answer = num1+num2;
                break;
            case 2:
                txtOprt.setText("-");
                num1 = mRand.nextInt(89)+10;
                num2 = mRand.nextInt(num1-10)+10;
                txtOpnd1.setText(Integer.toString(num1));
                txtOpnd2.setText(Integer.toString(num2));

                answer = num1 - num2;
                break;
            case 3:
                txtOprt.setText("X");
                num1 = mRand.nextInt(10)+10;
                num2 = mRand.nextInt(10)+10;
                txtOpnd1.setText(Integer.toString(num1));
                txtOpnd2.setText(Integer.toString(num2));

                answer = num1 * num2;
                break;
        }
    }

    // 메인스레드는 UI에 접근하지 못하므로 스레드에서 핸들러로 메시지를 보내어 애니메이션을 수행
    class CalculateHandler extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                calculateThread.sendEmptyMessage(0);
                try {
                    sleep(500);
                }
                catch (InterruptedException e) {}

                calculateThread.sendEmptyMessage(1);
                try {
                    sleep(500);
                }
                catch (InterruptedException e) {}
            }
        }
    }

    // 게임 중 뒤로가기 버튼 비활성화
    @Override
    public void onBackPressed() {
        CustomToast("비겁하게 도망가시는 건가요? ㅋㅋ");
    }


    //  커스텀토스트
    public void CustomToast(String s){
        LayoutInflater inflater = getLayoutInflater();
        View toastDesign = inflater.inflate(R.layout.toast_layout, (ViewGroup)findViewById(R.id.toast_layout)); //toast_design.xml 파일의 toast_design_root 속성을 로드

        TextView text = toastDesign.findViewById(R.id.toast_text);
        text.setText(s); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0); // CENTER를 기준으로 0, 0 위치에 메시지 출력
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastDesign);
        toast.show();
    }

    public void CustomToast_O(String s){
        LayoutInflater inflater = getLayoutInflater();
        View toastDesign = inflater.inflate(R.layout.toast_o, (ViewGroup)findViewById(R.id.toast_o)); //toast_design.xml 파일의 toast_design_root 속성을 로드

        TextView text = toastDesign.findViewById(R.id.toast_text);
        text.setText(s); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0); // CENTER를 기준으로 0, 0 위치에 메시지 출력
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastDesign);
        toast.show();
    }

    public void CustomToast_X(String s){
        LayoutInflater inflater = getLayoutInflater();
        View toastDesign = inflater.inflate(R.layout.toast_x, (ViewGroup)findViewById(R.id.toast_x)); //toast_design.xml 파일의 toast_design_root 속성을 로드

        TextView text = toastDesign.findViewById(R.id.toast_text);
        text.setText(s); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0); // CENTER를 기준으로 0, 0 위치에 메시지 출력
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastDesign);
        toast.show();
    }
}
