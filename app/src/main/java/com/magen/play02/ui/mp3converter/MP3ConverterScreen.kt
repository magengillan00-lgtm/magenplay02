package com.magen.play02.ui.mp3converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MP3ConverterScreen() {
    var selectedFormat by remember { mutableStateOf("mp3") }
    var quality by remember { mutableStateOf("128k") }
    var isConverting by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2DFFDB).copy(alpha = 0.3f),
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
                text = "🎵 محول الفيديو إلى MP3",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "استخراج الصوت من الفيديوهات بسهولة",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Video selection button
            Button(
                onClick = { /* Select video */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("اختر فيديو لاستخراج الصوت")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Format selection
            Text(
                text = "صيغة المخرجات",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("mp3", "wav", "aac", "flac", "ogg").forEach { format ->
                    FilterChip(
                        selected = selectedFormat == format,
                        onClick = { selectedFormat = format },
                        label = { Text(format.uppercase()) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Quality selection
            Text(
                text = "جودة الصوت",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Column {
                listOf("64k", "128k", "192k", "256k", "320k").forEach { q ->
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = quality == q,
                            onClick = { quality = q }
                        )
                        Text(
                            text = when (q) {
                                "64k" -> "منخفضة"
                                "128k" -> "متوسطة"
                                "192k" -> "جيدة"
                                "256k" -> "عالية"
                                "320k" -> "أفضل جودة"
                                else -> q
                            }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Convert button
            Button(
                onClick = { isConverting = true },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isConverting,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                if (isConverting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(if (isConverting) "جاري التحويل..." else "استخراج الصوت")
            }
        }
    }
}