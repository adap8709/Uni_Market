package com.example.unimarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    String regstr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup frag3V = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);

        EditText reginput = frag3V.findViewById(R.id.reg_input);
        Button button1 = frag3V.findViewById(R.id.frag3_button1);
        TextView regshow = frag3V.findViewById(R.id.regshow);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regstr = reginput.getText().toString();
                regshow.setText(regstr);
                regshow.setVisibility(regshow.VISIBLE);

                ( (Globalstr) getActivity().getApplication() ).setregion1(regstr);
            }
        });

        Button resetreg = frag3V.findViewById(R.id.frag3_button4);  //지역초기화
        resetreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regshow.setText(null);
                ( (Globalstr) getActivity().getApplication() ).setregion1(null);
                regshow.setVisibility(regshow.INVISIBLE);
            }
        });

        Button email = frag3V.findViewById(R.id.frag3_button2);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).sendmail();
            }
        });
        // 클릭했을 때 히스토리 페이지로 이동
        Button checkupdate = frag3V.findViewById((R.id.frag3_button3));
        checkupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),"업데이트 확인", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).gohistory();
            }
        });


        return frag3V;
    }

}
