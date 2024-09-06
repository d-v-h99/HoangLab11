package com.hoangdoviet.hoanglab11.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.hoangdoviet.hoanglab11.R;
import com.hoangdoviet.hoanglab11.utils.Constants;

public class DeleteConfirmFragment extends DialogFragment {
    private ListUser.DeleteListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final long userID = getArguments().getLong(Constants.USER_ID);
        Log.d("Checkk", ""+userID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you_sure);

        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> mListener.setConfirm(true, userID));
        builder.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> mListener.setConfirm(false, userID));
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListUser.DeleteListener) context;
    }
}
