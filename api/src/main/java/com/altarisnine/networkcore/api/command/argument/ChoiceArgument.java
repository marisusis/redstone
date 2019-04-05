package com.altarisnine.networkcore.api.command.argument;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public final class ChoiceArgument extends Argument<String> {

    @Getter @Setter private String value;
    @Getter private List<String> choices;

    public ChoiceArgument(String name, String... choices) {
        this(name, true, choices);
    }

    public ChoiceArgument(String key, boolean required, String... choices) {
        super(key, required);
        this.choices = ImmutableList.copyOf(choices);
    }

    @Override
    public String parseArgument(String argument) throws ArgumentParseException {
        if (choices.contains(argument)) {
            // It's a valid choice, return argument value
            return argument;
        } else {
            // Not valid, throw exception
            throw new ArgumentParseException(this);
        }
    }

    @Override
    public List<String> getAvailableValues() {
        return choices;
    }

    @Override
    public boolean validate(String input) {
        return choices.contains(input);
    }

    private String getFormattedList() {
        StringBuilder builder = new StringBuilder();

        for (String choice : choices) {
            builder.append(choice);
            builder.append('/');
        }

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    @Override
    public String getErrorMessage() {
        return "That is not a valid option! Must be one of " + getFormattedList() + '!';
    }
}
