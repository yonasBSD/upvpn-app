package app.upvpn.upvpn.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.upvpn.upvpn.model.Location
import app.upvpn.upvpn.model.toCountries
import app.upvpn.upvpn.ui.state.LocationUiState

@Composable
fun AllLocations(
    locationUiState: LocationUiState,
    verticalCountrySpacing: Dp,
    onRefresh: () -> Unit,
    isSelectedLocation: (Location) -> Boolean,
    onLocationSelected: (Location) -> Unit
) {
    if (locationUiState.locationFetchError != null && locationUiState.locations.isEmpty()) {
        LocationsError(error = locationUiState.locationFetchError, onRefresh)
    } else {
        LazyVerticalStaggeredGrid(
            verticalItemSpacing = verticalCountrySpacing,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            columns = StaggeredGridCells.Adaptive(300.dp),
            // so that after scrolling there is a gap at the top and bottom
            contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 10.dp),
            modifier = Modifier
                .fillMaxHeight()
                .animateContentSize()
        ) {
            items(locationUiState.locations.toCountries()) {
                CountryComponent(
                    country = it,
                    isSelectedLocation = isSelectedLocation,
                    onLocationSelected = onLocationSelected
                )
            }
        }
    }
}
