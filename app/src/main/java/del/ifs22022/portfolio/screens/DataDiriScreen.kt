package del.ifs22022.portfolio.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import del.ifs22022.portfolio.model.ScreenSize
import del.ifs22022.portfolio.model.UserProfile

@Composable
fun DataDiriScreen(screenSize: ScreenSize, userProfile: UserProfile) {
    val contentPadding = when (screenSize) {
        ScreenSize.Compact -> 16.dp
        ScreenSize.Medium -> 24.dp
        ScreenSize.Expanded -> 32.dp
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Data Diri",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A148C),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f))
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                DataItem("Nama Lengkap", userProfile.personalData.fullName)
                DataItem("Tanggal Lahir", userProfile.personalData.dateOfBirth)
                DataItem("Alamat", userProfile.personalData.address)
                DataItem("Email", userProfile.personalData.email)
                DataItem("No. Telepon", userProfile.personalData.phone)
                DataItem("Pendidikan", userProfile.personalData.education)
                DataItem("Keahlian", userProfile.personalData.skills.joinToString(", "))
                DataItem("Bahasa", userProfile.personalData.languages.joinToString(", "))
            }
        }
    }
}

@Composable
fun DataItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF673AB7)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Divider(
            modifier = Modifier.padding(top = 8.dp),
            color = Color(0xFFE0E0E0)
        )
    }
}