package app.com.toplist.Presenter.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.com.toplist.Constants.Constant;
import app.com.toplist.DTO.responses.TopListDTO;
import app.com.toplist.Model.DAO.TopListDAO;
import app.com.toplist.Presenter.UIManager;
import app.com.toplist.Presenter.adapters.TopListAdapter;
import app.com.toplist.Presenter.communication.IUIEventListner;
import app.com.toplist.R;

/**
 * Created by Balwinder on 27/07/2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */

public class FragmentTopList extends BaseFragment implements IUIEventListner
         {

    private Context mContext;
    private TopListAdapter adapter;
    private List<TopListDTO> albumList;
    UIManager uiManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiManager = new UIManager(this, this, mContext);
    }

    @Override
    protected View onChildCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_list, container,
                false);
        setActionBarTitle("Top List");
        intialiseUIObject(view);
        setHasOptionsMenu(true);
        return view;
    }

    private void intialiseUIObject(View view) {
       RecyclerView recyclerView;
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        TopListDAO dao = new TopListDAO(mContext);

        albumList =(List<TopListDTO>) dao.getData();
        if(albumList.isEmpty())
            Toast.makeText(mContext,"Press refresh to get latest music",Toast.LENGTH_LONG).show();

        adapter = new TopListAdapter(mContext, albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onMenuItemClickListener(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                uiManager.execute(Constant.Commands.REQUEST_GET_TOP_LIST, null);
                break;

        }

        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // collapsingToolbar.setTitleEnabled(false);
    }

    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {
        switch (action) {
            case Constant.Commands.REQUEST_GET_TOP_LIST:
                albumList = (List<TopListDTO>) data;
                adapter.updateData(albumList);
                adapter.notifyDataSetChanged();
                Toast.makeText(mContext, "List downloaded successfully", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
        switch (action) {

            case Constant.Commands.REQUEST_GET_TOP_LIST:
                Toast.makeText(mContext, data.toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        switch (action) {
            case Constant.Commands.REQUEST_GET_TOP_LIST:
                Toast.makeText(mContext, data.toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {

    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
     /*   MenuItem addButton = menu.findItem(R.id.action_settings);
        addButton.setVisible(true);*/
        super.onPrepareOptionsMenu(menu);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}