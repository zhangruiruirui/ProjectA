package lanou.foodpie.bean;

/**
 * Created by dllo on 16/10/26.
 */
public class HomePagerBean {
    private String title;
    private String card_image;
    private String description;
    private String publisher_avatar;
    private String type;

    public String getPublisher_avatar() {
        return publisher_avatar;
    }

    public void setPublisher_avatar(String publisher_avatar) {
        this.publisher_avatar = publisher_avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCard_image() {
        return card_image;
    }

    public void setCard_image(String card_image) {
        this.card_image = card_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
