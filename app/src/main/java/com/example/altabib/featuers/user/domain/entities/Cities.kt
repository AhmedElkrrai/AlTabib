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
    Suez;

    @Composable
    fun displayName(): String {
        val resourceId: Int = when (this) {
            Alexandria -> R.string.alexandria
            Aswan -> R.string.aswan
            Asyut -> R.string.asyut
            Cairo -> R.string.cairo
            Damanhur -> R.string.damanhur
            Damietta -> R.string.damietta
            Faiyum -> R.string.faiyum
            Giza -> R.string.giza
            Ismailia -> R.string.ismailia
            Luxor -> R.string.luxor
            Minya -> R.string.minya
            Qena -> R.string.qena
            Sohag -> R.string.sohag
            Suez -> R.string.suez
            Beni_Suef -> R.string.beni_suef
            Mansoura -> R.string.mansoura
            Kafr_El_Sheikh -> R.string.kafr_el_sheikh
            Marsa_Matrouh -> R.string.marsa_matrouh
            Shibin_El_Kom -> R.string.shibin_el_kom
            Arish -> R.string.arish
            Port_Said -> R.string.port_said
            Banha -> R.string.banha
            Hurghada -> R.string.hurghada
            Zagazig -> R.string.zagazig
            El_Tor -> R.string.el_tor
            Tanta -> R.string.tanta
        }
        return getLocalizedString(resourceId)
    }
}
