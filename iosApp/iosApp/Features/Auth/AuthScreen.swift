//
//  AuthScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    private var authUseCase: GetAccessTokenUseCase = GetAccessTokenUseCase()
    private var authManager = SpotifyAuthManager()
    @State private var currentURL: URL? = nil
    @State private var isLoading: Bool = false
    @State private var shouldNavigate: Bool = false
    
    init() {
        self.authUseCase.addHttpClient(httpClient: RadioApplicationUtils.httpInstance)
    }
    
    var body: some View {
        VStack {
            if shouldNavigate {
                NavigationLink(destination: HomeScreen.getScreenInstance(), isActive: $shouldNavigate) {
                    EmptyView()
                }
                .hidden()
            }
            
            if isLoading {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                let urlString = authManager.getAuthLoginUrl().addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)
                WebView(url: URL(string: urlString ?? "")!, onURLChanged: { url in
                    if authManager.isAccessTokenUrl(url: url.absoluteString) {
                        var accessToken = authManager.getAccessTokenByUrl(url: url.absoluteString)
                        isLoading = true
                        authUseCase.execute(requestValue: accessToken as NSString, listener: AuthUseCaseListener(screen: self))
                    }
                })
                .frame(height: .infinity)
                .background(Color.white)
            }
        }
        .onAppear {
            currentURL = URL(string: authManager.getAuthLoginUrl())
        }
    }
    
    class AuthUseCaseListener: SopifyUseCaseListener {
        
        private var screen: AuthScreen
        
        init(screen: AuthScreen) {
            self.screen = screen
        }
        
        func onStateUpdated(newState: SopifyState) {
            if newState is SopifySuccessState {
                let response = (newState as! SopifySuccessState).payload as! SpotifyAuthResponse
                RadioApplicationUtils.updateUserLoggedInStatus(newStatus: true)
                RadioApplicationUtils.updateAccessToken(newStatus: response.accessToken ?? "")
                RadioApplicationUtils.showMessage(errorMessage: RadioApplicationMessages.shared.getMessage(key: "login_message_success"))
                screen.isLoading = true
                screen.shouldNavigate = true
            } else if newState is SopifyErrorState {
                RadioApplicationUtils.showMessage(errorMessage: (newState as! SopifyErrorState).exception.message ?? "")
            }
        }
    }
    
}

struct AuthScreen_Previews: PreviewProvider {
    static var previews: some View {
        AuthScreen()
    }
}
