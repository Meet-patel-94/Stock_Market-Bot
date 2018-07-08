package com.meetpatel.loginregister2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.meetpatel.loginregister2.Dashboard.SelectMail;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PriceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatePicker datePicker;
    TextView myDate;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button savedate;
    private Button nextpage;

    private Calendar calendar;

    private int year;
    private int month;
    private int day;

    private Global global;

    private boolean bCheckFirstAndEnd = true;

    private TextView startDate;
    private TextView endDate;

    private Button btn_moveFirebase;

    private SelectMail selectMail = new SelectMail();

    private OnFragmentInteractionListener mListener;

    public PriceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PriceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PriceFragment newInstance(String param1, String param2) {
        PriceFragment fragment = new PriceFragment();
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
            datePicker = (DatePicker) datePicker.findViewById(R.id.DatePicker);
            calendar = Calendar.getInstance();
            datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
               @Override
               public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                   String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                   PriceFragment.this.year = year;
                   PriceFragment.this.month = monthOfYear;
                   PriceFragment.this.day = dayOfMonth;

               }
           });
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        global = Global.getInstance();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(selectMail);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price,container, false);
        savedate = (Button) view.findViewById(R.id.btn_savepage);
        nextpage = (Button) view.findViewById(R.id.btn_nextpage);

        startDate = (TextView) view.findViewById(R.id.textStartDate);
        endDate = (TextView) view.findViewById(R.id.textEndDate);

        btn_moveFirebase = view.findViewById(R.id.btn_moveFirebase);
        btn_moveFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Firebase.class);
                startActivity(intent);
            }
        });


        startDate.setText(global.getStrStartDate());
        endDate.setText(global.getStrEndDate());

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PriceFragment.this.year = datePicker.getYear();
                PriceFragment.this.month = datePicker.getMonth() + 1;
                PriceFragment.this.day = datePicker.getDayOfMonth();

                global.setStrStartDate(startDate.getText().toString());
                global.setStrEndDate(endDate.getText().toString());

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                selectMail= new SelectMail();

                transaction.replace(R.id.bottomNavLayout, selectMail);
                transaction.detach(PriceFragment.this).attach(selectMail);
                transaction.commit();


            }
        });

        savedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.setStrStartDate(startDate.getText().toString());
                global.setStrEndDate(endDate.getText().toString());
            }
        });

        datePicker = view.findViewById(R.id.DatePicker);
        calendar = Calendar.getInstance();

        global.setPresentYear(calendar.get(Calendar.YEAR));
        global.setPresentMonth(calendar.get(Calendar.MONTH));
        global.setPresentDay(calendar.get(Calendar.DAY_OF_MONTH));

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                if (bCheckFirstAndEnd){
                    startDate.setText(date);
                    global.setStartYear(year);
                    global.setStartMonth(monthOfYear + 1);
                    global.setStartDay(dayOfMonth);
                    bCheckFirstAndEnd = false;
                } else {

                    global.setEndDay(dayOfMonth);
                    global.setEndMonth(monthOfYear + 1);
                    global.setEndYear(year);
                    endDate.setText(date);
                    bCheckFirstAndEnd = true;
                }
            }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

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
