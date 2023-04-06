//
//  MovieSectionItemView.swift
//  iosApp
//
//  Created by Andrii Medvid on 06.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import struct Kingfisher.KFImage

struct MovieSectionItemView: View {
    var movie: HomeUiData.Movie
    var onClick: () -> Void

    var body: some View {
        VStack() {
            KFImage(URL(string: movie.posterUrl ?? ""))
                           .resizable()
                           .frame(width: 64, height: 64, alignment: .center)
                           .cornerRadius(8)
                           .padding(.trailing)
            Spacer(minLength: 8)
            
            VStack() {
                Text(movie.title)
                Spacer(minLength: 8)
                
                if let releaseDate = movie.releaseDate {
                    Text(releaseDate.format(pattern: "d MMM yyyy"))
                    Spacer(minLength: 8)
                }

                Text(String(movie.averageVote))
            }
        }.onTapGesture {
            onClick()
        }
    }
}

struct MovieSectionItemView_Previews: PreviewProvider {
    static let movie = HomeUiData.Movie(
        id: 1,
        title: "Movie 1",
        averageVote : 70.7,
        releaseDate: nil,//Kotlinx_datetimeLocalDate.companion.parse(isoString: "1 Jan 2022"),
        posterUrl : nil
    )
    static var previews: some View {
        MovieSectionItemView(
            movie: movie,
            onClick: {}
        )
    }
}
