package com.gma.data_remote.mapper

import com.google.firebase.database.DataSnapshot
import kotlin.reflect.KClass

fun <T : Any> DataSnapshot.toObjectList(clazz: KClass<T>): List<T> {
    val objectListResult = this.children.mapNotNull { dataBaseTeam ->
        dataBaseTeam.getValue(clazz.java)
    }

    return objectListResult
}