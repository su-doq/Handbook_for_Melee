package com.thatkawaiiguy.meleehandbook.fragment.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thatkawaiiguy.meleehandbook.activity.TechTabActivity;
import com.thatkawaiiguy.meleehandbook.other.ArrayHelper;
import com.thatkawaiiguy.meleehandbook.other.ItemClickSupport;
import com.thatkawaiiguy.meleehandbook.R;
import com.thatkawaiiguy.meleehandbook.activity.TechActivity;
import com.thatkawaiiguy.meleehandbook.adapter.TextAdapter;

public class TechFragment extends Fragment {

    private static final String TAG = "TechFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private String[] techs = ArrayHelper.getTechArray();

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected boolean canStart = true;

    protected RecyclerView mRecyclerView;
    protected TextAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public static TechFragment newInstance() {
        Bundle args = new Bundle();
        TechFragment fragment = new TechFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null)
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        setRecyclerViewLayoutManager();

        mAdapter = new TextAdapter(techs);
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (canStart) {
                    Intent mIntent;
                    if (techs[position].equals("Wall jump") ||
                            techs[position].equals("Directional Influence"))
                        mIntent = new Intent(getActivity(), TechTabActivity.class);
                    else
                        mIntent = new Intent(getActivity(), TechActivity.class);
                    mIntent.putExtra("option", techs[position]);
                    startActivity(mIntent);
                    canStart = false;
                }
            }
        });
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onResume() {
        canStart = true;
        super.onResume();
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
}