//
//  TimezoneItem.swift
//  timezoneIOS
//
//  Created by Kevin Moore on 6/16/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class TimezoneItems: ObservableObject {
    @Published var timezones = [String]()
    @Published var selectedTimezones = Set<String>()
    
    init() {
        DispatchQueue.main.async {
            self.timezones = TimeZoneHelperImpl().getTimeZoneStrings()
        }
    }
    
}

