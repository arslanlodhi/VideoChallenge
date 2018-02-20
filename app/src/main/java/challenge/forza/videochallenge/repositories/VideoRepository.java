package challenge.forza.videochallenge.repositories;


import java.util.ArrayList;

import javax.inject.Inject;

import challenge.forza.videochallenge.models.VideoModel;


public class VideoRepository {




    @Inject
    public VideoRepository(){

    }


    public ArrayList<VideoModel> getVideoList(){
        ArrayList<VideoModel> videos=new ArrayList<>();
        VideoModel video1=new VideoModel(1,"Video 1","","https://d2nsbgzitfs2gt.cloudfront.net/vods3/_definst_/mp4:amazons3/pitch-v\n" +
                "ideo-prod/22b1747fa3074b5482e60bf9c0fb25eb500bd0a2/playlist.m3u8");
        VideoModel video2=new VideoModel(2,"Video 2","","https://d2nsbgzitfs2gt.cloudfront.net/vods3/_definst_/mp4:amazons3/pitch-v\n" +
                "ideo-prod/a2f95065ac92313c3bf5d053d7779e5185f0aea4/playlist.m3u8");
        VideoModel video3=new VideoModel(3,"Video 3","","https://d2nsbgzitfs2gt.cloudfront.net/vods3/_definst_/mp4:amazons3/pitch-v\n" +
                "ideo-prod/5c1d2de9407ea0935c9ca49f5c8f45bbf9646dac/playlist.m3u8");
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        return videos;

    }




}
