package com.example.altabib.featuers.user.domain.entities

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.design_system.getLocalizedString

enum class City(
    val nameResource: Int
) {
    Alexandria(R.string.alexandria),
    Aswan(R.string.aswan),
    Asyut(R.string.asyut),
    Damanhur(R.string.damanhur),
    Beni_Suef(R.string.beni_suef),
    Cairo(R.string.cairo),
    Mansoura(R.string.mansoura),
    Damietta(R.string.damietta),
    Faiyum(R.string.faiyum),
    Tanta(R.string.tanta),
    Giza(R.string.giza),
    Ismailia(R.string.ismailia),
    Kafr_El_Sheikh(R.string.kafr_el_sheikh),
    Luxor(R.string.luxor),
    Marsa_Matrouh(R.string.marsa_matrouh),
    Minya(R.string.minya),
    Shibin_El_Kom(R.string.shibin_el_kom),
    Arish(R.string.arish),
    Port_Said(R.string.port_said),
    Banha(R.string.banha),
    Qena(R.string.qena),
    Hurghada(R.string.hurghada),
    Zagazig(R.string.zagazig),
    Sohag(R.string.sohag),
    El_Tor(R.string.el_tor),
    Suez(R.string.suez);

    @Composable
    fun displayName(): String {
        return getLocalizedString(nameResource)
    }

    companion object {
        private fun fromKey(key: String): City? = City.entries.find { key == it.name }

        @Composable
        fun displayName(key: String?): String {
            val default = getLocalizedString(R.string.select_city)
            key ?: return default
            val city = fromKey(key) ?: return default
            return getLocalizedString(city.nameResource)
        }
    }
}
