import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        initLogging()
        multiplatformDiInit()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

private func initLogging() {
  IosLogInitializer.shared.initialize()
}

private func multiplatformDiInit() {
    AppModule().startIos()
}
