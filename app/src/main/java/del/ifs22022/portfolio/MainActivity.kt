package del.ifs22022.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowMetricsCalculator
import del.ifs22022.portfolio.model.Hobby
import del.ifs22022.portfolio.model.PersonalData
import del.ifs22022.portfolio.model.Project
import del.ifs22022.portfolio.model.ScreenSize
import del.ifs22022.portfolio.model.UserProfile
import del.ifs22022.portfolio.screens.DataDiriScreen
import del.ifs22022.portfolio.screens.HobiScreen
import del.ifs22022.portfolio.screens.HomeScreen
import del.ifs22022.portfolio.screens.ProyekScreen
import del.ifs22022.portfolio.ui.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sample data for the app
        val userProfile = UserProfile(
            name = "Grace Simanullang",
            profileImage = R.drawable.profile_placeholder,
            shortDescription = "Android Developer",
            about = "Saya adalah seorang pengembang Android yang bersemangat dengan pengalaman dalam membangun aplikasi mobile yang intuitif dan fungsional. Saya selalu berusaha menerapkan prinsip-prinsip UI/UX yang baik dalam setiap proyek, dan senang mempelajari teknologi baru untuk mengembangkan keterampilan saya.",
            personalData = PersonalData(
                fullName = "Grace Alvani S. Simanullang",
                dateOfBirth = "24 Juni 2004",
                address = "Pulo Nagodang, Tambunan Baruara, Sumatra Utara",
                email = "gracealvani1@gmail.com",
                phone = "+62 813 7090 2993",
                education = "Institut Teknologi Del - S1 Informatika (2022 - sekarang)",
                skills = listOf("Android Development", "Kotlin", "Java", "QA", "Firebase", "RESTful API"),
                languages = listOf("Indonesia (Native)", "English (Professional)")
            ),
            projects = listOf(
                Project(
                    title = "E-Commerce App",
                    description = "Aplikasi e-commerce untuk UMKM lokal dengan fitur pembayaran terintegrasi dan tracking pengiriman. Aplikasi ini membantu penjual kecil dan menengah untuk menjangkau pasar yang lebih luas melalui platform digital.",
                    technologies = listOf("Kotlin", "MVVM", "Retrofit", "Room", "Firebase")
                ),
                Project(
                    title = "Aplikasi Pembelajaran",
                    description = "Platform pembelajaran daring untuk siswa SMA dengan materi interaktif dan sistem kuis. Aplikasi ini membantu siswa untuk belajar secara mandiri dan mempersiapkan diri untuk ujian nasional.",
                    technologies = listOf("Kotlin", "Jetpack Compose", "ExoPlayer", "Firebase")
                ),
                Project(
                    title = "Smart Home Controller",
                    description = "Aplikasi untuk mengontrol perangkat rumah pintar melalui smartphone. Pengguna dapat mengatur lampu, AC, dan alat elektronik lainnya dengan mudah dari mana saja.",
                    technologies = listOf("Kotlin", "Bluetooth API", "MQTT", "Material Design")
                ),
                Project(
                    title = "Task Manager",
                    description = "Aplikasi manajemen tugas dengan fitur prioritas, deadline, dan notifikasi. Membantu pengguna untuk tetap terorganisir dan produktif dalam mengelola tugas sehari-hari.",
                    technologies = listOf("Kotlin", "Room Database", "WorkManager", "Material Design")
                )
            ),
            hobbies = listOf(
                Hobby(
                    title = "Mendengarkan Musik",
                    description = "Mendengarkan musk-musik indah dan dikomposisikan dengan luar biasa.",
                    image = R.drawable.hobby_placeholder
                ),
                Hobby(
                    title = "Membaca",
                    description = "Membaca buku-buku tentang teknologi, pengembangan diri, dan bisnis untuk mengembangkan pengetahuan dan wawasan.",
                    image = R.drawable.hobby_placeholder
                ),
                Hobby(
                    title = "Fotografi",
                    description = "Mengabadikan momen berharga melalui lensa kamera, terutama fotografi lanskap dan street photography.",
                    image = R.drawable.hobby_placeholder
                )
            )
        )

        setContent {
            PortfolioTheme {
                val windowMetrics = WindowMetricsCalculator.getOrCreate()
                    .computeCurrentWindowMetrics(this)

                val widthDp = windowMetrics.bounds.width() / resources.displayMetrics.density

                // Determine screen size
                val screenSize = when {
                    widthDp < 600 -> ScreenSize.Compact
                    widthDp < 840 -> ScreenSize.Medium
                    else -> ScreenSize.Expanded
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortfolioApp(screenSize, userProfile)
                }
            }
        }
    }
}

@Composable
fun PortfolioApp(screenSize: ScreenSize, userProfile: UserProfile) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFF9370DB)) // Purple color
                )

                // Content based on navigation
                NavigationGraph(navController = navController, screenSize = screenSize, userProfile = userProfile)
            }
        }
    )
}

// Bottom navigation items
sealed class BottomNavItem(val route: String, val icon: @Composable () -> Unit, val title: String) {
    data object Home : BottomNavItem(
        route = "home",
        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
        title = "Home"
    )

    data object DataDiri : BottomNavItem(
        route = "data_diri",
        icon = { Icon(Icons.Default.Person, contentDescription = "Data Diri") },
        title = "Data Diri"
    )

    data object Proyek : BottomNavItem(
        route = "proyek",
        icon = { Icon(Icons.Default.WorkOutline, contentDescription = "Proyek") },
        title = "Proyek"
    )

    data object Hobi : BottomNavItem(
        route = "hobi",
        icon = { Icon(Icons.Default.Favorite, contentDescription = "Hobi") },
        title = "Hobi"
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.DataDiri,
        BottomNavItem.Proyek,
        BottomNavItem.Hobi
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color(0xFF8A2BE2), // Purple color for bottom navigation
        contentColor = Color.White
    ) {
        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                icon = { item.icon() },
                label = { Text(text = item.title) },
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color(0xFFDDCCFF),
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color(0xFFDDCCFF),
                    indicatorColor = Color(0xFF7C4DFF)
                )
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, screenSize: ScreenSize, userProfile: UserProfile) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(screenSize, userProfile)
        }
        composable(BottomNavItem.DataDiri.route) {
            DataDiriScreen(screenSize, userProfile)
        }
        composable(BottomNavItem.Proyek.route) {
            ProyekScreen(screenSize, userProfile)
        }
        composable(BottomNavItem.Hobi.route) {
            HobiScreen(screenSize, userProfile)
        }
    }
}

