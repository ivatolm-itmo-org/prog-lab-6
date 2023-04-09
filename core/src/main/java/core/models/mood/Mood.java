package core.models.mood;

// import core.NameNotFoundException;
// import core.parser.SimpleParseException;

import core.database.Serializable;
import core.models.Validatable;
import core.models.Validator;
import core.utils.NameNotFoundException;
import core.utils.SimpleParseException;

/**
 * Data structure for Mood described in the task.
 *
 * @author ivatolm
 */
public enum Mood implements Serializable, Validatable {

    LONGING("longing"),
    GLOOM("gloom"),
    APATHY("apathy"),
    RAGE("rage")
    ;

    // String value of the enum
    @Validator(validator = MoodValueValidator.class)
    private String value;

    /**
     * Constructs new instance from the string argument.
     *
     * @param value enum string representation
     */
    Mood(String value) {
        this.value = value;
    }

    /**
     * Constructs new instance from the integer argument.
     *
     * @param value enum int representation
     */
    Mood(int value) {
        this.value = Mood.parseMood("" + value).value;
    }

    /**
     * Parses {@code value} into {@code Mood}.
     *
     * @param value enum string representation
     * @return Enum with the provided name
     * @throws NameNotFoundException if there is no instance with such name
     */
    public static Mood parseMood(String value) throws NameNotFoundException {
        try {
            int intValue = Integer.parseInt(value);

            if (0 <= intValue && intValue < values().length) {
                return values()[intValue];
            }

        } catch (Exception e) {
            /*
                Mood value is not represented by integer, maybe it's
                represented by String. So, skipping...
             */
        }

        for (Mood mood : Mood.values()) {
            if (value.equalsIgnoreCase(mood.value)) {
                return mood;
            }
        }

        throw new NameNotFoundException("'" + value + "'" + " " + "cannot be converted into Mood.");
    }

    /**
     * Implements {@code serialize} for {@code Serializable}.
     * Serializes fields into {@code String} array.
     *
     * @return serialized object
     */
    @Override
    public String[] serialize() {
        return new String[] { this.value };
    }

    /**
     * Implements {@code deserialize} for {@code Serializable}.
     * Casts input value to target type. Overrides internal value with a new one.
     *
     * @param string serialized object
     * @throws SimpleParseException if input is invalid
     */
    @Override
    public void deserialize(String[] string) throws SimpleParseException {
        String value = string[0];

        try {
            this.value = Mood.parseMood(value).value;
        } catch(NameNotFoundException e) {
            throw new SimpleParseException("Cannot parse Mood from: " + value);
        }
    }

    /**
     * Overrides {@code toString} of {@code Object}
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return this.value;
    }

}
