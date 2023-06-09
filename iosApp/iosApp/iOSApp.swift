import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

	var body: some Scene {
		WindowGroup {
            SplashScreen()
        }
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        // Code to run when the application finishes launching
        
        let currentLanguage = Locale.current.languageCode
        RadioApplicationConfigurations.shared.applicationLanguage = currentLanguage ?? "en"
        
        return true
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Code to run when the application enters the background
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Code to run when the application is about to enter the foreground
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Code to run when the application becomes active
    }

    func applicationWillResignActive(_ application: UIApplication) {
        // Code to run when the application is about to resign active
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Code to run when the application is about to terminate
    }
}
