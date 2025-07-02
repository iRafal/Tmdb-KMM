import SwiftUI
import shared

@main
struct iOSApp: App {

  init() {
    AppModule.shared.startIos()
  }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
