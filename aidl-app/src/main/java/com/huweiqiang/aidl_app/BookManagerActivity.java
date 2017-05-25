package com.huweiqiang.aidl_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {
    private static final int MESSAGE_ARRIVED = 1;
    private static final String TAG = "BookManagerActivity";
    private boolean isBind;
    private IBookManager mBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBookManager = IBookManager.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: " + mBookManager);

            try {
                Book newBook = new Book(3, "Java 并发编程实战");
                mBookManager.addBook(newBook);

                List<Book> books = mBookManager.getBookList();
                Log.i(TAG, "onServiceConnected: books" + books.toString());
                Log.d(TAG, "onServiceConnected: " + mOnNewBookArrivedListener);
                mBookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            bindService();
        }
    };

    public void bindService(View view) {
        bindService();
    }

    public void bindService() {
        isBind = true;
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mBookManager != null && mBookManager.asBinder().isBinderAlive()) {
            try {
                mBookManager.unRegisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        if (isBind) {
            unbindService(mConnection);
            isBind = false;
        }

        mHandler.removeCallbacksAndMessages(null);

        super.onDestroy();
    }

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_ARRIVED:
                    Log.d(TAG, "receive new book: " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.d(TAG, "onNewBookArrived: thread:" + Thread.currentThread());
            mHandler.obtainMessage(MESSAGE_ARRIVED, newBook).sendToTarget();
        }
    };
}
