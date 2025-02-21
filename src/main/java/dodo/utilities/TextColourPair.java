package dodo.utilities;

/**
 * Class pair that holds text and its colour.
 */
public class TextColourPair {
    private String text;
    private String colour;

    /**
     * Constructor for setting text and colour.
     *
     * @param text
     * @param colour
     */
    public TextColourPair(String text, String colour) {
        this.text = text;
        this.colour = colour;
    }

    public String getText() {
        return this.text;
    }

    public String getColour() {
        return this.colour;
    }
}
