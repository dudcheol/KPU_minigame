package com.example.parkyoungcheol.minigame.Thread;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.widget.LinearLayout;

// 암산게임의 배경 애니메이션을 위한 핸들러 클래스
// 메인스레드로부터 메시지를 받아 UI에 접근, 변경 수행
public class mentalCalThread extends Handler {

    LinearLayout itemBoard;
    Drawable item1, item2;
    public mentalCalThread(LinearLayout itemBoard, Drawable item1, Drawable item2){
        this.itemBoard = itemBoard;
        this.item1 = item1;
        this.item2 = item2;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.what == 0) {
            itemBoard.setBackground(item1);
        }

        else{
            itemBoard.setBackground(item2);
        }
    }
}
