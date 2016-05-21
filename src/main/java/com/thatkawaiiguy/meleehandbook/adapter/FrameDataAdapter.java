/*
    This file is part of Handbook for Melee.

    Handbook for Melee is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Handbook for Melee is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Handbook for Melee.  If not, see <http://www.gnu.org/licenses/>
 */

package com.thatkawaiiguy.meleehandbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.thatkawaiiguy.meleehandbook.other.CustomChildObject;
import com.thatkawaiiguy.meleehandbook.other.CustomChildViewHolder;
import com.thatkawaiiguy.meleehandbook.other.CustomParentObject;
import com.thatkawaiiguy.meleehandbook.other.CustomParentViewHolder;
import com.thatkawaiiguy.meleehandbook.R;
import com.thatkawaiiguy.meleehandbook.activity.FrameDataActivity;

import java.util.List;

public class FrameDataAdapter extends ExpandableRecyclerAdapter<CustomParentViewHolder, CustomChildViewHolder> {

    private final LayoutInflater mInflater;

    private final String characterPicked;

    private final Context context;

    private boolean canStart = true;

    public FrameDataAdapter(Context context, List<ParentObject> parentItemList, String character) {
        super(context, parentItemList);
        this.context = context;
        characterPicked = character;
        mInflater = LayoutInflater.from(context);
        removeAnimation();
    }

    @Override
    public CustomParentViewHolder onCreateParentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.recycler_item_layout_card_parent, parent, false);
        return new CustomParentViewHolder(view);
    }

    @Override
    public CustomChildViewHolder onCreateChildViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.recycler_item_layout_child, parent, false);
        return new CustomChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CustomParentViewHolder parentViewHolder, int position, Object parentObject) {
        CustomParentObject customParentObject = (CustomParentObject) parentObject;
        parentViewHolder.dataText.setText(customParentObject.getParentText());
    }

    @Override
    public void onBindChildViewHolder(final CustomChildViewHolder childViewHolder, int position, Object childObject) {
        CustomChildObject customChildObject = (CustomChildObject) childObject;
        childViewHolder.dataText.setText(customChildObject.getChildText());
        childViewHolder.dataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canStart) {
                    context.startActivity(
                            new Intent(context, FrameDataActivity.class)
                            .putExtra("option", characterPicked)
                            .putExtra("frame", childViewHolder.dataText.getText()));
                    canStart = false;
                }
            }
        });
    }

    public void setCanStart(){
        canStart = true;
    }
}
