package com.example.baatcheet.Adapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.baatcheet.fragment.ChatFragment;
import com.example.baatcheet.fragment.StatusFragment;

public class fragmentAdapter extends FragmentPagerAdapter {
    public fragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new ChatFragment();
            case 1: return new StatusFragment();
            case 2: return new ChatFragment();
            default:return new ChatFragment();


        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="CHATS";
        }
        if(position==1){
            title="STATUS";

        }
        if(position==2)
        {
            title="CALLS";
        }
        return title;
    }
}
