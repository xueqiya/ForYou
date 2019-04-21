package com.yiya.foryou.ui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

import com.yiya.foryou.R;
import com.yiya.foryou.base.BaseFragment;
import com.yiya.foryou.databinding.FragmentMineBinding;
import com.yiya.foryou.dialog.NormalDialog;
import com.yiya.foryou.ui.login.LoginActivity;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.PerfectClickListener;
import com.yiya.foryou.utils.SPUtil;
import com.yiya.foryou.utils.VersionUtils;
import com.yiya.foryou.viewmodel.MineViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:39
 * description:
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {

    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        bindingView.outLogin.setOnClickListener(clickListener);
        String uid = (String) SPUtil.get(getActivity(), AppConstants.KEY_UID, "");
        bindingView.uid.setText(uid);
        bindingView.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        bindingView.collapsingToolbar.setTitle(getString(R.string.app_name));
        bindingView.version.setText("Version：" + VersionUtils.getLocalVersion(getActivity()));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

    }

    private PerfectClickListener clickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.outLogin:
                    outLogin();
                    break;
            }
        }
    };

    private void outLogin() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "提示");
        bundle.putString("message", "您确定要退出登录吗？");
        final NormalDialog normalDialog = new NormalDialog();
        normalDialog.setArguments(bundle);
        normalDialog.show(getFragmentManager(), "normalDialog");
        normalDialog.setDialogBackCall(new NormalDialog.DialogBackCall() {
            @Override
            public void onConfirm() {
                SPUtil.put(getContext(), AppConstants.KEY_LOGIN, false);
                startIntent(LoginActivity.class, null);
                getActivity().finish();
            }

            @Override
            public void onCancel() {

            }
        });
    }

}
