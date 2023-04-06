//
//  HomeState.swift
//  iosApp
//
//  Created by Andrii Medvid on 06.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class HomeState: ObservableObject {
    let viewModel: HomeViewModel
    
    @Published private(set) var nowPlayingSection: [HomeUiData.Movie] = []
    @Published private(set) var nowPopularSection: [HomeUiData.Movie] = []
    @Published private(set) var topRatedSection: [HomeUiData.Movie] = []
    @Published private(set) var upcomingSection: [HomeUiData.Movie] = []
    
    init() {
        viewModel = SharedModule().homeViewModel
        viewModel.observeUiStateIos(
            onChange: { sectionsData in
                sectionsData.forEach { homeUiData in
                    if homeUiData.isSuccess {
                        switch homeUiData.section {
                        case HomeMovieSection.nowPlaying:
                            self.nowPlayingSection = homeUiData.data
                        case HomeMovieSection.nowPopular:
                            self.nowPopularSection = homeUiData.data
                        case HomeMovieSection.topRated:
                            self.topRatedSection = homeUiData.data
                        case HomeMovieSection.upcoming:
                            self.upcomingSection = homeUiData.data
                        default:
                            ""
                        }
                    }
                }
            }
        )
    }
    
    deinit {
        viewModel.dispose()
    }
}
