//
//  ContentView.swift
//  Test1
//
//  Created by Kevin Moore on 6/10/21.
//

import SwiftUI
import shared

struct ContentView: View {
    @State private var selection = 1
    @StateObject var timezoneVariables = TimezoneVariables()
    @StateObject var calculatorVariables = CalculatorVariables()
    @StateObject var timezones = TimezoneItems()
 
    init() {
        UITabBar.appearance().backgroundColor = UIColor.blue
    }
    var body: some View {
        TabView(selection:$selection) {
            TimezoneView(timezoneVariables: timezoneVariables)
                .tabItem {
                    Image(systemName: "network")
                    Text("Timezones")
                }
            Calculator(calculatorVariables: calculatorVariables)
                .tabItem {
                    Image(systemName: "function")
                    Text("Calculator")
                }
        }
        .environmentObject(timezones)
    }
}


struct ContentView_Previews: PreviewProvider {
    init() {
        UITabBar.appearance().backgroundColor = UIColor.blue
    }
    static var previews: some View {
        ContentView()
    }
}
