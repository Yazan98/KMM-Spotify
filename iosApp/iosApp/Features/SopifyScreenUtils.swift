//
//  SopifyScreenUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

public class SopifyScreenUtils<T: NSObject> {
    
    static func initViewModelListener(viewModel: SopifyViewModel<T>?) {
        viewModel?.onAttachListenerInstance(errorListener: ViewModelErrorsListener())
    }
}

public class ViewModelErrorsListener: SopifyViewModelListeners {
    
    public func onErrorMessageTriggered(message: String) {
        RadioApplicationUtils.showMessage(errorMessage: message)
    }
    
    public func onErrorScreenEventTriggered(exception: KotlinThrowable) {
        
    }
    
}
