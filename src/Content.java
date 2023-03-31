public class Content {

    private final String title;
    private final String urlImage;
    private final String rating;
    
    public Content(String title, String urlImage) {
        this(title, urlImage, null);
    }
    
    public Content(String title, String urlImage, String rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getUrlImage() {
        return urlImage;
    }
    
    public String getRating() {
        return rating;
    }    
}
