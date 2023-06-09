//
//  CategoriesTabScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoriesTabScreen: View {
    
    @ObservedObject var viewModel: HomeViewModel
    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        ZStack {
            if viewModel.categoriesScreenLoadingState {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                ScrollView {
                    LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible()), GridItem(.flexible())], spacing: 16) {
                        ForEach(viewModel.categoriesScreenItems, id: \.self) { element in
                            NavigationLink(destination: EmptyView()) {
                                CategoryComposeView(category: element).onAppear {
                                    print("Screen Item : \(element)")
                                }
                            }
                        }
                    }
                }
            }
        }
        .onAppear {
            if viewModel.categoriesScreenItems.isEmpty {
                viewModel.getViewModelInstance()?.execute(action: GetCategoriesScreenContentAction())
            }
        }
    }
}
