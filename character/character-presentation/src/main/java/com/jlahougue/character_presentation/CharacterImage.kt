package com.jlahougue.character_presentation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.jlahougue.core_presentation.R

@Composable
fun CharacterImage(
    imageUri: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    if (imageUri == null) {
        AsyncImage(
            model = R.drawable.loading,
            imageLoader = imageLoader,
            contentDescription = stringResource(id = R.string.descr_character_image),
            modifier = modifier
        )
    } else {
        if (imageUri.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.anonymous),
                contentDescription = stringResource(id = R.string.descr_character_image),
                modifier = modifier
            )
        } else {
            AsyncImage(
                model = imageUri,
                imageLoader = imageLoader,
                placeholder = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(data = R.drawable.loading)
                        .apply {
                            size(Size.ORIGINAL)
                        }.build(),
                    imageLoader = imageLoader
                ),
                contentDescription = stringResource(id = R.string.descr_character_image),
                modifier = modifier
            )
        }
    }
}