import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
  @Binding var isDarkTheme: Bool?
  func makeUIViewController(context: Context) -> UIViewController {
    let viewController = MainViewControllerKt.MainViewController {  isDarkTheme in
    }
    return viewController
  }

  func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
  @State private var isDarkTheme: Bool? = nil

  var body: some View {
    ComposeView(isDarkTheme: $isDarkTheme)
        .preferredColorScheme(
          isDarkTheme == nil ? nil : (isDarkTheme! ? .dark : .light)
        )
        .ignoresSafeArea(edges: .all)
        .ignoresSafeArea(.keyboard)
  }
}
