package com.hog2020.ex92videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class FullscreenActivity extends AppCompatActivity {
    PlayerView pv;

    //진짜 플레이는 이친구가 함
    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        pv= findViewById(R.id.pv);

        player=new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);

        Intent intent =getIntent();
        String videoUrl=intent.getStringExtra("imgUrl");
        long currentPos=intent.getLongExtra("currentPos",0);

        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();

        player.seekTo(currentPos);
        player.play();
        player.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL);
    }



    //화면에서 안보이기 시작할때 일시정지


    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    //이 액티비티가 종료될때 이비디오도 종료
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //플레이어를 메모리에서 완전 삭제하기
        pv.setPlayer(null);
        player.release();
        player=null;

    }
}