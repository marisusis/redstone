package com.altarisnine.networkcore.api.command;

import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.util.Rank;

public interface CommandSender {
    boolean hasClearance(Rank rank);
    void sendMessage(String message);
    void sendMessage(Text text);
}
