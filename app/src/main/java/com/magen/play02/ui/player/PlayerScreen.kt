package com.magen.play02.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.magen.play02.ui.theme.AnimeTheme

@Composable
fun PlayerScreen() {
    var selectedVideo by remember { mutableStateOf<String?>(null) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6A00EE).copy(alpha = 0.3f),
                        Color(0xFF1A1A2E)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "🎬 مشغل الفيديو",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "فيديوهاتك المحفوظة",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyColumn {
                items(sampleVideos) { video ->
                    VideoItem(
                        title = video.title,
                        duration = video.duration,
                        onClick = { selectedVideo = video.path }
                    )
                }
            }
        }
    }
}

data class VideoItem(val title: String, val duration: String, val path: String)

val sampleVideos = listOf(
    VideoItem("فيديو تجريبي 1.mp4", "3:45", "/storage/video1.mp4"),
    VideoItem("فيديو تجريبي 2.mp4", "10:22", "/storage/video2.mp4"),
    VideoItem("فيديو تجريبي 3.mp4", "1:55", "/storage/video3.mp4")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoItem(title: String, duration: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = duration,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}