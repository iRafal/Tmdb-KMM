import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        SharedModuleIos().start()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
