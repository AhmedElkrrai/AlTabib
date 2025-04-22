package com.example.altabib.featuers.user.domain.entities

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.utils.getLocalizedString

enum class City {
    Alexandria,
    Aswan,
    Asyut,
    Damanhur,
    Beni_Suef,
    Cairo,
    Mansoura,
    Damietta,
    Faiyum,
    Tanta,
    Giza,
    Ismailia,
    Kafr_El_Sheikh,
    Luxor,
    Marsa_Matrouh,
    Minya,
    Shibin_El_Kom,
    Arish,
    Port_Said,
    Banha,
    Qena,
    Hurghada,
    Zagazig,
    Sohag,
    El_Tor,
    Suez
}

@Composable
fun City.displayName(): String {
    val resourceId: Int = when (this) {
        City.Alexandria -> R.string.alexandria
        City.Aswan -> R.string.aswan
        City.Asyut -> R.string.asyut
        City.Cairo -> R.string.cairo
        City.Damanhur -> R.string.damanhur
        City.Damietta -> R.string.damietta
        City.Faiyum -> R.string.faiyum
        City.Giza -> R.string.giza
        City.Ismailia -> R.string.ismailia
        City.Luxor -> R.string.luxor
        City.Minya -> R.string.minya
        City.Qena -> R.string.qena
        City.Sohag -> R.string.sohag
        City.Suez -> R.string.suez
        City.Beni_Suef -> R.string.beni_suef
        City.Mansoura -> R.string.mansoura
        City.Kafr_El_Sheikh -> R.string.kafr_el_sheikh
        City.Marsa_Matrouh -> R.string.marsa_matrouh
        City.Shibin_El_Kom -> R.string.shibin_el_kom
        City.Arish -> R.string.arish
        City.Port_Said -> R.string.port_said
        City.Banha -> R.string.banha
        City.Hurghada -> R.string.hurghada
        City.Zagazig -> R.string.zagazig
        City.El_Tor -> R.string.el_tor
        City.Tanta -> R.string.tanta
    }
    return getLocalizedString(resourceId)
}
