package com.example.kamisado.ui;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.kamisado.R;
import com.example.kamisado.model.Cell;
import com.example.kamisado.model.Game;
import com.example.kamisado.model.Wizard;
import com.example.kamisado.ui.util.FieldAdapter;
import com.example.kamisado.ui.util.FieldAdapter2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    GridView fieldView;
    FieldAdapter2 fieldAdapter;
    Game game;

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldView = (GridView) findViewById(R.id.fieldView);
        //fieldView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //fieldView.setLayoutManager(new LinearLayoutManager(this));
        game = new Game();
        Game.genFieldColors();

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                64);
        Map<String, Object> m;
        int fieldColor = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                m = new HashMap<String, Object>();
                m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
                m.put(ATTRIBUTE_NAME_VALUE, i);
                fieldColor = Game.fieldColors[i][j].getIntColor();
                m.put(ATTRIBUTE_NAME_IMAGE, fieldColor);
                data.add(m);
            }
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
                ATTRIBUTE_NAME_IMAGE };

        int[] to = { R.id.fieldPic, R.id.wizardPic};
        
        fieldAdapter = new FieldAdapter2(this, data, R.layout.cell,
        from, to);
        fieldView.setAdapter(fieldAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FieldAdapter2 extends BaseAdapter {

        private static final int TYPE_MAX_COUNT = 1;

        //private ArrayList<Communication> mData = new ArrayList<Communication>();
        private LayoutInflater mInflater;

        private ArrayList<Integer> mMessageType = new ArrayList<Integer>();

        public FieldAdapter2() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /*
        public void addCommunication(Communication communication) {
            for (int i = 0; i < mData.size(); i++) {
                if(mData.get(i).equals(communication)){
                    mData.set(i, communication);
                    Collections.sort(mData);
                    return;
                }
            }

            mData.add(communication);
            Collections.sort(mData);
        }
*/
        @Override
        public int getItemViewType(int position) {

                return TYPE_MAX_COUNT;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return Game.FIELD_SIZE * Game.FIELD_SIZE;
        }

//TODO need to return something else
        @Override
        public Wizard getItem(int position) {
            return game.getField().get(new Cell(position / 8, position % 8));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

                convertView = mInflater.inflate(R.layout.cell, parent, false);


            convertView.setBackgroundColor(game.getField().get(new Cell(position / 8, position % 8)).getColor());
            if (game.getField().get(new Cell(position / 8, position % 8)).getWizard() != null) {
                holder.ivWizardPic.setVisibility(View.VISIBLE);
            } else {
                holder.ivWizardPic.setVisibility(View.INVISIBLE);
            }

            convertView.setTag(holder);

            return convertView;
        }

    }

    public static class ViewHolder {
        public ImageView ivWizardPic;

    }


}
