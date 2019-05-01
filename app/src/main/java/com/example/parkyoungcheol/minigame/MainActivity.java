package com.example.parkyoungcheol.minigame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkyoungcheol.minigame.imageMatch.imageMatch;
import com.example.parkyoungcheol.minigame.mentalCal.mentalCal;

public class MainActivity extends AppCompatActivity {
    // 어떤 게임인지 인식하기 위한 변수
    int gameNum=0;
    Integer[] imageIDs={
            R.drawable.mentalcal,R.drawable.imagematch,
            R.drawable.icon3,R.drawable.icon7,
            R.drawable.icon4,R.drawable.icon8,
            R.drawable.icon5,R.drawable.icon9,
            R.drawable.icon6,R.drawable.icon10,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        final TextView gameName = (TextView)findViewById(R.id.gameName);
        final String[] selectGameName = {null};
        Button btn = (Button)findViewById(R.id.mainbtn);

        //게임시작버튼 기능
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  null;
                switch (gameNum)
                {
                    case 0:
                        intent = new Intent(MainActivity.this,mentalCal.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,imageMatch.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "게임을 선택해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getBaseContext(), "클릭하면 다이알로그 띄우거나 해서 OOO게임 할거니? 예,아니오 이런식?", Toast.LENGTH_SHORT).show();
                gameNum=position;
                switch (position)
                {
                    case 0:
                        selectGameName[0] ="암산게임";
                        break;
                    case 1:
                        selectGameName[0] ="이미지 맞추기 게임";
                        break;
                }
                gameName.setText(selectGameName[0]);
                ImageView imageView = (ImageView)findViewById(R.id.image);
                imageView.setImageResource(imageIDs[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;
        public ImageAdapter(Context c){context = c;}
        public int getCount(){return imageIDs.length;}

        public Object getItem(int position){return position;}

        public long getItemId(int position){return position;}

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(400,400));
            //imageView.setBackgroundColor(Color.WHITE);
            return imageView;
        }
    }
}