package com.yazantarifi.radio

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object RadioApplicationConfigurations {

    var applicationLanguage: String = "en"

}