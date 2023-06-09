//
//  RadioUseCasesProvider.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

public class RadioUseCasesProvider {
    
    public static let homeScreenUseCaseInstance = GetHomeScreenItemsUseCase()
    public static let categoriesUseCaseInstance = GetCategoriesUseCase()
    public static let accountTreeUseCaseInstance = GetAccountTreeInfoUseCase()
    
}
