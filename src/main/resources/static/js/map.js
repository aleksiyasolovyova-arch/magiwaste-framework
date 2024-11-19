function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 40.7128, lng: -74.0060 }, // Coordinates for New York City
        zoom: 10,
        disableDefaultUI: true,
        styles: [
            {
                featureType: "poi", // Points of interest
                stylers: [{ visibility: "off" }], // Hide POIs
            },
            {
                featureType: "transit", // Transit stations
                stylers: [{ visibility: "off" }], // Hide transit
            },
            {
                featureType: "administrative.land_parcel", // Land parcels
                stylers: [{ visibility: "off" }], // Hide land parcels
            },
            {
                featureType: "road", // Roads
                elementType: "labels.icon", // Icons (e.g., highway icons)
                stylers: [{ visibility: "off" }], // Hide road icons
            },
        ],
    });
}


window.onload = initMap;
