package del.ifs22022.portfolio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import del.ifs22022.portfolio.R
import del.ifs22022.portfolio.model.ScreenSize
import del.ifs22022.portfolio.model.UserProfile

@Composable
fun HomeScreen(screenSize: ScreenSize, userProfile: UserProfile) {
    val contentPadding = when (screenSize) {
        ScreenSize.Compact -> 16.dp
        ScreenSize.Medium -> 32.dp
        ScreenSize.Expanded -> 48.dp
    }

    val imageSize = when (screenSize) {
        ScreenSize.Compact -> 150.dp
        ScreenSize.Medium -> 200.dp
        ScreenSize.Expanded -> 250.dp
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.profileimage),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .background(Color(0xFFDCD0FF)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // User Name
        Text(
            text = userProfile.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E0460)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Short Description
        Text(
            text = userProfile.shortDescription,
            fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF201734)
        )



        Spacer(modifier = Modifier.height(32.dp))

        // About Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f))
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tentang Saya",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A148C),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = userProfile.about,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }
        }
    }
}