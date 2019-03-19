package com.example.grainchain;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    View v;
    private RecyclerView myrecyclerView;
    ArrayList<Contact> lstContact = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;

    public FragmentContact(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //lstContact = new ArrayList<>();
        lstContact.add(new Contact("Diego Casas","4921208023", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Daniela Casas","4921235678", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Dante Casas","4929434357", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Noé Casas","4927447689", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Olga Alvarado","492345785", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Julio Enríquez","492136794", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Martin Campos","4922348623", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Juan Enriquez","4922670986", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Tere Cuevas","4926420967", R.drawable.ic_person_black_24dp));
        lstContact.add(new Contact("Claudia Morales","4922342367", R.drawable.ic_person_black_24dp));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), lstContact);
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        myrecyclerView = (RecyclerView) v.findViewById(R.id.contact_recyclerview);

        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.example_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        //MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        //final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (s == null || s.trim().isEmpty()){
            recyclerViewAdapter.setFilter(lstContact);
            return false;
        }
        s = s.toLowerCase();
        final  ArrayList<Contact> filteredNewsList = new ArrayList<>();
        for (Contact contact : lstContact){
            final String name = contact.getName().toLowerCase();
            final String phone = contact.getPhone().toLowerCase();
            if (name.contains(s) || phone.contains(s)){
                filteredNewsList.add(contact);
            }
        }
        recyclerViewAdapter.setFilter(filteredNewsList);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {

        recyclerViewAdapter.setFilter(lstContact);
        return true;
    }

    public void addItem(String name, String num){
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), lstContact);
        lstContact.add(lstContact.size(), new Contact(name,num, R.drawable.ic_person_black_24dp));
        recyclerViewAdapter.notifyDataSetChanged();
    }
}