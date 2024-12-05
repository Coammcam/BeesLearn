package fpl.md07.beeslearn.screens.music

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.compose.ui.platform.LocalContext

@Composable
fun YouTubePlayerComposable(videoId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            createYouTubePlayerView(ctx, videoId)
        },
        modifier = modifier
    )
}

fun createYouTubePlayerView(context: Context, videoId: String): YouTubePlayerView {
    val youTubePlayerView = YouTubePlayerView(context)
    youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            youTubePlayer.loadVideo(videoId, 0f)
        }
    })
    return youTubePlayerView
}
