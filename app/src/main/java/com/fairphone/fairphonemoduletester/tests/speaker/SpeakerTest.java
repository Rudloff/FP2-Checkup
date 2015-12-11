package com.fairphone.fairphonemoduletester.tests.speaker;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;

import com.fairphone.fairphonemoduletester.R;
import com.fairphone.fairphonemoduletester.tests.Test;

/**
 * Created by maarten on 10-12-15.
 */
public class SpeakerTest extends Test {

    View mTestView;
    private MediaPlayer mediaPlayer;

    public SpeakerTest(Context context) {
        super(context);
    }

    @Override
    protected void runTest() {
        replaceView();
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.fiesta);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onCleanUp() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onCleanUp();
    }

    private void replaceView() {
        mTestView = LayoutInflater.from(getContext()).inflate(R.layout.view_speaker_test, null);
        setTestView(mTestView);
    }

    @Override
    protected int getTestTitleID() {
        return R.string.speaker_test_title;
    }

    @Override
    protected int getTestDescriptionID() {
        return R.string.speaker_test_description;
    }
}
