package tv.danmaku.ijk.media.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.player.widget.media.IjkVideoView;

/**
 * <pre>
 *     author : Nelson
 *     GitHub : https://github.com/Nelson-KK
 *     time   : 2019/5/8
 *     desc   :
 * </pre>
 */
public class ScrollActivity extends Activity {

    private IjkVideoView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity);
        mView = findViewById(R.id.video_view);
        mView.setVideoPath("http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8");
        mView.start();
    }
}
