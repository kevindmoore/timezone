//
//  TimezoneDialog.swift
//  Test1
//
//  Created by Kevin Moore on 6/12/21.
//

import SwiftUI
import shared

extension String: Identifiable {
    public var id: String { return self }
}

struct TimezoneDialog: View {
    @State var searchText: String
    @EnvironmentObject var timezones : TimezoneItems
    @ObservedObject var timezoneVariables: TimezoneVariables
    @Environment(\.presentationMode) var presentationMode


    var body: some View {
        VStack {
            Searchbar(text: $searchText)
            List(selection: $timezones.selectedTimezones) {
                ForEach(timezones.timezones.filter({ searchText.isEmpty ? true : $0.contains(searchText) }), id: \.self) { timezone in
                    HStack {
                        Image(systemName: timezones.selectedTimezones.contains(timezone) ? "checkmark.circle" : "circle")
                            .onTapGesture {
                                selectTimezone(timezone: timezone)
                            }
                        Text(timezone)
                            .onTapGesture {
                                selectTimezone(timezone: timezone)
                            }
                    }
                }
            }
        }
        Button("Dismiss") {
            timezoneVariables.showTimezoneDialog = false
            self.presentationMode.wrappedValue.dismiss()
        }
    }
    func selectTimezone(timezone: String) {
        if (timezones.selectedTimezones.contains(timezone)) {
            timezones.selectedTimezones.remove(timezone)
        } else {
            timezones.selectedTimezones.insert(timezone)
        }
    }
}


struct TimezoneDialog_Previews: PreviewProvider {
    static var previews: some View {
        TimezoneDialog(searchText: "", timezoneVariables: TimezoneVariables()).environmentObject(TimezoneItems())
    }
}
