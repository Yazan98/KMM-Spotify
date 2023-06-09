//
//  HomeViewModelStateListener.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

public protocol HomeViewModelStateListener {
    func onHomeScreenItems()
    
    func onCategoriesListUpdate(newList: [RadioCategoryItem])
    func onCategoriesListLoadingUpdate(newState: Bool)
    
}
