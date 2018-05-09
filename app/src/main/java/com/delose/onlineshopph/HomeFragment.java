package com.delose.onlineshopph;


import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SimpleDraweeView imageBanner;
    private SimpleDraweeView imgAlden,imgAlexa,imgKyle,imgCole,imgMarvin,imgMC;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance)
    {
        super.onActivityCreated(savedInstance);

        imageBanner = getView().findViewById(R.id.image_banner);
        imgAlden = getView().findViewById(R.id.image_team_alden);
        imgAlexa = getView().findViewById(R.id.image_team_alexa);
        imgKyle = getView().findViewById(R.id.image_team_kyle);
        imgCole = getView().findViewById(R.id.image_team_cole);
        imgMarvin = getView().findViewById(R.id.image_team_marvin);
        imgMC = getView().findViewById(R.id.image_team_mc);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        storageReference.child("logo_transparent.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imageBanner.setImageURI(uri);
            }
        });

        storageReference.child("alden.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgAlden.setImageURI(uri);
            }
        });

        storageReference.child("alexa.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgAlexa.setImageURI(uri);
            }
        });

        storageReference.child("kyle.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgKyle.setImageURI(uri);
            }
        });

        storageReference.child("cole.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgCole.setImageURI(uri);
            }
        });

        storageReference.child("marvin.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgMarvin.setImageURI(uri);
            }
        });

        storageReference.child("mc.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                imgMC.setImageURI(uri);
            }
        });
    }

}
