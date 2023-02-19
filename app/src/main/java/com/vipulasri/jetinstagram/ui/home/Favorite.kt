package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.data.FavoriteRepository
import com.vipulasri.jetinstagram.ui.theme.grey100
import com.vipulasri.jetinstagram.ui.theme.grey50

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun FavScaffold() {
    Scaffold(
        modifier = Modifier
            .background(color = grey50)
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                backgroundColor = grey50,
                title = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Close, contentDescription = ""
                        )

                    }
                    Box(contentAlignment = Alignment.Center) {

                        Text(
                            "Favorites",
                            Modifier
                                .fillMaxWidth()
                                .background(color = Color.Transparent),
                            textAlign = TextAlign.Center,
                            color = Color.Black,

                            )
                    }

                },
                actions = {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Check, contentDescription = ""
                        )

                    }
                }
            )
        },

        content = {
            Column(modifier = Modifier) {

                TopNotification()
                Spacer(modifier = Modifier.height(10.dp))
                SearchBar()
                Divider()
                ContentLazyColumn()

            }
        },
    )
}


@Composable
fun TopNotification() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("New posts from your favorites will appear higher in feed. Only you can see who you add or remove. ")
            }
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("How it works")
            }
        }, modifier = Modifier
            .background(grey100)
            .padding(18.dp), fontSize = 15.sp
    )
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(text = "Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun ContentLazyColumn() {
    val favoriteRepository by FavoriteRepository.posts
    val list = listOf(
        "storee",
        "nianyc",
        "opioke",
        "ashoke",
        "dark_emarlds",
        "bedtan",
        "shrish",
        "matdo",
        "phillsohn",
        "deitch"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 18.dp)
    ) {
        items(items = favoriteRepository, itemContent = { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    rememberImagePainter(item.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop,

                    )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = item.name)
                }
                val stateFavButton = mutableStateOf(true)

                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {


                    Button(
                        onClick = { stateFavButton.value = !stateFavButton.value },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .height(25.dp)
                            .width(65.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = if (stateFavButton.value) {
                                "Add"
                            } else {
                                "Remove"
                            },
                            fontSize = 12.sp
                        )

                    }
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
        })
    }
}

