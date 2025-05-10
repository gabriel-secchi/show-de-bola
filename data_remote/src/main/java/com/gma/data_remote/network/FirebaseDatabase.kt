package com.gma.data_remote.network

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

open class FirebaseDatabase {
    val reference: DatabaseReference = Firebase.database.reference

    /*fun <T : Any> getObjectList(
        table: String,
        clazz: KClass<T>,
        resultListener: (List<T>?) -> Unit
    ) {
        reference.child(table).get()
            .addOnSuccessListener { snapshot ->
                val resultObjectList = snapshot.toObjectList(clazz)
                resultListener(resultObjectList)
            }
            .addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
                resultListener(null)
            }
    }*/
}