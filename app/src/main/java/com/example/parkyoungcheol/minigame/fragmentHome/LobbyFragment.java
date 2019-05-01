package com.example.parkyoungcheol.minigame.fragmentHome;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.parkyoungcheol.minigame.R;
import com.example.parkyoungcheol.minigame.game3index;
import com.example.parkyoungcheol.minigame.imageMatch.imageMatch;
import com.example.parkyoungcheol.minigame.mentalCal.mentalCal;

/**
 * A simple {@link Fragment} subclass.
 */
public class LobbyFragment extends Fragment {
    // 어떤 게임인지 인식하기 위한 변수
    int gameNum=0;
    ImageView imageView;
    Integer[] imageIDs={
            R.drawable.mentalcal,R.drawable.imagematch,R.drawable.bubbleicon,R.drawable.imagematch
    };

    public LobbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lobby, container, false);

        Gallery gallery = (Gallery)view.findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(view.getContext()));
        final TextView gameName = (TextView)view.findViewById(R.id.gameName);
        final String[] selectGameName = {null};
        Button btn = (Button)view.findViewById(R.id.mainbtn);
        imageView = (ImageView)view.findViewById(R.id.image);

        //게임시작버튼 기능
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  null;
                switch (gameNum)
                {
                    case 0:
                        intent = new Intent(getActivity(),mentalCal.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(),imageMatch.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), game3index.class);
                        startActivity(intent);
                        break;
                    /*case 3:
                        intent = new Intent(getActivity(), game4.class);
                        startActivity(intent);
                        break;*/
                    default:
                        Toast.makeText(getActivity(), "게임을 선택해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gameNum=position;
                switch (position)
                {
                    case 0:
                        selectGameName[0] ="암산게임";
                        break;
                    case 1:
                        selectGameName[0] ="이미지 맞추기 게임";
                        break;
                    case 2:
                        selectGameName[0] = "비눗방울 게임";
                }
                gameName.setText(selectGameName[0]);
                imageView.setImageResource(imageIDs[position]);
            }
        });

        return view;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        public ImageAdapter(Context c){context = c;}
        public int getCount(){return imageIDs.length;}

        public Object getItem(int position){return position;}

        public long getItemId(int position){return position;}

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(200 ,200));

            //imageView.setBackgroundColor(Color.WHITE);
            return imageView;
        }
    }
}
