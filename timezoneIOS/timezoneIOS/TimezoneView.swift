//
//  TimezoneView.swift
//  Test1
//
//  Created by Kevin Moore on 6/10/21.
//

import SwiftUI
import shared


struct TimezoneView: View {
    @EnvironmentObject var timezones : TimezoneItems
    @State private var timezoneHelper = TimeZoneHelperImpl()
//    @EnvironmentObject var presentations: Presentations
    @ObservedObject var timezoneVariables: TimezoneVariables

    var body: some View {
        VStack {
            NavigationView {
                VStack {
                    Text("6:50 pm")
                        .bold()
                        .font(.system(size: 56.0))
                    List() {
                        ForEach(Array(timezones.selectedTimezones), id: \.self) {  timezone in
                            HStack {
                                Text(timezone)
                                Spacer()
                                Text(timezoneHelper.getTime(timezoneId: timezone))
                            }
                        }
                    }
                    Spacer()
                        .toolbar {
                            ToolbarItem(placement: .navigationBarTrailing) {
                                HStack {
                                    Spacer()
                                    Image(systemName: "plus")
                                        .frame(alignment: .trailing)
                                }
                                .onTapGesture {
                                    timezoneVariables.showTimezoneDialog = true
                                }
                            }
                        }
                }
            }
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .top
            )
            Text("").hidden().sheet(isPresented: $timezoneVariables.showTimezoneDialog) {
                TimezoneDialog(searchText: "", timezoneVariables: timezoneVariables)
                    .environmentObject(timezones)
            }
        }
        
    }
    
}



struct TimezoneView_Previews: PreviewProvider {
    static var previews: some View {
        TimezoneView(timezoneVariables: TimezoneVariables())
            .environmentObject(TimezoneItems())
    }
}

