//
//  CategoryComposeView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct CategoryComposeView: UIViewControllerRepresentable {
    var category: RadioCategoryItem
    init(category: RadioCategoryItem) {
        self.category = category
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    func makeUIViewController(context: Context) -> some UIViewController {
        return HomeIosViewControllerKt.HomeCategoryComposableAlias(item: category) { String in
            
        }
    }
}
