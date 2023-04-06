import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        NavigationView {
            VStack {
                HomeView()
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
