package com.yiya.qq.ui.mine;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.databinding.FragmentMineBinding;
import com.yiya.qq.viewmodel.MineViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private String  result = null;
    private Handler postHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==1000){
               bindingView.text.setText(result);
            }
        };
    };
    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

        new Thread(postThread).start();
    }
    private Thread postThread = new Thread(){
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://api.qmwm777.com/api/find/newsRe");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("pageNum", "1");
                connection.setDoOutput(true);
                connection.connect();
                if(connection.getResponseCode() == 200){
                    InputStream in= connection.getInputStream();
                    byte[] b=new byte[1024*512]; //定义一个byte数组读取输入流
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(); //定义缓存流来保存输入流的数据
                    int len=0;
                    while((len=in.read(b))>-1){  //每次读的len>-1 说明是是有数据的
                        baos.write(b,0,len);  //三个参数  输入流byte数组   读取起始位置  读取终止位置
                    }
                    result=baos.toString();
                    postHandler.sendEmptyMessage(1000);
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if(connection!=null){
                    connection.disconnect();
                }
            }
        };
    };


}
