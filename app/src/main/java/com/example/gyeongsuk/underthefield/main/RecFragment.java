package com.example.gyeongsuk.underthefield.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyeongsuk.underthefield.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView recText;
    EditText userEt;
    EditText artistEt;
    EditText titleEt;
    Button sendBtn;


    FirebaseDatabase database;
    DatabaseReference recommendationRef;
    DatabaseReference rootRef;

    ArrayList<Map<String,User>> datas = new ArrayList<>();

    public RecFragment() {
        // Required empty public constructor
    }

    public static RecFragment newInstance(String param1, String param2) {
        RecFragment fragment = new RecFragment();
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

        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        database = FirebaseDatabase.getInstance();
        recommendationRef = database.getReference("recommendation");

        recText = (TextView)view.findViewById(R.id.recText);
        userEt = (EditText)view.findViewById(R.id.userEt);
        artistEt = (EditText)view.findViewById(R.id.artistEt);
        titleEt = (EditText)view.findViewById(R.id.titleEt);
        sendBtn = (Button)view.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userEt.getText().toString().trim();
                String artist = artistEt.getText().toString().trim();
                String title = titleEt.getText().toString().trim();

                if(!"".equals(userName) && !"".equals(artist) && !"".equals(title)){
                    userRecommendation(userName,artist,title);
                    userEt.setText("");
                    artistEt.setText("");
                    titleEt.setText("");
                    Toast.makeText(getActivity(),"감사합니다! 추천이 완료되었습니다!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"닉네임, 아티스트 이름, 노래 제목을 입력하세요",Toast.LENGTH_LONG).show();
                }
            }
        });



        return view;
    }
    private void userRecommendation(String userName, String artist, String title) {
        User user = new User(artist, title);
        recommendationRef.child(userName).setValue(user);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
