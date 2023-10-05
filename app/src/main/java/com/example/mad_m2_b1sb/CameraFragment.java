package com.example.mad_m2_b1sb;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class CameraFragment extends Fragment {
    ImageView photo;
    Button shutter;

    VideoView video;
    Button shoot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_camera, container, false);

        photo = root.findViewById(R.id.cam_image);
        shutter = root.findViewById(R.id.cam_photobtn);
        video = root.findViewById(R.id.cam_video);
        shoot = root.findViewById(R.id.cam_vdoBtn);

        final int pic_id=123;

        shutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraintent,pic_id);
            }
        });

        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);

        final int vid_id = 456;

        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraintent2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(cameraintent2,vid_id);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123 && resultCode == RESULT_OK){
            Bitmap clicked_photo = data.getParcelableExtra("data");
            photo.setImageBitmap(clicked_photo);
        } else if(requestCode == 456 && resultCode == RESULT_OK){
            Uri videouri = data.getData();
            video.setVideoURI(videouri);
            video.start();
        }
        else {
            Toast.makeText(getActivity(),"Sad",Toast.LENGTH_SHORT).show();
        }
    }



}