import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        SharedModule().start()
        let sharedHomeViewModel = SharedModule().sharedHomeViewModel
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
