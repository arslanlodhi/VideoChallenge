package challenge.forza.videochallenge.models;


import android.databinding.BaseObservable;

public class VideoModel extends BaseObservable{

    public VideoModel(int id,String title, String description, String url) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.url = url;
    }



    int id;
    String title;
    String description;
    String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
