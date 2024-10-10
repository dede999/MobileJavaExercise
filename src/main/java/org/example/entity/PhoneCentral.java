package org.example.entity;

import org.example.records.Call;

import java.util.ArrayList;
import java.util.List;

public class PhoneCentral {
    private final List<String> voiceMessages = new ArrayList<>();
    private Call currentCall = null;
    private final List<Call> incomingCalls = new ArrayList<>();

    public PhoneCentral() {}

    public boolean isBusy() {
        return currentCall != null;
    }

    public void call(PhoneCentral phone, String message) {
        if (this.isBusy()) {
            return;
        }
        if (phone.isBusy()) {
            if (!message.isBlank()) {
                phone.voiceMessages.add(message);
            }
        } else {
            phone.incomingCalls.add(new Call(this, message));
            currentCall = new Call(phone, message);
        }
    }

    public void call(PhoneCentral phone) {
        call(phone, "");
    }

    public void endCall() {
        Call caller = currentCall;
        caller.other().currentCall = null;
        currentCall = null;
    }

    public String answer(int index) {
        if (incomingCalls.isEmpty()) {
            return "No incoming calls";
        } else if (index < 0 || index >= incomingCalls.size()) {
            removeOtherCalls();
            return "Turning down all calls ...";
        } else {
            currentCall = incomingCalls.remove(index);
        }
        removeOtherCalls();
        return "Answering call ...";
    }

    public String answer() {
        return answer(0);
    }

    public String[] getVoiceMessages() {
        String[] voiceMessages = this.voiceMessages.toArray(new String[0]);
        this.voiceMessages.clear();
        return voiceMessages;
    }

    private void removeOtherCalls() {
        for (Call call : incomingCalls) {
            call.other().currentCall = null;
            String message = call.message();
            if (!message.isBlank()) {
                this.voiceMessages.add(message);
            }
        }
        incomingCalls.clear();
    }
}
