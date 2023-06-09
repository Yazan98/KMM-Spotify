//
//  WebView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import WebKit
import SwiftUI

struct WebView: UIViewRepresentable {
    let url: URL
    let onURLChanged: ((URL) -> Void)?
    
    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator
        return webView
    }
    
    func updateUIView(_ webView: WKWebView, context: Context) {
        let request = URLRequest(url: url)
        webView.load(request)
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(onURLChanged: onURLChanged)
    }
    
    class Coordinator: NSObject, WKNavigationDelegate {
        let onURLChanged: ((URL) -> Void)?
        
        init(onURLChanged: ((URL) -> Void)?) {
            self.onURLChanged = onURLChanged
        }
        
        func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
            if let url = navigationAction.request.url {
                onURLChanged?(url)
            }
            decisionHandler(.allow)
        }
    }
}

