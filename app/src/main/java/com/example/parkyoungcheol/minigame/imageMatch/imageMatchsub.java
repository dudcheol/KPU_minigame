package com.example.parkyoungcheol.minigame.imageMatch;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkyoungcheol.minigame.Exit;
import com.example.parkyoungcheol.minigame.R;
import com.example.parkyoungcheol.minigame.Thread.Item;
import com.example.parkyoungcheol.minigame.Thread.matchThread;
import com.example.parkyoungcheol.minigame.Thread.matchThread;

import java.util.Random;

public class imageMatchsub extends AppCompatActivity {
    ImageView table0;
    ImageButton table[];

    com.example.parkyoungcheol.minigame.Thread.Item[] Item;
    Item answer;
    String[] resName;
    String packName;

    com.example.parkyoungcheol.minigame.Thread.matchThread matchThread;

    private int correct;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagematch_sub);
        // 정답 횟수 변수
        correct = 0;

        // 이미지가 들어갈 버튼 배열 선언
        table = new ImageButton[16];

        table0 = (ImageView)findViewById(R.id.table0);
        table[0] = (ImageButton)findViewById(R.id.table1);    table[1] = (ImageButton)findViewById(R.id.table2);    table[2] = (ImageButton)findViewById(R.id.table3);    table[3] = (ImageButton)findViewById(R.id.table4);
        table[4] = (ImageButton)findViewById(R.id.table5);    table[5]= (ImageButton)findViewById(R.id.table6);    table[6] = (ImageButton)findViewById(R.id.table7);    table[7] = (ImageButton)findViewById(R.id.table8);
        table[8] = (ImageButton)findViewById(R.id.table9);    table[9] = (ImageButton)findViewById(R.id.table10);    table[10] = (ImageButton)findViewById(R.id.table11);    table[11] = (ImageButton)findViewById(R.id.table12);
        table[12] = (ImageButton)findViewById(R.id.table13);    table[13] = (ImageButton)findViewById(R.id.table14);    table[14] = (ImageButton)findViewById(R.id.table15);    table[15] = (ImageButton)findViewById(R.id.table16);

        // 각 이미지의 리소스 ID를 담는 문자열 배열
        resName = new String[16];
        packName = getApplicationContext().getPackageName();

        for(int i=0; i<16; i++){
                resName[i] = packName+":"+"drawable/space"+(i+1);
        }

        // 각 이미지를 관리하는 CatItem 클래스 인스턴트 생성 및 초기화
        // Item은 각 이미지 리소스 관리, get,set 메소드를 수행한다.
        Item = new Item[16];
        for(int i=0; i<16; i++){
            Item[i] = new Item();
            Item[i].setId(0);
            Item[i].setDraw(getDrawable(getResources().getIdentifier(resName[i], "drawable", packName)));
        }

        CustomToast("미션 : 이미지를 10번 매치하시오.");

        // 최초 게임 세트.
        setItems(Item);

        // 이미지 애니메이션 동작을 위한 스레드 생성
        matchThread = new matchThread(table, Item);

        // UI 접근을 위한 핸들러 생성
        MatchHandler matchHandler = new MatchHandler();
        //  matchHandler.setDaemon(true);
        matchHandler.start();
    }

    // 매 게임마다 이미지 배치를 무작위로 수행
    // Item을 개체로 하여 무작위로 배열 위치를 바꾼다.
    public void shuffleArray(Item[] arr){
        Random rand = new Random();
        for(int i = 15; i>0; i--){
            int index = rand.nextInt(i+1);
            Item tmp = arr[index];
            arr[index] = arr[i];
            arr[i] = tmp;
        }
    }

    // 게임을 액티비티에 배치
    // 무작위로 섞여진 Item의 배열에서 다시 무작위로 Item을 뽑아 그 문제의 정답으로 정한다.
    public void setItems(Item[] arr){
        shuffleArray(arr);
        Random mRand = new Random();
        int randIdx = mRand.nextInt(16);
        answer = arr[randIdx];
        answer.setId(table[randIdx].getId());
        table0.setImageDrawable(answer.getDraw(0));
        for(int i=0; i<16; i++) {
            table[i].setImageDrawable(Item[i].getDraw(0));
        }
    }

    // 각 이미지버튼을 클릭하면 클릭한 Item과 정답의 Item이 일치하는지 비교
    public void selectIcon(View v){

        // 정답 횟수 도달 시 액티비티 종료, 그렇지 않으면 다음 문제 배치
        if(v.getId() == answer.getId()){
            if(++correct == 10){
                Intent i=new Intent(imageMatchsub.this,Exit.class);
                startActivity(i);
                finish();
            }
            else
                setItems(Item);
        }
        else{
            CustomToast_X("땡!");
        }
    }

    // 애니메이션을 위한 스레드 내부클래스 선언
    class MatchHandler extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                matchThread.sendEmptyMessage(0);
                try {
                    sleep(500);
                }
                catch (InterruptedException e) {}

                matchThread.sendEmptyMessage(1);
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
        CustomToast("모든 이미지를 매치해주세요.");
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
