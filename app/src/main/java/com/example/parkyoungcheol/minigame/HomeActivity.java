package com.example.parkyoungcheol.minigame;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkyoungcheol.minigame.fragmentHome.IntroduceFragment;
import com.example.parkyoungcheol.minigame.fragmentHome.KPUFragment;
import com.example.parkyoungcheol.minigame.fragmentHome.LobbyFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fr = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fr = new IntroduceFragment();
                    break;
                case R.id.navigation_dashboard:
                    fr = new LobbyFragment();
                    break;
                case R.id.navigation_notifications:
                    fr = new KPUFragment();
                    break;
            }
            getFragmentManager().beginTransaction().replace(R.id.container,fr).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getFragmentManager().beginTransaction()
                .add(R.id.container, new IntroduceFragment()).commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


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

    // 웹뷰 처리
    @Override
    public void onBackPressed() {

            KPUFragment fragment =
                    (KPUFragment) getFragmentManager().findFragmentById(R.id.container);
            WebView webView = fragment.myWebView;

            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                super.onBackPressed();
            }

    }
}
