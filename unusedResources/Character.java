package unusedResources;

/*
 * This class is used in QuizUI.
 */
public class Character {
    private String name;
    private String icon;
    private String hiddenIcon;

    public Character(String name, String icon, String hiddenIcon) {
        this.name = name;
        this.icon = icon;
        this.hiddenIcon = hiddenIcon;
    }

    //Getter methods
    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getHiddenIcon() {
        return hiddenIcon;
    }
}
