import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        // Initialize Koin for dependency injection
        MainViewControllerKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
