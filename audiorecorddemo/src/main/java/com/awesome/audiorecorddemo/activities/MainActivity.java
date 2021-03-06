package com.awesome.audiorecorddemo.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.awesome.audiorecorddemo.R;
import com.awesome.audiorecorddemo.app.App;
import com.awesome.audiorecorddemo.util.AudioRecordManager;
import com.awesome.audiorecorddemo.util.MediaRecorderManager;
import com.awesome.sdk.base.BaseActivity;
import com.awesome.sdk.util.ShowLogUtil;

import static com.awesome.sdk.constant.Constant.RECORD_AUDIO_CODE;
import static com.awesome.sdk.constant.Constant.RECORD_AUDIO_PERMISSION;

public class MainActivity extends BaseActivity {

    private Button mBtnMediaRecorder;
    private Button mBtnAudioRecord;
    private Button mBtnTeeimRecord;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!hasPermission(RECORD_AUDIO_PERMISSION)) {
                requestPermission(RECORD_AUDIO_CODE, RECORD_AUDIO_PERMISSION);
                return false;
            }
            switch (v.getId()) {
                case R.id.btn_media_recorder:
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            MediaRecorderManager.SINGLETON.startRecord(new MediaRecorderManager.IOnMediaRecordListener() {
                                @Override
                                public void onStart() {
                                    ShowLogUtil.info("开始录音啦");
                                }

                                @Override
                                public void onStop() {
                                    ShowLogUtil.info("结束录音啦");
                                }

                                @Override
                                public void onError(Exception e) {
                                    ShowLogUtil.info("录音出错啦");
                                }

                                @Override
                                public void onVolumeChange(int curVolume) {
                                    ShowLogUtil.info("录音声音变化啦 " + curVolume);
                                }
                            });
                            break;
                        case MotionEvent.ACTION_UP:
                            MediaRecorderManager.SINGLETON.stopRecord();
                            break;
                    }
                    break;
                case R.id.btn_audio_record:
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            AudioRecordManager.SINGLETON.init();
                            AudioRecordManager.SINGLETON.startRecording();
                            break;

                        case MotionEvent.ACTION_UP:
                            AudioRecordManager.SINGLETON.stopRecording();
                            break;
                    }
                    break;
                case R.id.btn_teeim_record:
                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            AmrEncoder.getInstance().start(new AmrEncoder.Callback() {
//                                @Override
//                                public void onEncoded(byte[] buffer, long millsec, int dbs) {
//
//                                }
//
//                                @Override
//                                public void onEnd() {
//
//                                }
//                            });
//                            break;
//
//                        case MotionEvent.ACTION_UP:
//                            AmrEncoder.getInstance().stop();
//                            break;
                    }
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnMediaRecorder = findViewById(R.id.btn_media_recorder);
        mBtnAudioRecord = findViewById(R.id.btn_audio_record);
        mBtnTeeimRecord = findViewById(R.id.btn_teeim_record);

        mBtnMediaRecorder.setOnTouchListener(mOnTouchListener);
        mBtnAudioRecord.setOnTouchListener(mOnTouchListener);
        mBtnTeeimRecord.setOnTouchListener(mOnTouchListener);

    }


}