package challenge.forza.videochallenge.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by arslanlodhi on 2/20/18.
 */

public class CustomRecyclerView extends RecyclerView {

    int lastVisibleItem = -1;
    OnVisibileItemChange onVisibileItemChange;


    public interface OnVisibileItemChange {
        void onVisibileItemChange(int position, View view);
    }


    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int firstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition();

        View firstVisiblePositionView = getLayoutManager().findViewByPosition(firstVisibleItemPosition);
        View lastVisiblePositionView = getLayoutManager().findViewByPosition(lastCompletelyVisibleItemPosition);


        if (lastVisibleItem == -1 || (firstVisibleItemPosition != lastVisibleItem && getVisiblePercent(firstVisiblePositionView) > getVisiblePercent(lastVisiblePositionView))) {
            lastVisibleItem = firstVisibleItemPosition;
            if (onVisibileItemChange != null)
                onVisibileItemChange.onVisibileItemChange(lastVisibleItem, firstVisiblePositionView);
        } else if (lastCompletelyVisibleItemPosition != lastVisibleItem && getVisiblePercent(lastVisiblePositionView) > getVisiblePercent(firstVisiblePositionView)) {
            lastVisibleItem = lastCompletelyVisibleItemPosition;
            if (onVisibileItemChange != null)
                onVisibileItemChange.onVisibileItemChange(lastVisibleItem, lastVisiblePositionView);
        }

    }

    public int getVisiblePercent(View v) {
        if (v != null && v.isShown()) {
            Rect r = new Rect();
            v.getGlobalVisibleRect(r);
            double sVisible = r.width() * r.height();
            double sTotal = v.getWidth() * v.getHeight();
            return (int) (100 * sVisible / sTotal);
        } else {
            return -1;
        }
    }


    public void setOnVisibileItemChange(OnVisibileItemChange onVisibileItemChange) {
        this.onVisibileItemChange = onVisibileItemChange;
    }


}
