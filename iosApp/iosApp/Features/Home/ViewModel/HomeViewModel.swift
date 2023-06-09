//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class HomeViewModel: ObservableObject, HomeViewModelStateListener {
    
    private var viewModelOperationsInstance: HomeViewModelOperations?
    @Published var homeScreenItems: [RadioHomeItem] = []
    @Published var categoriesScreenItems: [RadioCategoryItem] = []
    @Published var accountScreenItems: [RadioAccountItem] = []
    
    @Published var homeScreenLoadingState: Bool = false
    @Published var categoriesScreenLoadingState: Bool = false
    @Published var accountScreenLoadingState: Bool = false
    
    init() {
        self.viewModelOperationsInstance = HomeViewModelOperations(listener: self)
    }
    
    public func getViewModelInstance() -> HomeViewModelOperations? {
        return self.viewModelOperationsInstance
    }
    
    func onHomeScreenItems() {
        
    }
    
    func onCategoriesListUpdate(newList: [RadioCategoryItem]) {
        DispatchQueue.main.async {
            self.categoriesScreenItems = newList
        }
    }
    
    func onCategoriesListLoadingUpdate(newState: Bool) {
        DispatchQueue.main.async {
            self.categoriesScreenLoadingState = newState
        }
    }
    
}

