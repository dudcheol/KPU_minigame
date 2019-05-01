package com.example.parkyoungcheol.minigame.Thread;

import android.graphics.drawable.Drawable;

public class Item {
    protected int id;
    Drawable img[];

    public Item(){
        this.id = 0;
        this.img = new Drawable[2];

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setDraw(Drawable img1){
        this.img[0] = img1;
    }

    public Drawable getDraw(int idx){
        return img[idx];
    }
}
