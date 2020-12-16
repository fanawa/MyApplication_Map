package com.example.myapplication_map;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;         // GoogleMapオブジェクト

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // フラグメント管理クラスを取得
        FragmentManager manager = getSupportFragmentManager();

        // フラグメントを取得
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);

        // GoogleMapを取得（非同期）
        mapFragment.getMapAsync(this);
    }

    /**
     * マップが準備できた時に呼び出される
     *
     * @param googleMap GoogleMapオブジェクト
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // 緯度・軽度オブジェクトを生成
        LatLng sydney = new LatLng(-34, 151);

        // マーカー用オブジェクトを生成
        MarkerOptions options = new MarkerOptions();
        options.position(sydney);                       // 緯度・経度
        options.title("Marker in Sydney");              // タイトル
        options.alpha(0.5f);                            // 透明度
        options.snippet("シドニーです");                  // 情報（スニペット）

        // アイコンの設定（色）
        BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        options.icon(icon);

        // GoogleMapにマーカーを追加
        mMap.addMarker(options);

        // カメラの動きを定義
        //        CameraUpdate camera = CameraUpdateFactory.newLatLng(sydney);                // 緯度・経度を指定
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(sydney, 15.0f);     // 緯度・経度・拡大率を指定
        mMap.moveCamera(camera);                                                    // 表示位置を変更

        // ズームコントロールの有効化
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

}