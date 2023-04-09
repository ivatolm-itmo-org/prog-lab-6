package core.command.arguments;

/**
 * This class represents long command line argument.
 *
 * @author ivatolm
 */
public class LongArgument extends Argument {

    /**
     * Constructs new instance without arguments.
     */
    public LongArgument() {
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
    public LongArgument(String name, ArgCheck check, String greeingMsg, String errorMsg) {
        super(name, check, greeingMsg, errorMsg);
    }

    /**
     * Implements {@code parse} for {@code Parsable}.
     *
     * @param value value to parse
     * @throws NumberFormatException if number format is invalid
     */
    @Override
    public void parse(String value) throws NumberFormatException {
        this.value = Long.parseLong(value);
    }

}
