package com.eduramza.relativetime

import java.util.*
import java.util.concurrent.TimeUnit

class UnixToHumanTimeAgo {

    companion object{

        private const val DAYS_ON_THE_YEAR = 365
        private const val DAYS_ON_THE_MONTH = 30

        fun relativeTimePast(unixTime: Long): String{
            val unixDate = Date(unixTime * 1000)

            return calculateTimeAgo(getNow() - unixDate.time, LanguageType.EN_US)

        }

        fun relativeTimePast(unixTime: Long, language: LanguageType): String{
            val unixDate = Date(unixTime * 1000)

            return calculateTimeAgo(getNow() - unixDate.time, language)

        }

        private fun calculateTimeAgo(diff: Long, language: LanguageType): String{
            val toDays = TimeUnit.MILLISECONDS.toDays(diff)
            if (toDays > 0){
                return daysAgo(toDays, language)
            }

            val toHours = TimeUnit.MILLISECONDS.toHours(diff)
            if (toHours > 0){
                return "$toHours ${getHoursFromLanguage(language)}"
            }

            val toMinutes = TimeUnit.MILLISECONDS.toMinutes(diff)
            return if (toMinutes > 0){
                "$toMinutes ${getMinutesFromLanguage(language)}"
            } else {
                getJustNowFromLanguage(language)
            }
        }

        private fun daysAgo(toDays: Long, language: LanguageType): String {
            return when {
                toDays > 365 -> {
                    return "${getRelativeAgo(toDays, DAYS_ON_THE_YEAR)} ${getYearFromLanguage(language)}"
                }
                toDays > 30 -> {
                    return "${getRelativeAgo(toDays, DAYS_ON_THE_MONTH)} ${getMonthFromLanguage(language)}"
                }
                toDays < 30 -> {
                    return "$toDays ${getDaysFromLanguage(language)}"
                }
                else -> ""
            }
        }

        private fun getHoursFromLanguage(language: LanguageType): String {
            return when(language){
                LanguageType.EN_US -> "hour(s) ago"
                LanguageType.ES -> "hora(s) atras"
                LanguageType.PT_BR -> "hora(s) atrás"
            }
        }

        private fun getMinutesFromLanguage(language: LanguageType): String{
            return when(language){
                LanguageType.EN_US -> "minutes ago"
                LanguageType.ES -> "minutos atras"
                LanguageType.PT_BR -> "minutos atrás"
            }
        }

        private fun getJustNowFromLanguage(language: LanguageType): String{
            //TODO Usar os atributos XML de strings para a tradução poder ser automatica
            return when(language){
                LanguageType.EN_US -> "just now"
                LanguageType.ES -> "En este momento"
                LanguageType.PT_BR -> "agora mesmo"
            }
        }

        private fun getYearFromLanguage(language: LanguageType): String {
            return when(language){
                LanguageType.EN_US -> "year(s) ago"
                LanguageType.ES -> "año(s) atras"
                LanguageType.PT_BR -> "ano(s) atrás"
            }
        }

        private fun getMonthFromLanguage(language: LanguageType): String {
            return when(language){
                LanguageType.EN_US -> "month(s) ago"
                LanguageType.ES -> "mes(es) atras"
                LanguageType.PT_BR -> "mes(es) atrás"
            }
        }

        private fun getDaysFromLanguage(language: LanguageType): String {
            return when(language){
                LanguageType.EN_US -> "day(s) ago"
                LanguageType.ES -> "día(s) atras"
                LanguageType.PT_BR -> "dia(s) atrás"
            }
        }

        private fun getRelativeAgo(toDays: Long, period: Int) = toDays / period

        private fun getNow() = System.currentTimeMillis()

    }
}