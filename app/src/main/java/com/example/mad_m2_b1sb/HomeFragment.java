package com.example.mad_m2_b1sb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {
    WebView webView;
    Button nextBtn;
    ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        img = root.findViewById(R.id.home_image);
        nextBtn = root.findViewById(R.id.home_appointbtn);
        webView = root.findViewById(R.id.home_webView);

        img.setImageResource(R.drawable.judge);

        webView.loadUrl("https://www.google.com");
        WebSettings webSettings = webView.getSettings();
        webSettings.getJavaScriptEnabled();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = String.valueOf(request.getUrl());
                if(url.startsWith("tel:")){
                    Intent telephone = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(telephone);
                    return true;
                } else if(url.startsWith("mailto:")){
                    Intent mail = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(mail);
                    return true;
                } else{
                    view.loadUrl(url);
                }
                return false;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraFragment cameraFragment = new CameraFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.home_framelayout,cameraFragment).addToBackStack(null).commit();
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.nav_cam);
            }
        });
        return root;
    }
}