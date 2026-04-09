package com.rhythmicworks.sunsalutos.patches

import app.morphe.patcher.patch.resourcePatch

val roundsPatch = resourcePatch(
    name = "Increase Max Rounds",
    description = "Changes the max value of the rounds SeekBar from 108 to 216."
) {
    execute {
        document("res/layout/activity_main.xml").use { document ->
            val seekBars = document.getElementsByTagName("SeekBar")
            for (i in 0 until seekBars.length) {
                val node = seekBars.item(i)
                val idAttr = node.attributes.getNamedItem("android:id")?.nodeValue
                if (idAttr?.contains("roundsSeekBar") == true) {
                    node.attributes.getNamedItem("android:max")?.nodeValue = "216"
                }
            }
        }
    }
}
