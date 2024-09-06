package com.hoangdoviet.hoanglab11.base;

import com.hoangdoviet.hoanglab11.BasePresenter;
import com.hoangdoviet.hoanglab11.BaseView;
import com.hoangdoviet.hoanglab11.data.model.User;

import java.util.List;

public interface ListUser {
    interface Presenter extends BasePresenter {
        void addNewUser();
        void populateUser();
        void openEditScreen(User user);
        void openConfirmDeleteDialog(User user);
        void delete(long userID);
    }
    interface View extends BaseView<ListUser.Presenter>{
        void showAddUser();
        void setUsers(List<User> userList);
        void showEditScreen(long userID);
        void showDeleteConfirmDialog(User user);
        void showEmptyMessage();
    }
    interface OnItemClickListener {

        void clickItem(User person);

        void clickLongItem(User person);
    }

    interface DeleteListener {

        void setConfirm(boolean confirm, long personId);

    }

}
