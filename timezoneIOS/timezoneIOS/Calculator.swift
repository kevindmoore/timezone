//
//  Calculator.swift
//  timezoneIOS
//
//  Created by Kevin Moore on 6/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Calculator: View {
    @EnvironmentObject var timezones : TimezoneItems
    @State private var selectedStartIndex = 8
    @State private var selectedEndIndex = 17
    @State private var timezoneHelper = TimeZoneHelperImpl()
    @State private var meetingHours = [Int]()
    @State var showHoursDialog: Bool = false
    
    @ObservedObject var calculatorVariables: CalculatorVariables
    
    var body: some View {
        VStack {
            NavigationView {
                VStack {
                    Spacer()
                        .frame(height: 8)
                    Text("Find Meetings")
                        .bold()
                    Spacer()
                        .frame(height: 8)
                    Form {
                        Section(header: Text("Time Range")) {
                            HStack() {
                                Picker("Start Time", selection: $selectedStartIndex, content: {
                                    ForEach(0..<24, id:\.self, content: { index in
                                        Text("\(index)").tag("Start\(index)")
                                    })
                                })
                                Spacer()
                                    .frame(height: 8)
                                Picker("End Time", selection: $selectedEndIndex, content: {
                                    ForEach(0..<24, id:\.self, content: { index in
                                        Text("\(index)").tag("End\(index)")
                                    })
                                })
                            }
                        }
                        Section(header: Text("Time Zones")) {
                            List() {
                                ForEach(Array(timezones.selectedTimezones), id: \.self) {  timezone in
                                    HStack {
                                        Text(timezone)
                                        Spacer()
                                    }
                                }
                            }
                        }
                    } // Form
                    Spacer()
                    Button(action: {
                        meetingHours.removeAll()
                        let hours = timezoneHelper.search(startHour:Int32(selectedStartIndex), endHour:Int32(selectedEndIndex), timezoneStrings:Array(timezones.selectedTimezones))
                        let hourInts = hours.map { kotinHour in
                            Int(truncating: kotinHour)
                        }
                        meetingHours.append(contentsOf: hourInts)
                        calculatorVariables.showHoursDialog = true
                        //                                    showHoursDialog = true
                    }, label: {
                        Text("Search")
                    })
                } // VStack
            } // NavigationView
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .top
            )
            Text("").hidden().sheet(isPresented: $calculatorVariables.showHoursDialog) {
                //                HourSheet(hours: $meetingHours, showHoursDialog: $showHoursDialog)
                HourSheet(hours: $meetingHours, calculatorVariables: calculatorVariables)
            }
        }
        
        
    }
    
}

struct Calculator_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            Calculator(calculatorVariables: CalculatorVariables())
                .environmentObject(TimezoneItems())
        }
    }
}
