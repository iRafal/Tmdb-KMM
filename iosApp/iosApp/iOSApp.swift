import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        //SharedModule().doInit()
        let a = SharedModule().sharedHomeViewModel
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
