package com.altarisnine.redstone.api.command.exception;

import com.altarisnine.redstone.api.text.Text;
import lombok.Getter;

public class InternalCommandException extends Exception {
    @Getter private final Text feedback;

    public InternalCommandException(Text message) {
        super();
        this.feedback = message;
    }
}
