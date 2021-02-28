package com.noahgeren.trailangel.ui.common

import com.noahgeren.trailangel.models.Contact

interface ContactViewModel {

    fun addContact(contact: Contact)
    fun removeContactAt(index: Int)

}