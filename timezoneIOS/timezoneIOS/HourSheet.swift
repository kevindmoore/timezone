//
//  HourSheet.swift
//  timezoneIOS
//
//  Created by Kevin Moore on 6/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct HourSheet: View {
    @Binding var hours: [Int]
    @ObservedObject var calculatorVariables: CalculatorVariables
    @Environment(\.presentationMode) var presentationMode
//    @Binding var showHoursDialog: Bool

    
    var body: some View {
        List() {
            ForEach(hours, id: \.self) {  hour in
                Text("\(hour)")
            }
        }
        Spacer()
        Button("Dismiss") {
            calculatorVariables.showHoursDialog = false
//            showHoursDialog = false
//            self.presentationMode.wrappedValue.dismiss()
        }
    }
}

struct HourSheet_Previews: PreviewProvider {
    static var previews: some View {
//        HourSheet(hours: .constant( [8,9,10]), showHoursDialog: .constant(true))
        HourSheet(hours: .constant( [8,9,10]), calculatorVariables: CalculatorVariables())
    }
}
