package com.zeus.pedido;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeus.R;
import com.zeus.android.adapter.PagerAdapter;
import com.zeus.android.entity.Personal;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedActivityPedido extends Fragment {

    public static final String TAG = TabbedActivityPedido.class.getSimpleName();
    ViewPager mViewPager;
    PagerAdapter pagerAdapter;
    private Personal personal;

    public TabbedActivityPedido() {
        // Required empty public constructor
    }

    public static TabbedActivityPedido newInstance() {
        return new TabbedActivityPedido();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_item_one, container, false);

        personal=(Personal)getArguments().getSerializable("personal");

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new ListadoPedidoActivity(), "SINCRONIZADOS");
        pagerAdapter.addFragment(new ListadoPedidoAtendidos(), "NO SINCRONIZADOS");
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        return v;
    }

}
