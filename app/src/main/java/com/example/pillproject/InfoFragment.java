package com.example.pillproject;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class InfoFragment extends Fragment
{
    View thisView;
    TextView textView;
    String drug_id;

    public InfoFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thisView = inflater.inflate(R.layout.fragment_info, container, false);
        drug_id = getArguments().getString("drug_id");
        assignElements();

        return thisView;
    }

    private void assignElements()
    {
        textView = (TextView) thisView.findViewById(R.id.desc_header);
        textView.setText(drug_id);
    }

}
