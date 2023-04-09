package core.command.arguments;

import core.models.mood.Mood;

/**
 * This class represents mood command line argument.
 *
 * @author ivatolm
 */
public class MoodArgument extends Argument {

    /**
     * Constructs new instance without arguments.
     */
    public MoodArgument() {
        super(null, null, null, null);
    }

    /**
     * Constructs new instance with provided arguments.
     *
     * @param name name
     * @param check check
     * @param greeingMsg greeting message
     * @param errorMsg error message
     */
    public MoodArgument(String name, ArgCheck check, String greeingMsg, String errorMsg) {
        super(name, check, greeingMsg, errorMsg);
    }

    /**
     * Implements {@code parse} for {@code IParsable}.
     *
     * @param value value to parse
     */
    @Override
    public void parse(String value) {
        this.value = Mood.parseMood(value);
    }

}
