package com.example.kamisado.ui.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kamisado.R;
import com.example.kamisado.model.Cell;
import com.example.kamisado.model.Color;
import com.example.kamisado.model.Wizard;
import com.google.common.collect.BiMap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by danil on 21/02/2015.
 */
public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

        private ArrayList<Wizard> wizardsSet;
        private ArrayList<Cell> cellsSet;

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView wizardPic;
            ImageView fieldPic;

            public ViewHolder(View v) {
                super(v);

                wizardPic = (ImageView) v.findViewById(R.id.wizardPic);
                fieldPic = (ImageView) v.findViewById(R.id.fieldPic);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO handle clicks here
                    }
                });

            }

        }
        // END_INCLUDE(recyclerViewSampleViewHolder)

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
         */
        public FieldAdapter(BiMap<Cell, Wizard> dataSet) {
            if (dataSet == null) {
                wizardsSet = new ArrayList<>();
                cellsSet = new ArrayList<>();
            } else {
                wizardsSet = new ArrayList<>(dataSet.values());
                cellsSet = new ArrayList<>(dataSet.keySet());
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view.
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cell, viewGroup, false);

            return new ViewHolder(v);
        }
        // END_INCLUDE(recyclerViewOnCreateViewHolder)

        // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        }
        // END_INCLUDE(recyclerViewOnBindViewHolder)

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            Log.d("Adapter", "cells size:" + cellsSet.size() );
            return cellsSet.size();
        }

        public void moveWizard(Wizard wizard, Cell cell) {

        }

}
