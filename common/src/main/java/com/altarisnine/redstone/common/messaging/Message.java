package com.altarisnine.redstone.common.messaging;

import lombok.Data;

@Data
public class Message {

    private final String channel;
    private final String content;

}
