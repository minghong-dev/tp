package gigabyte.model.client;

import static gigabyte.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's name in the client list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should start with a letter or digit, and should only "
            + "contain letters, digits, spaces and these characters: ' - . , & / ( ) @";

    /*
     * The first character of the name must be a letter or digit,
     * otherwise " " (a blank string) or a name made only of punctuation becomes a valid input.
     * The '-' is placed last in the character class so that it is not read as a range.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} '.,&/()@-]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name otherName)) {
            return false;
        }

        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
