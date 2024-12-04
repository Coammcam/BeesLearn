import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.DisposableEffect

@Composable
fun YoutubeMoviePlayerScreen(videoMovieId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Tạo YouTubePlayerView
    AndroidView(
        factory = { ctx ->
            createYouTubeTrailerPlayerView(ctx, videoMovieId)
        },
        modifier = modifier
    )
}

// Hàm tạo YouTubePlayerView và khởi tạo listener để phát video
fun createYouTubeTrailerPlayerView(context: Context, videoId: String): YouTubePlayerView {
    val youTubePlayerView = YouTubePlayerView(context)
    youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            // Phát video khi YouTubePlayer đã sẵn sàng
            youTubePlayer.loadVideo(videoId, 0f)
        }
    })
    return youTubePlayerView
}


