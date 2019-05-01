package com.example.parkyoungcheol.minigame.Thread;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;

public class matchThread extends Handler {
    ImageButton[] table;
    Item[] Item;

    public matchThread(ImageButton[] table, Item[] Item){
        this.table = table;
        this.Item = Item;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
            for (int i = 0; i < 16; i++) {
                table[i].setImageDrawable(Item[i].getDraw(0));
            }
    }
}