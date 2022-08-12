package assessment.core.vttpssf.models;

public class NewsArticle {

    private String id;
    private String published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublished_on() {
        return published_on;
    }

    public void setPublished_on(String published_on) {
        this.published_on = published_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static NewsArticle createCoinInfo(String articleId, String publishDate, String articleTitle,
            String articleUrl, String imgUrl, String articleBody, String articleTag, String articleCategory) {
        
                NewsArticle articleData = new NewsArticle();
                articleData.setId(articleId);
                articleData.setPublished_on(publishDate);
                articleData.setTitle(articleTitle);
                articleData.setUrl(articleUrl);
                articleData.setImageurl(imgUrl);
                articleData.setBody(articleBody);
                articleData.setTags(articleTag);
                articleData.setCategories(articleCategory);

        return articleData;
    }

}
