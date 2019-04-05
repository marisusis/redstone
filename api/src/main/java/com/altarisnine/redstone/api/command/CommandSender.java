package com.altarisnine.redstone.api.command;

import com.altarisnine.redstone.api.text.Text;
import com.altarisnine.redstone.api.util.Rank;

public interface CommandSender {
    boolean hasClearance(Rank rank);
    void sendMessage(String message);
    void sendMessage(Text text);
}
