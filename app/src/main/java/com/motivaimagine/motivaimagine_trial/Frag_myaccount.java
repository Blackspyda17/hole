package com.motivaimagine.motivaimagine_trial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Frag_myaccount extends Fragment {

private AppBarLayout appbar;
    private TabLayout tabs;
    private ViewPager viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frag_myaccount, container, false);
        View contenedor=(View)container.getParent();
        appbar=(AppBarLayout) contenedor.findViewById(R.id.appbar);
        tabs=new TabLayout(getActivity());
        tabs.setTabTextColors(Color.BLACK,Color.BLACK);
        appbar.addView(tabs);
        viewPager=(ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appbar.removeView(tabs);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }


        String[] tituloTabs={getString(R.string.profile),getString(R.string.Password)};

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:return new UserData();
                case 1:return new UserContact();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tituloTabs[position];
        }
    }
}
