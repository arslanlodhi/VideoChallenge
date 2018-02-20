package challenge.forza.videochallenge.adapters;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import challenge.forza.videochallenge.R;
import challenge.forza.videochallenge.databinding.RowVideoListBinding;
import challenge.forza.videochallenge.videoplayer.PlayBackConfiguration;
import challenge.forza.videochallenge.models.VideoModel;
import challenge.forza.videochallenge.viewmodels.VideoListFragmentViewModel;


public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface OnMuteButtonPressed{
       public void onPressed();
    }

    OnMuteButtonPressed onMuteButtonPressed;
    private ArrayList<VideoModel> mItems;
    LayoutInflater inflater;
    Context context;
    VideoListFragmentViewModel viewModel;
    PlayBackConfiguration playBackConfiguration;


    public VideoListAdapter(Context context, VideoListFragmentViewModel viewModel,OnMuteButtonPressed onMuteButtonPressed)
    {
        this(new ArrayList<VideoModel>(),context,viewModel,onMuteButtonPressed);
    }

    public VideoListAdapter(ArrayList<VideoModel> items, Context context,VideoListFragmentViewModel viewModel,OnMuteButtonPressed onMuteButtonPressed) {
        mItems = items;
        this.onMuteButtonPressed=onMuteButtonPressed;
        this.viewModel=viewModel;
        this.context= context;
        inflater = LayoutInflater.from(context);

    }




    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RowVideoListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.row_video_list,
                parent, false);

        return new VideoListViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((VideoListViewHolder)holder).binding.setVideoModel(mItems.get(position));
        ((VideoListViewHolder)holder).binding.setPlayBackInfo(playBackConfiguration);
        ((VideoListViewHolder)holder).binding.executePendingBindings();

        ((VideoListViewHolder)holder).btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onMuteButtonPressed!=null)
                    onMuteButtonPressed.onPressed();
            }
        });
    }



    public void setItems(ArrayList<VideoModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void setPlayBackConfiguration(PlayBackConfiguration playBackConfiguration) {
        this.playBackConfiguration = playBackConfiguration;
    }



    public VideoModel getItem(int position) {
        return mItems.get(position);
    }


    public class VideoListViewHolder extends RecyclerView.ViewHolder {


        RowVideoListBinding binding;
        private ImageView btnMute;



        public VideoListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            btnMute = itemView.findViewById(R.id.btn_mute);
        }



    }
}
