package com.noahgeren.trailangel.ui.common

import com.noahgeren.trailangel.models.Contact

interface ContactModel {

    fun addContact(contact: Contact)
    fun removeContactAt(index: Int)

}

