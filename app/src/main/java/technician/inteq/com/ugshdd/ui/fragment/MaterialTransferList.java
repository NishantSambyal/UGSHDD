package technician.inteq.com.ugshdd.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MyPagerAdapter;

public class MaterialTransferList extends Fragment {


    private OnFragmentInteractionListener mListener;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MaterialTransferList() {

    }

    public static MaterialTransferList newInstance(String param1, String param2) {
        MaterialTransferList fragment = new MaterialTransferList();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_material_transfer_list, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.heading_text), ContextCompat.getColor(getContext(), R.color.heading_text));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
