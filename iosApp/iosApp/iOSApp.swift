import SwiftUI
import ComposeApp

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
