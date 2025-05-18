package del.ifs22022.portfolio.model

data class UserProfile(
    val name: String,
    val profileImage: Int,
    val shortDescription: String,
    val about: String,
    val personalData: PersonalData,
    val projects: List<Project>,
    val hobbies: List<Hobby>
)

data class PersonalData(
    val fullName: String,
    val dateOfBirth: String,
    val address: String,
    val email: String,
    val phone: String,
    val education: String,
    val skills: List<String>,
    val languages: List<String>
)

data class Project(
    val title: String,
    val description: String,
    val technologies: List<String> = emptyList()
)

data class Hobby(
    val title: String,
    val description: String,
    val image: Int
)