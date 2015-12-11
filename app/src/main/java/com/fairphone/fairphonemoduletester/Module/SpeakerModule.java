package com.fairphone.fairphonemoduletester.Module;

import android.content.Context;

import com.fairphone.fairphonemoduletester.R;
import com.fairphone.fairphonemoduletester.tests.Test;
import com.fairphone.fairphonemoduletester.tests.speaker.SpeakerTest;
import com.fairphone.fairphonemoduletester.tests.vibrator.VibratorTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dirk on 20-10-15.
 */
public class SpeakerModule implements Module {
    @Override
    public int getPictureResourceID() {
        return R.drawable.speaker;
    }

    @Override
    public int getDescriptionId() {
        return R.string.speaker_module_description;
    }

    @Override
    public int getModuleNameID() {
        return R.string.speaker_module_name;
    }

    public List<Test> getTestList(Context context) {
        List<Test> tests = new ArrayList<>();
        tests.add(new VibratorTest(context));
        tests.add(new SpeakerTest(context));
        return tests;
    }
}
