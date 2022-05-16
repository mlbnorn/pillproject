package com.example.pillproject;

//import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchFragment extends Fragment
{
    View thisView;
    SearchView searchView;
    ListView listView;
    ArrayList<Drug> dataList;
    ArrayAdapter<String> dataListAdapter;

    DatabaseHelper dbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thisView = inflater.inflate(R.layout.fragment_search, container, false);
        assignElements();
        //fetchData();
        dataList = ((MainActivity) getActivity()).getDataList();
        dataListAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,getBrandNameList());
        listView.setAdapter(dataListAdapter);
        setListeners();

        return thisView;
    }

    private void assignElements()
    {
        searchView = thisView.findViewById(R.id.searchView);
        listView = thisView.findViewById(R.id.listView);
    }

    private void setListeners()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(dataList.contains(query)) { dataListAdapter.getFilter().filter(query); }
                else { Toast.makeText(thisView.getContext(), "No Match Found",Toast.LENGTH_SHORT).show(); }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                dataListAdapter.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
            {
                String item = (String) adapterView.getItemAtPosition(pos);
                Log.i("TESTTSETS",item);

                Bundle b = new Bundle();
                b.putString("drug_id",item);
                InfoFragment infoFragment = new InfoFragment();
                infoFragment.setArguments(b);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFragment,infoFragment)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }

    private ArrayList<String> getBrandNameList()
    {
        ArrayList<String> bnl = new ArrayList<String>();

        for (Drug drug:dataList){
            bnl.add(drug.brand_name);
        }
        return bnl;
    }

}