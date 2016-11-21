package com.example.gyeongsuk.underthefield.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.gyeongsuk.underthefield.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UnderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private WebView mSoundCloudPlayer;

    FirebaseDatabase database;
    DatabaseReference underRef;
    String VIDEO_URL;


    public UnderFragment() {

    }


    public static UnderFragment newInstance(String param1, String param2) {
        UnderFragment fragment = new UnderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_under, container, false);
        mSoundCloudPlayer =(WebView)view.findViewById(R.id.webView);
        database = FirebaseDatabase.getInstance();

        underRef = database.getReference("under");
        underRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VIDEO_URL = dataSnapshot.getValue().toString();
                Log.e("check", "url is ="+VIDEO_URL);
                String html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" />" +
                        " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" />" +
                        "</head> <body style=\"background:black;margin:0 0 0 0; padding:0 0 0 0;\">" +
                        " <iframe id=\"sc-widget " + "\" width=\"100%\" height=\"450\"" + // Set Appropriate Width and Height that you want for SoundCloud Player
                        " src=\"" + VIDEO_URL   // Set Embedded url
                        + "\" frameborder=\"no\" scrolling=\"no\"></iframe>" +
                        "<script src=\"https://w.soundcloud.com/player/api.js\" type=\"text/javascript\"></script> </body> </html> ";

                mSoundCloudPlayer.setVisibility(View.VISIBLE);
                mSoundCloudPlayer.getSettings().setJavaScriptEnabled(true);
                mSoundCloudPlayer.getSettings().setLoadWithOverviewMode(true);
                mSoundCloudPlayer.getSettings().setUseWideViewPort(true);
                mSoundCloudPlayer.loadDataWithBaseURL("",html,"text/html", "UTF-8", "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
