package com.hoangdoviet.hoanglab11.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoangdoviet.hoanglab11.R;
import com.hoangdoviet.hoanglab11.data.model.User;
import com.hoangdoviet.hoanglab11.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> mValues;
    private ListUser.OnItemClickListener mOnItemClickListener;

    public UserAdapter(ListUser.OnItemClickListener onItemClickListener) {
        mValues = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nameTextView.setText(mValues.get(position).name);
        holder.phoneTextView.setText(mValues.get(position).phone);

        holder.addressTextView.setText(holder.mItem.address);
        holder.emailTextView.setText(holder.mItem.email);
        holder.birthdayTextView.setText(Util.formatMin(holder.mItem.birthday));
        holder.mView.setOnClickListener(v -> mOnItemClickListener.clickItem(holder.mItem));

        holder.mView.setOnLongClickListener(v -> {
            mOnItemClickListener.clickLongItem(holder.mItem);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public void setValues(List<User> values) {
        mValues = values;
        notifyDataSetChanged();
    }
    public void removeUser(long userId) {
        int position = -1;
        for (int i = 0; i < mValues.size(); i++) {
            if (mValues.get(i).id == userId) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            mValues.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mValues.size());
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameTextView;
        public final TextView phoneTextView;
        public final TextView emailTextView;
        public final TextView birthdayTextView;
        public final TextView addressTextView;
        public User mItem;
        public ViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            nameTextView = view.findViewById(R.id.nameTextView);
            phoneTextView = view.findViewById(R.id.phoneTextView);
            emailTextView = view.findViewById(R.id.emailTextView);
            birthdayTextView = view.findViewById(R.id.birthdayTextView);
            addressTextView = view.findViewById(R.id.addressTextView);
        }
    }
}
