package com.apps.finalproject.remote.model

class getsame {
}

fun main() {
    val string = "psdonco"
    var awal = 1

    string.forEach {
        for (x in awal until string.length){
            if (it == string[x]){
                print("sama nih $it == ${string[x]}")
            }
        }
        awal+=1
    }
}