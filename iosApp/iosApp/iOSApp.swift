import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        SharedModule().start {
            (additionalConfig: Koin_coreKoinApplication) -> Void in
        }
        let sharedHomeViewModel = SharedModule().sharedHomeViewModel
//        let homeViewModel = SharedUiModule().homeViewModel
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
