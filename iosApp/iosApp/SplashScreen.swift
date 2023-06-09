import SwiftUI
import shared

struct SplashScreen: View {
    var body: some View {
        NavigationView {
            if RadioApplicationUtils.isUserLoggedIn() {
                HomeScreen.getScreenInstance()
            } else {
                AuthScreen()
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        SplashScreen()
	}
}
