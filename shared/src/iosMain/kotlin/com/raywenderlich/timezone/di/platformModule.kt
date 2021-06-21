package com.raywenderlich.timezone.di

import com.raywenderlich.timezone.TimeZoneHelper
import com.raywenderlich.timezone.TimeZoneHelperImpl
import org.koin.dsl.module

actual fun platformModule() = module {
    single<TimeZoneHelper> { TimeZoneHelperImpl() }
}