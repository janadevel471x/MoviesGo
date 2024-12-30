package com.example.moviesgo.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.moviesgo.model.Movies

@Composable
fun MovieRow(movie: Movies, onItemClick: (String) -> Unit = {}) {
    var expandable by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
//            .height(130.dp)
            .clickable {
                onItemClick(movie.Title)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
            ) {
//                Icon(
//                    imageVector = Icons.Default.AccountBox,
//                    contentDescription = "Movie Image"
//                )
                Image(
                    painter = rememberAsyncImagePainter(model = movie.poster),
                    contentDescription = "Movie Image"
                )

            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.Title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Director: ${movie.Title}",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Release: ${movie.Released}",
                    style = MaterialTheme.typography.labelLarge
                )
                AnimatedVisibility(visible = expandable) {
                    Column {
                        Text(text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                Color.DarkGray,
                                fontSize = 13.sp)
                            ){
                                append("Plot: ")
                            }

                            withStyle(style = SpanStyle(
                                Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light
                            )
                            ){
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        HorizontalDivider(modifier = Modifier.padding(6.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.labelLarge)
                        Text(text = "Actor: ${movie.actors}", style = MaterialTheme.typography.labelLarge)
                        Text(text = "Rating: ${movie.Rated}", style = MaterialTheme.typography.labelLarge)
                    }
                }

                Icon(
                    imageVector = if(expandable) {
                        Icons.Filled.KeyboardArrowUp}
                    else{
                        Icons.Filled.KeyboardArrowDown},
                    contentDescription = "Down Arrow",
                    modifier = Modifier.size(25.dp).
                    clickable {
                        expandable = !expandable
                    },
                    tint = Color.DarkGray
                )

            }

        }
    }

    Spacer(modifier = Modifier.padding(5.dp))
}