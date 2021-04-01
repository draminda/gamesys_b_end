package com.gamesys.news.domain;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Raminda
 * @apiNote news DTO
 */
public class News extends AuditField {

    private Long id;
    private String title;
    private String link;
    private String description;
    private String language;
    private String copyright;
    private Instant pubDate;
    private String author;
    private String icon;
    private String image;
    private String docs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Instant getPubDate() {
        return pubDate;
    }

    public void setPubDate(Instant pubDate) {
        this.pubDate = pubDate;
    }


    public  String  getAuthor(){
        return this.author;
    }
    public void setAuthor(String author){
        this.author = author;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", copyright='").append(copyright).append('\'');
        sb.append(", pubDate=").append(pubDate);
        sb.append(", author='").append(author).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", docs='").append(docs).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!Objects.equals(title, news.title)) return false;
        if (!Objects.equals(link, news.link)) return false;
        if (!Objects.equals(description, news.description)) return false;
        if (!Objects.equals(language, news.language)) return false;
        if (!Objects.equals(copyright, news.copyright)) return false;
        if (!Objects.equals(pubDate, news.pubDate)) return false;
        if (!Objects.equals(author, news.author)) return false;
        if (!Objects.equals(icon, news.icon)) return false;
        if (!Objects.equals(image, news.image)) return false;
        return Objects.equals(docs, news.docs);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (copyright != null ? copyright.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (docs != null ? docs.hashCode() : 0);
        return result;
    }

    /**
     * @apiNote get values as object array
     * @return List<Object>
     */
    public List<Object> getAsArray(){
        return Arrays.asList(title,link,description,language,copyright,pubDate,author, icon,image,docs,getCreatedAt());
    }
}
