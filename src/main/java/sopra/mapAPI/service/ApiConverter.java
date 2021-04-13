package sopra.mapAPI.service;

import sopra.mapAPI.GeoAdminApi;

public class ApiConverter {
    /**
     * Convert a user input summit name to a GeoAdmin API request
     * @autor: Beat Furrer
     * @version: 1.0
     */
    private String searchName = "";
    private String uri = "";

    /**
     * Constructor
     * @param searchName for the API searchText
     */
    public ApiConverter(String searchName){
        uri = getGeoAdminRequest(searchName);
    }

    /**
     * Returns the full URL
     * @return URL for find request on GeoAdmin Map
     */
    public String getUri() {
        System.out.println(uri);
        return uri;
    }

    private String getGeoAdminRequest(String searchName){
        return (GeoAdminApi.URLMapServer.value +
                GeoAdminApi.findRequest.value +
                GeoAdminApi.layerNames.value +
                GeoAdminApi.AND.value +
                GeoAdminApi.searchText.value +
                searchName +
                GeoAdminApi.AND.value +
                GeoAdminApi.searchField.value);
    }
}
