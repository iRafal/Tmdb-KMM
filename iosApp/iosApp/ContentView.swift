import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
  func makeUIViewController(context: Context) -> UIViewController {
    let container = UIViewController()

    DispatchQueue.main.async {
      if let window = container.view.window {
        let mainVC = MainViewControllerKt.MainViewController(window: window)

        // Replace container's content with the main view controller
        container.addChild(mainVC)
        container.view.addSubview(mainVC.view)
        mainVC.view.frame = container.view.bounds
        mainVC.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        mainVC.didMove(toParent: container)
      }
    }

    return container
  }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard) //INFO: Compose has own keyboard handler
    }
}
