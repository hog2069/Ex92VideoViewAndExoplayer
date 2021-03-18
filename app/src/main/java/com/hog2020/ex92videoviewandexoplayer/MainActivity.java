package com.hog2020.ex92videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    //Video Uri [ 용량 문제로 별도의 서버에 위치하는 경우가 대부분 ] - 인터넷 퍼미션 필요 구글에  "sample video Url" 검색
    // 즉 , 비디오파일을 서버에 놓고 이를 읽어들여 플레이 하도록 코딩

    Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv=findViewById(R.id.vv);

        // 비디오의 재생, 일시정지 등을 할 수 있는 컨트롤바 붙이기
       vv.setMediaController(new MediaController(this));

        //비디오뷰가 동영상의 uri 상정하기
        vv.setVideoURI(videoUri);

        //동영상을 로딩하는데 시간이 걸리기 때문에 곧바로 실행하지 못하고
       // 비디오의 로딩준비 (prepare) 가 완료 되었을때 실행하도록...
       // 리스너 이용
       vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
               //미디어 시작
               vv.start();
           }
       });


    }

    //액티비티가 화면에서 보이지 않을때 비디오를 일시정지 시켜줄 필요가 있음
    //비디오 뷰는 별도 Thread 로 동영상을 재생하기에..

    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시정지
        if (vv != null && vv.isPlaying()) vv.pause();
    }
    //비디오 뷰는 성능이 좋지않고 무거움. 또한 컨트롤러 위치지정도 다소 불편
    //실무에서는  안드로이드의 공식 VideoView Library 인 Exoplayer 를 많이 사용



    public void clickbtn(View view) {

       startActivity(new Intent(this,SecondActivity.class));
       finish();
    }


    public void clickbtn2(View view) {
       startActivity(new Intent(this,ThirdActivity.class));
       finish();
    }
}