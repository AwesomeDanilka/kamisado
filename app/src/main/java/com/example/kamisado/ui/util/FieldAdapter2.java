package com.example.kamisado.ui.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.kamisado.R;
import com.example.kamisado.model.Cell;
import com.example.kamisado.model.Color;
import com.example.kamisado.model.Wizard;
import com.google.common.collect.BiMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by danil on 21/02/2015.
 */
public class FieldAdapter2 extends BaseAdapter {

        private static final int TYPE_MAX_COUNT = 1;

        private ArrayList<Communication> mData = new ArrayList<Communication>();
        private LayoutInflater mInflater;

        private ArrayList<Integer> mMessageType = new ArrayList<Integer>();

        public FieldAdapter2() {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

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

        @Override
        public int getItemViewType(int position) {
            Communication communication = mData.get(position);
            if (communication instanceof P2PCommunication) {
                return TYPE_P2P_COMMUNICATION;
            } else if (communication instanceof GroupCommunication){
                return TYPE_GROUP_COMMUNICATION;
            }
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Communication getItem(int position) {
            return mData.get(position);
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

            if (mData.get(position) instanceof P2PCommunication) {
                P2PCommunication p2pCommunication = (P2PCommunication) mData.get(position);
                User remoteUser = MainActivity.podComm.getUserByID(p2pCommunication.getRemoteUserID());
                String name = remoteUser.getName();
                Log.d(MainActivity.LOG_TAG, "ListView: name: " + name);
                HistoryMessage historyMessage = p2pCommunication.getLatestMessage();

                String unreadMsgCounter = p2pCommunication.getUnreadMsgCounter();

                convertView = mInflater.inflate(R.layout.communication_list_view_new, parent, false);
                if (historyMessage != null) {

                    holder.tvTextCommunicationName = (TextView) convertView
                            .findViewById(R.id.tvTextCommunicationName);
                    holder.tvTextCommunicationMsgCount = (TextView) convertView
                            .findViewById(R.id.tvTextCommunicationMsgCount);
                    holder.textLatestMessage = (TextView) convertView
                            .findViewById(R.id.textLatestMessage);
                    holder.textLatestTime = (TextView) convertView
                            .findViewById(R.id.textLatestTime);
                    holder.ivPic = (ImageView) convertView
                            .findViewById(R.id.ivPic);

                    holder.tvTextCommunicationName.setText(name);
                    holder.tvTextCommunicationMsgCount.setText(unreadMsgCounter);
                    if (historyMessage instanceof FileMessage) {
                        FileMessage fileMessage = (FileMessage) historyMessage;
                        holder.textLatestMessage.setText(fileMessage.getFile().getName());
                    } else {
                        if ((historyMessage != null) && (historyMessage.getMessage() != null)) {
                            holder.textLatestMessage.setText(historyMessage.getMessage());
                        } else {
                            holder.textLatestMessage.setText("");
                        }
                    }

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");

                    if (historyMessage.getTime() != null) {
                        if ((new Date()).getDay() == historyMessage.getTime().getDay()) {
                            holder.textLatestTime.setText(timeFormat.format(historyMessage.getTime()));
                        } else {
                            holder.textLatestTime.setText(dateFormat.format(historyMessage.getTime()));
                        }
                    } else {
                        holder.textLatestTime.setText("");
                    }

                    //holder.textLatestTime.setText(historyMessage.getTime().toString());
                    //holder.ivPic.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_person));
                    holder.ivPic.setImageDrawable(getResources().getDrawable(R.drawable.avatar_contact_guy));

                    int unreadCounter = 0;
                    try {
                        unreadCounter = Integer.parseInt(p2pCommunication.getUnreadMsgCounter());
                    } catch (NumberFormatException e) {
                        unreadCounter = 0;
                    }

                    ImageView ivUnreadMessages = (ImageView) convertView.findViewById(R.id.imageView8);

                    ImageView ivGroup = (ImageView) convertView.findViewById(R.id.imageView7);
                    ivGroup.setVisibility(View.GONE);

                    if (unreadCounter > 0) {
                        holder.tvTextCommunicationName.setTextColor(getActivity().getResources().getColor(R.color.dark_blue));
                        ivUnreadMessages.setVisibility(View.VISIBLE);
                    } else {
                        holder.tvTextCommunicationName.setTextColor(getActivity().getResources().getColor(R.color.grey_most_dark));
                        ivUnreadMessages.setVisibility(View.GONE);
                    }
                }
                Log.d(MainActivity.LOG_TAG, "Adding P2P Comm to list");

            } else if (mData.get(position) instanceof GroupCommunication) {

                GroupCommunication groupCommunication = (GroupCommunication) mData.get(position);
                Group group = MainActivity.podComm.getGroupByID(groupCommunication.getGroupID());

                String name = group.getName();
                String users = group.GetGroupNameByUsersList(group.getUsers());
                HistoryMessage historyMessage = groupCommunication.getLatestMessage();
                String unreadMsgCounter = groupCommunication.getUnreadMsgCounter();

                convertView = mInflater.inflate(R.layout.communication_list_view_new, parent, false);

                holder.tvTextCommunicationName = (TextView) convertView
                        .findViewById(R.id.tvTextCommunicationName);
                holder.tvTextCommunicationMsgCount = (TextView) convertView
                        .findViewById(R.id.tvTextCommunicationMsgCount);
                holder.textLatestMessage = (TextView) convertView
                        .findViewById(R.id.textLatestMessage);
                holder.textLatestTime = (TextView) convertView
                        .findViewById(R.id.textLatestTime);
                holder.ivPic = (ImageView) convertView
                        .findViewById(R.id.ivPic);

 /*                   TextView tvUsers = (TextView) convertView
                            .findViewById(R.id.tvUsers);


                    tvUsers.setText(users);
                    tvUsers.setVisibility(View.GONE);
*/
                holder.tvTextCommunicationName.setText(name);
                holder.tvTextCommunicationMsgCount.setText(unreadMsgCounter);

                if (historyMessage instanceof GroupFileMessage) {
                    GroupFileMessage fileMessage = (GroupFileMessage) historyMessage;
                    holder.textLatestMessage.setText(fileMessage.getFile().getName());
                } else {
                    if ((historyMessage!=null) && (historyMessage.getMessage() != null)) {
                        holder.textLatestMessage.setText(historyMessage.getMessage());
                    } else {
                        holder.textLatestMessage.setText("");
                    }
                }

                if (historyMessage != null) {

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");

                    if (historyMessage.getTime() != null) {
                        if ((new Date()).getDay()==historyMessage.getTime().getDay()) {
                            holder.textLatestTime.setText(timeFormat.format(historyMessage.getTime()));
                        } else {
                            holder.textLatestTime.setText(dateFormat.format(historyMessage.getTime()));
                        }
                    } else {
                        holder.textLatestTime.setText("");
                    }

                } else {
                    holder.textLatestMessage.setText("");
                    holder.textLatestTime.setText("");
                }
                //holder.ivPic.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_group));
                holder.ivPic.setImageDrawable(getResources().getDrawable(R.drawable.avatar_recent_public_group_star));
                Log.d(MainActivity.LOG_TAG, "Adding P2P Comm to list");

                int unreadCounter = 0;
                try {
                    unreadCounter = Integer.parseInt(groupCommunication.getUnreadMsgCounter());
                } catch (NumberFormatException e) {
                    unreadCounter = 0;
                }

                ImageView ivUnreadMessages = (ImageView) convertView.findViewById(R.id.imageView8);

                ImageView ivGroup = (ImageView) convertView.findViewById(R.id.imageView7);
                ivGroup.setVisibility(View.VISIBLE);

                if (unreadCounter > 0) {
                    holder.tvTextCommunicationName.setTextColor(getActivity().getResources().getColor(R.color.dark_blue));
                    ivUnreadMessages.setVisibility(View.VISIBLE);
                } else {
                    holder.tvTextCommunicationName.setTextColor(getActivity().getResources().getColor(R.color.grey_most_dark));
                    ivUnreadMessages.setVisibility(View.GONE);
                }

            }




            convertView.setTag(holder);

            return convertView;
        }

        public void clear() {
            mData.clear();
            mMessageType.clear();

        }



    }

    public static class ViewHolder {
        public TextView textLatestTime;
        public TextView textLatestMessage;
        public TextView tvTextCommunicationMsgCount;
        public TextView tvTextCommunicationName;
        public ImageView ivPic;

    }

}
