//
//  HomeView.swift
//  iosApp
//
//  Created by Andrii Medvid on 06.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared


struct HomeView: View {
    @ObservedObject var homeState: HomeState
    
    init() {
        homeState = HomeState()
    }
    
    var body: some View {
             VStack {
                Text("Now Playing")
                List(homeState.nowPlayingSection, id: \.id) { section in
                    MovieSectionItemView(
                        movie: section,
                        onClick: {}
                    )
                }
                
                Text("Now Popular")
                List(homeState.nowPopularSection, id: \.id) { section in
                    MovieSectionItemView(
                        movie: section,
                        onClick: {}
                    )
                }
                
                Text("Top Rated")
                List(homeState.topRatedSection, id: \.id) { section in
                    MovieSectionItemView(
                        movie: section,
                        onClick: {}
                    )
                }
                
                Text("Upcoming")
                List(homeState.upcomingSection, id: \.id) { section in
                    MovieSectionItemView(
                        movie: section,
                        onClick: {}
                    )
                }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
