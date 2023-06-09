//
//  HomeViewModelOperations.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

public class HomeViewModelOperations: SopifyViewModel<HomeScreenAction> {
    
    private var viewModelListener: HomeViewModelStateListener? = nil
    private let homeScreenUseCaseInstance = RadioUseCasesProvider.homeScreenUseCaseInstance
    private let categoriesUseCaseInstance = RadioUseCasesProvider.categoriesUseCaseInstance
    private let accountUseCaseInstance = RadioUseCasesProvider.accountTreeUseCaseInstance
    init(listener: HomeViewModelStateListener) {
        self.viewModelListener = listener
    }
    
    @objc public override func onNewActionTriggered(action: HomeScreenAction? = nil) async throws {
        if action is GetHomeScreenContentAction {
            getHomeScreenItemsContent()
        } else if action is GetCategoriesScreenContentAction {
            getCategoriesScreenContent()
        } else if action is GetAccountScreenContentAction {
            getAccountScreenContent()
        }
    }
    
    private func getHomeScreenItemsContent() {
        
    }
    
    private func getCategoriesScreenContent() {
        class CategoriesStateListener: SopifyUseCaseListener {
            var viewModelListener: HomeViewModelStateListener? = nil
            init(viewModelListener: HomeViewModelStateListener? = nil) {
                self.viewModelListener = viewModelListener
            }
            
            func onStateUpdated(newState: SopifyState) {
                if newState is SopifyLoadingState {
                    viewModelListener?.onCategoriesListLoadingUpdate(newState: (newState as! SopifyLoadingState).isLoading)
                } else if newState is SopifySuccessState {
                    let state = newState as! SopifySuccessState
                    let categoriesList = state.payload as! [RadioCategoryItem]
                    viewModelListener?.onCategoriesListUpdate(newList: categoriesList)
                }
            }
        }
        
        categoriesUseCaseInstance.addHttpClient(httpClient: RadioApplicationUtils.httpInstance)
        categoriesUseCaseInstance.execute(
            requestValue: RadioApplicationUtils.getAccessToken() as NSString,
            listener: CategoriesStateListener(viewModelListener: viewModelListener)
        )
    }
    
    private func getAccountScreenContent() {
        
    }
    
    deinit {
        clear()
        homeScreenUseCaseInstance.clear()
        categoriesUseCaseInstance.clear()
        accountUseCaseInstance.clear()
    }
    
}
