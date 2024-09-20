package com.example.rickandmortycompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmortycompose.presentation.model.CharacterModel
import com.example.rickandmortycompose.presentation.viewmodel.RickAndMortyViewModel
import androidx.hilt.navigation.compose.hiltViewModel


/**
 * Composable function that displays a list of characters from Rick and Morty using a ViewModel to
 * manage the data. It handles different states such as loading, empty state, and error.
 *
 * @param rickAndMortyViewModel ViewModel that provides the list of characters through a paging source.
 */
@Composable
fun ListScreen(rickAndMortyViewModel: RickAndMortyViewModel= hiltViewModel()) {

    // Collect the list of characters as LazyPagingItems, allowing paginated loading.
    val characters = rickAndMortyViewModel.characters.collectAsLazyPagingItems()

    // Handle different states based on the loading status of the characters data.
    when {
        // Show a loading indicator when refreshing and there are no items loaded yet.
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.White
                )
            }
        }
        // Show a message when the loading is complete, but no characters are available.
        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Text(text = "Todavía no hay personajes")
        }

        // Show an error message if there's an issue loading the characters.
        characters.loadState.hasError -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Red), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ha ocurrido un error")
            }
        }
        // Display the list of characters when they are available.
        else -> {
            CharactersList(characters)

            // Show a loading indicator at the bottom while more items are being loaded (pagination).
            if (characters.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp), color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CharactersList(characters: LazyPagingItems<CharacterModel>) {
    LazyColumn {
        items(characters.itemCount) {
            characters[it]?.let { characterModel ->
                ItemList(characterModel)
            }
        }
    }
}

@Composable
fun ItemList(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .padding(14.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Black)
            .fillMaxWidth()
            .height(250.dp), contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = characterModel.name, color = Color.White, fontSize = 18.sp)
        }

    }
}
