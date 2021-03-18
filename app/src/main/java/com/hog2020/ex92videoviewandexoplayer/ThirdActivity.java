package com.hog2020.ex92videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ThirdActivity extends AppCompatActivity {

    ArrayList<VideoItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    VideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView=findViewById(R.id.recycler);
        adapter=new VideoListAdapter(this,items);
        recyclerView.setAdapter(adapter);

        //서버에 있는 json 문서를 읽어와서 파싱(분석)하고 리스트로 보여주기
        loadData();
    }

    void loadData(){
        //Retrofit 을 이용하여 서버에 있는 json 문서 읽어와서 파싱(우선 json 을 문자열로 읽어와서 직접파싱)
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://hog2069.dothome.co.kr/"); //서버 기본 Url
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit =builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<String> call =retrofitService.getVideoDatas();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String jsonStr =response.body();
                Log.i("tag",jsonStr);

                //이 jsonStr 을 videoItem 으로 분석하여 리스트 뷰에 추가하기
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray categories =jsonObject.getJSONArray("categories");
                    JSONObject object =categories.getJSONObject(0);
                    JSONArray videoArray=object.getJSONArray("videos");

                    for (int i=0 ; i<videoArray.length();i++ ){
                        JSONObject video =videoArray.getJSONObject(i);

                        String title= video.getString("title");
                        String subtitle=video.getString("subtitle");
                        String thumb=  video.getString("thumb");
                        String desc= video.getString("description");
                        String sources=video.getString("sources");

                        sources=sources.replace("\\/","/");
                        sources=sources.replace("[\"","");
                        sources=sources.replace("\"]","");

                        Log.i("tag",sources);

                        VideoItem videoItem = new VideoItem(title,subtitle,thumb,sources,desc);
                        //리사이클러뷰가 보여줄 아이템리스트에 추가
                        items.add(videoItem);
                        //리사이클러 화면 갱신
                        adapter.notifyItemInserted(items.size()-1);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ThirdActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}