package utils

object Formatter {
    fun extractYear(inputDate: String): String {
        return inputDate.substringBefore("-")
    }

    fun formatMinutesToHoursAndMinutes(totalMinutes: Long): String {
        val hours = totalMinutes / 60
        val remainingMinutes = totalMinutes % 60

        val hoursString = if (hours > 0) "${hours}h" else ""
        val minutesString = if (remainingMinutes > 0) "${remainingMinutes}m" else ""

        return "$hoursString $minutesString".trim()
    }
}