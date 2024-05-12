package com.example.madcourse2024;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BirdsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BirdsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BirdsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BirdsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BirdsFragment newInstance(String param1, String param2) {
        BirdsFragment fragment = new BirdsFragment();
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
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_birds, container, false);

        Resources res = getResources();

        ImageButton btnGet = root.findViewById(R.id.bd_btn);
        EditText eText = root.findViewById(R.id.bd_etext);

        btnGet.setOnClickListener(v -> {
            String cur_date = eText.getText().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            if (cur_date.equals("")) {
                Calendar cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
            } else {
                Calendar cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);

            }
            DatePickerDialog picker = new DatePickerDialog(
                    root.getContext(),(view, year1, moy, dom) -> {
                        // int mnth = moy + 1;
                        //String date_rs = year1 + "-" + mnth + "-" + dom;
                        // String date_rs = dom + "/" + mnth + "/" + year1;

                        String date_rs=
                        String.format(res.getString(R.string.formatted_date), year1, moy + 1, dom);
                        eText.setText(date_rs);
                    },
                    year, month, day);
            picker.show();
        });



        // Time Picket Dialog
        ImageButton btnGetTime = root.findViewById(R.id.bd_btn_time);
        EditText eTextTime = root.findViewById(R.id.bd_eText_time);

        btnGetTime.setOnClickListener( v-> {
            Calendar cldr= Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int min= cldr.get(Calendar.MINUTE);
            // timepickerdialog
            TimePickerDialog picker_time= new TimePickerDialog(root.getContext(),(view, h, m) ->  {
                String ampm, time_rs;
                if(h >= 0 && h < 12) {
                    ampm= "am";
                }else{
                    ampm= "pm";
                }

                if(m < 10) {
                    time_rs= String.format(res.getString(R.string.formatted_time), h, "0" + m, ampm);
                } else{
                    time_rs= String.format(res.getString(R.string.formatted_time), h, "" + m, ampm);
                }
                eTextTime.setText(time_rs);
                }, hour, min, true);
            picker_time.show();
        });

        return root;
    }
}