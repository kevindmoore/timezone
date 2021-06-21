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
    @ObservedObject var timezoneVariables: TimezoneVariables
    @State var currentDate = Date()
    let timer = Timer.publish(every: 1000, on: .main, in: .common).autoconnect()
    let dateFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateStyle = .none
        formatter.timeStyle = .short
        return formatter
    }()
    
    var body: some View {
        VStack {
            NavigationView {
                VStack {
                    Text("\(currentDate, formatter: dateFormatter)")
                        .bold()
                        .font(.system(size: 56.0))
                        .onReceive(timer) { input in
                            currentDate = input
                        }
                    List() {
                        ForEach(Array(timezones.selectedTimezones), id: \.self) {  timezone in
                            HStack {
                                Text(timezone)
                                Spacer()
                                Text(timezoneHelper.getTime(timezoneId: timezone))
                            }
                        }
                        .onDelete(perform: deleteItems)
                    }
                    Spacer()
                }
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
    func deleteItems(at offsets: IndexSet) {
        for index in offsets {
            let element = Array(timezones.selectedTimezones)[index]
            timezones.selectedTimezones.remove(element)
        }
    }
    
}



struct TimezoneView_Previews: PreviewProvider {
    static var previews: some View {
        TimezoneView(timezoneVariables: TimezoneVariables())
            .environmentObject(TimezoneItems())
    }
}

