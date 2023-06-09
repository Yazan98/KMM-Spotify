//
//  RadioApplicationUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import MaterialComponents.MaterialSnackbar

class RadioApplicationUtils {
    
    static var httpInstance = HttpBaseClient().httpClient
    
    static func isUserLoggedIn() -> Bool {
        let storageProvider = SopifyStorageProvider(provider: SopifyStorageKeyValue(context: UserDefaults()))
        return storageProvider.isUserLoggedIn()
    }
        
    static func getAccessToken() -> String {
        let storageProvider = SopifyStorageProvider(provider: SopifyStorageKeyValue(context: UserDefaults()))
        return storageProvider.getAccessToken()
    }
        
    static func updateUserLoggedInStatus(newStatus: Bool) {
        let storageProvider = SopifyStorageProvider(provider: SopifyStorageKeyValue(context: UserDefaults()))
        storageProvider.updateLoggedInUser(newState: newStatus)
    }
        
    static func updateAccessToken(newStatus: String) {
        let storageProvider = SopifyStorageProvider(provider: SopifyStorageKeyValue(context: UserDefaults()))
        storageProvider.insertAccessToken(newToken: newStatus)
    }
        
    static func showMessage(errorMessage: String) {
        let action = MDCSnackbarMessageAction()
        let message = MDCSnackbarMessage()
        message.text = errorMessage
        let actionHandler = {() in
                
        }
        action.handler = actionHandler
        action.title = "OK"
        MDCSnackbarManager.default.show(message)
    }
    
}
