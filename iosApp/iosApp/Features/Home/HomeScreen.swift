//
//  HomeScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    
    @StateObject var viewModel: HomeViewModel = HomeViewModel()
    
    static func getScreenInstance() -> some View {
        let screen = HomeScreen()
        let viewModel = HomeViewModel()
        SopifyScreenUtils<HomeScreenAction>.initViewModelListener(viewModel: viewModel.getViewModelInstance())
        return screen.environmentObject(viewModel)
    }
    
    var body: some View {
        NavigationView {
                    ZStack {
                        TabView {
                            HomeTabScreen()
                                .environmentObject(viewModel)
                                .clipped()
                                .tabItem {
                                    Image(systemName: "house")
                                    Text(RadioApplicationMessages.shared.getMessage(key: "home_tab_1"))
                                }
                            
                            CategoriesTabScreen(viewModel: viewModel)
                                .environmentObject(viewModel)
                                .tabItem {
                                    Image(systemName: "list.bullet")
                                    Text(RadioApplicationMessages.shared.getMessage(key: "home_tab_2"))
                                }
                            
                            AccountTabScreen()
                                .environmentObject(viewModel)
                                .tabItem {
                                    Image(systemName: "person.fill")
                                    Text(RadioApplicationMessages.shared.getMessage(key: "home_tab_3"))
                                }
                        }
                    }
                    .navigationBarTitle("Radio")
                    .navigationBarTitleDisplayMode(.inline)
                }
        
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                UIApplication.shared.windows.first?.rootViewController?.dismiss(animated: true, completion: nil)
            }
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
